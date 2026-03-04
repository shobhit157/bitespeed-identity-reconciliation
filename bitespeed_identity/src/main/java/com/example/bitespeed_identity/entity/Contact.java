package com.example.bitespeed_identity.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;

	 private String email;

	 private String phoneNumber;

	 private Long linkedId;
	 
	 @Enumerated(EnumType.STRING)
	 private LinkPrecedence linkPrecedence;

	 private LocalDateTime createdAt;

	 private LocalDateTime updatedAt;

	 private LocalDateTime deletedAt;

	 public void setEmail(String email2) {
		// TODO Auto-generated method stub
		this.email=email2;
	 }

	 public void setPhoneNumber(String phoneNumber2) {
		// TODO Auto-generated method stub
		this.phoneNumber=phoneNumber2;
	 }

	 public void setLinkPrecedence(LinkPrecedence precedence) {
		// TODO Auto-generated method stub
		 this.linkPrecedence=precedence;
		
	 }

	 public void setCreatedAt(LocalDateTime now) {
		// TODO Auto-generated method stub
		 this.createdAt=now;
		
	 }

	 public void setUpdatedAt(LocalDateTime now) {
		 
		 this.updatedAt=now;
		// TODO Auto-generated method stub
		
	 }

	 public LinkPrecedence getLinkPrecedence() {
		// TODO Auto-generated method stub
		return this.linkPrecedence;
	 }

	 public Long getLinkedId() {
		// TODO Auto-generated method stub
		return this.linkedId;
	 }

	 public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	 }

	 public void setLinkedId(Long id2) {
		// TODO Auto-generated method stub
		 this.linkedId=id2;
		
	 }

	 public LocalDateTime getCreatedAt() {
		// TODO Auto-generated method stub
		return this.createdAt;
	 }

	 public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	 }

	 public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return this.phoneNumber;
	 }
	 
	 
	


}
