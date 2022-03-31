package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;


public interface IDepartmentService {
	List<Department> getListDepartments();
	
	Page<Department> getListDeparmentsPaging(Pageable pageble, String search, DepartmentFilterForm dpFF);
	
	void createDepartment(DepartmentDTO dpDTO);
}
