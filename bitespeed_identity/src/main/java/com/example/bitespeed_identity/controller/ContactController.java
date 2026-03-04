package com.example.bitespeed_identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bitespeed_identity.dto.ContactRequest;
import com.example.bitespeed_identity.dto.ContactResponse;
import com.example.bitespeed_identity.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/identify")
	private ContactResponse identify(@RequestBody ContactRequest request)
	{
		return contactService.identify(request);
	}

}
