package com.vti.dto;

import java.util.Set;

import lombok.Data;

@Data
public class SignupDTO {
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private Set<String> roles;
	
	private String status;
}
