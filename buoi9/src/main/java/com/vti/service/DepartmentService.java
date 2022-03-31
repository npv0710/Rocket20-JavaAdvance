package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.DepartmentSpecification;



@Service
public class DepartmentService implements IDepartmentService{
	@Autowired
	private IDepartmentRepository dpRepository;
	
	@Override
	public Page<Department> getListDeparmentsPaging(Pageable pageble, String search, DepartmentFilterForm dpFF) {
		Specification<Department> where = DepartmentSpecification.buildWhere(search, dpFF);
		return dpRepository.findAll(where, pageble);
	}
	
	@Override
	public List<Department> getListDepartments() {
		return dpRepository.findAll();
	}

	@Override
	public void createDepartment(DepartmentDTO dpDTO) {
	}

	

}
