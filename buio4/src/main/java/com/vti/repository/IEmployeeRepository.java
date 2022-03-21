package com.vti.repository;

import java.util.List;

import com.vti.entity.ContractEmployee;
import com.vti.entity.RegularEmployee;

public interface IEmployeeRepository {
	List<RegularEmployee> getListRegularEmployeees();
	List<ContractEmployee> getListContractEmployeees();
	
	void createRegularEmployee(RegularEmployee reularEmployee);
	List<RegularEmployee> getListRegularEmployeesWithSearch(String search);
}
