package com.vti.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AccountDTO {
	private int id;
	
	@NotBlank(message = "Username can not be null")
	private String username;
	
	@Length(min = 6, max = 20, message = "Password's length is max 20 and min is 6 characters")
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@Email(message = "Email invalid")
	private String email;
	
	@Pattern(regexp = "Admin|Employee|Manager",
			message = "The type must be Admin, Employee or Manager"
	)
	private String role;
	
	private String status;
	
	@PositiveOrZero(message = "Department Id must be greater than or equal 0")
	@Positive
	private int departmentId;
	
	private String departmentName;
	
}
