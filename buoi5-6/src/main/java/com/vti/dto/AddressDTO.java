package com.vti.dto;

public class AddressDTO {
	private int id;
	
	private String street;
	
	private String city;
	
	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AddressDTO(int id, String street, String city, String username) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.username = username;
	}
	
	
}
