package com.uned.covid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @NoArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@NonNull @Column(name = "username")  				
	private String username;

	@NonNull @Column(name = "email") 
	private String email;

	@NonNull @Column(name = "password") 
	private String password;

	@NonNull @Column(name = "validation")
	private String validation;

	@Column(name = "level")
	private int level = 1;
}