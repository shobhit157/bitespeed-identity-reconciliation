package com.example.bitespeed_identity.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
//import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

//import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bitespeed_identity.dto.ContactRequest;
import com.example.bitespeed_identity.dto.ContactResponse;
import com.example.bitespeed_identity.entity.Contact;
import com.example.bitespeed_identity.entity.LinkPrecedence;
import com.example.bitespeed_identity.repository.ContactRepository;

@Service
public class ContactService {
	
	
	@Autowired
	private ContactRepository contactRepository;

	public ContactResponse identify(ContactRequest request) {
		// TODO Auto-generated method stub
		List<Contact> contactsByEmail = new ArrayList<>();
	    List<Contact> contactsByPhone = new ArrayList<>();
	    
	    
	    
	    if(request.getEmail()!=null)
	    {
	    	contactsByEmail = contactRepository.findByEmail(request.getEmail());
	    	
	    }
	    
	    if(request.getPhoneNumber()!=null)
	    {
	    	contactsByPhone = contactRepository.findByPhoneNumber(request.getPhoneNumber());
	    	
	    }
	    
	    
	    Set<Contact> allContacts = new HashSet<>();
	    allContacts.addAll(contactsByEmail);
	    allContacts.addAll(contactsByPhone);
	    
	    if (allContacts.isEmpty()) {

	        Contact newContact = new Contact();
            newContact.setEmail(request.getEmail());
	        newContact.setPhoneNumber(request.getPhoneNumber());
	        newContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
	        newContact.setCreatedAt(LocalDateTime.now());
	        newContact.setUpdatedAt(LocalDateTime.now());

	        contactRepository.save(newContact);

	        return buildResponse(newContact, List.of());
	    }
	    
	    
	    // Contact exists
	    
	   
	     List<Contact> primaries = allContacts.stream()
        .filter(c -> c.getLinkPrecedence() == LinkPrecedence.PRIMARY)
        .toList();
	     
	     if(primaries.size()>1)
	     {
	     
	     //to know which primary is oldest
	     
	          List<Contact> sortedPrimaries = primaries.stream()
              .sorted(Comparator.comparing(Contact::getCreatedAt))
              .toList();

             Contact oldestPrimary = sortedPrimaries.get(0);
	     
	         for (int i = 1; i < sortedPrimaries.size(); i++) {

                 Contact newerPrimary = sortedPrimaries.get(i);

               newerPrimary.setLinkPrecedence(LinkPrecedence.SECONDARY);
               newerPrimary.setLinkedId(oldestPrimary.getId());
               newerPrimary.setUpdatedAt(LocalDateTime.now());

                contactRepository.save(newerPrimary);
              }
          
               List<Contact> updatedContacts = contactRepository
               .findByLinkedId(oldestPrimary.getId());

               return buildResponse(oldestPrimary, updatedContacts);
	  
	      }
	     
	     
	                            
	    
	    

	    // Find primary from DB contacts
	    Contact primary = allContacts.stream()
	            .filter(c -> c.getLinkPrecedence() == LinkPrecedence.PRIMARY)
	            .findFirst()
	            .orElse(allContacts.iterator().next());
	    
	    
	    boolean exactMatchExists = allContacts.stream()
	    	    .anyMatch(c ->
	    	        Objects.equals(c.getEmail(), request.getEmail()) &&
	    	        Objects.equals(c.getPhoneNumber(), request.getPhoneNumber())
	    	    );

	    	if (exactMatchExists) {
	    	    List<Contact> secondaries = allContacts.stream()
	    	            .filter(c -> !c.getId().equals(primary.getId()))
	    	            .toList();

	    	    return buildResponse(primary, secondaries);
	    	}

	    // Create new secondary
	    Contact secondary = new Contact();
	    secondary.setEmail(request.getEmail());
	    secondary.setPhoneNumber(request.getPhoneNumber());
	    secondary.setLinkPrecedence(LinkPrecedence.SECONDARY);
	    secondary.setLinkedId(primary.getId());
	    secondary.setCreatedAt(LocalDateTime.now());
	    secondary.setUpdatedAt(LocalDateTime.now());

	    contactRepository.save(secondary);

	    // 🔥 ADD new contact to set
	    allContacts.add(secondary);

	    // Collect secondaries (everything except primary)
	    List<Contact> secondaries = allContacts.stream()
	            .filter(c -> !c.getId().equals(primary.getId()))
	            .toList();

	    return buildResponse(primary, secondaries);
	    
	}

	public ContactResponse buildResponse(Contact primary, List<Contact> secondaries) {
		// TODO Auto-generated method stub
		 ContactResponse response = new ContactResponse();
		 
		 response.setPrimaryContactId(primary.getId());
		 
		 

//		    response.setPrimaryContactId(primary.getId());

		    // Collect all emails
		    response.setEmails(List.of(primary.getEmail()));

		    // Collect all phone numbers
		    response.setPhoneNumbers(
		        Stream.concat(
		            Stream.of(primary.getPhoneNumber()),
		            secondaries.stream().map(Contact::getPhoneNumber)
		        )
		        .filter(Objects::nonNull)
		        .distinct()
		        .toList()
		    );

		    // Collect secondary contact IDs
		    response.setSecondaryContactIds(
		        secondaries.stream()
		            .map(Contact::getId)
		            .toList()
		    );

		    return response;
	
	}
	
	

}
