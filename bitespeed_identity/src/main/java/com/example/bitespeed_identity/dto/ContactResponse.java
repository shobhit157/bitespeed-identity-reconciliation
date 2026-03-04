package com.example.bitespeed_identity.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
	
	private Long primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Long> secondaryContactIds;
	public void setPrimaryContactId(Long id) {
		
		this.primaryContactId=id;
		// TODO Auto-generated method stub
		
	}
	public void setEmails(List<String> list) {
		// TODO Auto-generated method stub
		if (list == null) {
	        this.emails = new ArrayList<>();
	    } else {
	        this.emails = list.stream().distinct().collect(Collectors.toList());
	    }
		
	}
	public void setPhoneNumbers(List<String> list) {
		// TODO Auto-generated method stub
		if (list == null) {
	        this.phoneNumbers = new ArrayList<>();
	    } else {
	        this.phoneNumbers = list.stream().distinct().collect(Collectors.toList());
	    }
		
	}
	public void setSecondaryContactIds(List<Long> list) {
		// TODO Auto-generated method stub
		if (list == null) {
	        this.secondaryContactIds = new ArrayList<>();
	    } else {
	        this.secondaryContactIds = list.stream().distinct().collect(Collectors.toList());
	    }
		
	}
    
	public Long getPrimaryContactId()
	{
		return this.primaryContactId;
	}
	
	public List<String> getEmails()
	{
		return this.emails;
	}
     
	public List<String> getPhoneNumbers()
	{
		return this.phoneNumbers;
	}
	
	public List<Long> getSecondaryContactIds()
	{
		return this.secondaryContactIds;
	}
	

}
