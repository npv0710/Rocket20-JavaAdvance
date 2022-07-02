package com.vti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	public Account() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountRole getRole() {
		return role;
	}

	public void setRole(AccountRole role) {
		this.role = role;
	}

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", length = 50, nullable = false)
	private String username;
	
	@Column(name = "password", length = 50, nullable = false)
	private String password;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Column(name = "role", columnDefinition = "ENUM('ADMIN', 'EMPLOYEE', 'MANAGER')")
	@Enumerated(EnumType.STRING)
	private AccountRole role;
	
//	@OneToOne
//	@JoinColumn(name = "address_id", referencedColumnName = "id")
//	private Address address;
	
	@OneToMany(mappedBy = "account")
	private List<Address> addresses;
	
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	
//	@ManyToMany()
//	@JoinTable(
//		name = "AccountAddress",
//		joinColumns = { @JoinColumn(name = "account_id")},
//		inverseJoinColumns = { @JoinColumn(name = "address_id") }
//	)
//	private List<Address> addresses;
	
	public List<Address> getAddress() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public enum AccountRole {
		ADMIN, EMPLOYEE, MANAGER;
//		public static AccountRole toEnum(String name) {
//			for (AccountRole item : AccountRole.values()) {
//				if (item.toString().equals(name))
//					return item;
//			}
//			return null;
//		}
	}
	
	@Override
	public String toString() {
		return "[id = " + id + "; username = " + username + "; role = " + role + "]";
	}
}
