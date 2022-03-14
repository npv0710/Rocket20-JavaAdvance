package com.vti.service;

import java.util.List;

import com.vti.entity.ContractEmployee;
import com.vti.entity.RegularEmployee;

public interface IEmployeeService {
	List<RegularEmployee> getListRegularEmployee();
	List<ContractEmployee> getListContractEmployee();
	void createRegularEmployee(RegularEmployee regularEmployee);
}
