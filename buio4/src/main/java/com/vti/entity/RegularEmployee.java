package com.vti.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
//@DiscriminatorValue("regularemployee")
@Table(name = "Regular_Employee")
@PrimaryKeyJoinColumn(name = "id") //Type Joined
public class RegularEmployee extends Employee{
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "bonus")
	private int bonus;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
