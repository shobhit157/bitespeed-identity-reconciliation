package com.example.bitespeed_identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ContactRequest {
	
	private String Email;
	
	private String PhoneNumber;

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.Email;
	}
	
	public String getPhoneNumber() {
		return this.PhoneNumber;
	}

}
