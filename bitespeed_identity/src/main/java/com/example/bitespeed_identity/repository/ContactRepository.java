package com.example.bitespeed_identity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bitespeed_identity.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
	
	List<Contact> findByEmail(String email);

    List<Contact> findByPhoneNumber(String phoneNumber);

    List<Contact> findByLinkedId(Long linkedId);

}
