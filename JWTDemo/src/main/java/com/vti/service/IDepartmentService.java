package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;


public interface IDepartmentService {
	Page<Department> getListDeparments(Pageable pageble, String search);
	
	List<Department> getListDepartments();
	
	void createDepartment(DepartmentDTO dpDTO);
}
