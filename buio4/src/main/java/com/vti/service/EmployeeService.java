package com.vti.service;

import java.util.List;

import com.vti.entity.ContractEmployee;
import com.vti.entity.RegularEmployee;
import com.vti.repository.EmployeeRepository;
import com.vti.repository.IEmployeeRepository;

public class EmployeeService implements IEmployeeService{
	private IEmployeeRepository employeeRepository;
	public EmployeeService () {
		employeeRepository = new EmployeeRepository();
	}
	
	@Override
	public List<RegularEmployee> getListRegularEmployee() {
		return employeeRepository.getListRegularEmployeees();
	}

	@Override
	public List<ContractEmployee> getListContractEmployee() {
		return employeeRepository.getListContractEmployeees();
	}

	@Override
	public void createRegularEmployee(RegularEmployee regularEmployee) {
		employeeRepository.createRegularEmployee(regularEmployee);
	}

}
