package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Table(name = "Account")
@Data
public class Account{
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", length = 50, nullable = false)
	private String userName;
	
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
	
	@ManyToMany
	@JoinTable(name = "AccountDepartment",
		joinColumns = { @JoinColumn(name = "account_id")},
		inverseJoinColumns = { @JoinColumn(name = "department_id")}
	)
	private List<Department> departments;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public AccountStatus status;
	
	public enum AccountStatus {
		NOT_ACTIVE, ACTIVE;
	}
	
	public enum AccountRole {
		ADMIN, EMPLOYEE, MANAGER;
		public static AccountRole toEnum(String name) {
			for (AccountRole item : AccountRole.values()) {
				if (item.toString().equals(name))
					return item;
			}
			return null;
		}
	}
}
