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
//@DiscriminatorValue("contractemployee")
//

//Single Table
//@AttributeOverrides({
//	@AttributeOverride(name = "id", column = @Column(name = "id")),
//	@AttributeOverride(name = "name", column = @Column(name = "name"))
//})

//@PrimaryKeyJoinColumn(name = "id")

@Entity(name = "Contract_Employee")

//@AttributeOverrides({
//	@AttributeOverride(name = "id", column = @Column(name = "id")),
//	@AttributeOverride(name = "name", column = @Column(name = "name"))
//})
public class ContractEmployee extends Employee{
	
	@Column(name = "pay_per_hour")
	private int payPerHour;
	
	@Column(name = "contract_duration", length = 50)
	private String contractDuration;

	public int getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(int payPerHour) {
		this.payPerHour = payPerHour;
	}

	public String getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(String contractDuration) {
		this.contractDuration = contractDuration;
	}
	
}
