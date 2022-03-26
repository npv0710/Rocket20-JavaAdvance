package com.vti.dto;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class JwtResponseDTO {
	@NonNull
	private String token;
	@NonNull
	private int id;
	@NonNull
	private String username;
	@NonNull
	private String email;
	@NonNull
	private List<String> roles;
//	public JwtResponseDTO(String token, int id, String username, String email, List<String> roles) {
//		super();
//		this.token = token;
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.roles = roles;
//	}
}
