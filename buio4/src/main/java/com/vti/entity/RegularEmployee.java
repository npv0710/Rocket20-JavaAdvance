package com.vti.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

//@Entity

//Single Table
//@DiscriminatorValue("regularemployee")
//

//Single Table
//@AttributeOverrides({
//	@AttributeOverride(name = "id", column = @Column(name = "id")),
//	@AttributeOverride(name = "name", column = @Column(name = "name"))
//})
//

//@PrimaryKeyJoinColumn(name = "id") //Type Joined
@Entity(name = "Regular_Employee")

//@AttributeOverrides({
//	@AttributeOverride(name = "id", column = @Column(name = "id")),
//	@AttributeOverride(name = "name", column = @Column(name = "name"))
//})
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
