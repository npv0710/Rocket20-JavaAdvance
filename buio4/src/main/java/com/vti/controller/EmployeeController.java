package com.vti.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.ContractEmployee;
import com.vti.entity.RegularEmployee;
import com.vti.service.EmployeeService;

@RestController
@RequestMapping(value = "api/employeees")
public class EmployeeController {
	private EmployeeService employeeService;
	
	public EmployeeController() {
		employeeService = new EmployeeService();
	}
	
	@GetMapping("/regular")
	public List<RegularEmployee> getListRegularEmployee() {
		return employeeService.getListRegularEmployee();
	}
	
	@GetMapping("/contract")
	public List<ContractEmployee> getListContractEmployee() {
		return employeeService.getListContractEmployee();
	}
	
	@PostMapping("/regular")
	public ResponseEntity<?> createRegularEmployee(@RequestBody RegularEmployee rgEmployee) {
		System.out.println(rgEmployee);
		employeeService.createRegularEmployee(rgEmployee);
		return ResponseEntity.status(HttpStatus.OK).body("Create successfully");
	}
	
	@GetMapping("/regular/search")
	public ResponseEntity<?> getListRegularEmployeesWithSearch(@RequestParam(name="name") String name) {
		List<RegularEmployee> regularEmployeees = employeeService.getListRegularEmployeesWithSearch(name);
		
		return ResponseEntity.status(HttpStatus.OK).body(regularEmployeees);
	}
	
}
