package com.vti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

//@Entity
//@Table(name = "Employee")
//Single Table
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "`type`", discriminatorType = DiscriminatorType.STRING)

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy = InheritanceType.JOINED) // Joined

@MappedSuperclass // Mapped super class
public class Employee {
	//@Column(name = "id")
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)//Single Table & Joined
	//@GeneratedValue(strategy = GenerationType.AUTO) //For table_per_class
	//private int id;
	
	//Type suppser class not config primary key Id;
	
	//@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`name`", length = 50, nullable = false)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
