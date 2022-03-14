package com.vti.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
//@DiscriminatorValue("contractemployee")
@Table(name = "Contract_Employee")
@PrimaryKeyJoinColumn(name = "id")
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
