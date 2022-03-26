package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "/api/departments")
public class DepartmentController {
	
	@Autowired
	private IDepartmentService dpService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<DepartmentDTO> getListDepartments() {
		List<Department> departments = dpService.getListDepartments();
		List<DepartmentDTO> listDpDTO = modelMapper.map(departments, new TypeToken< List<DepartmentDTO> >() {}.getType());
		return listDpDTO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/paging")
	public Page<DepartmentDTO> getListDepartmentsPagin(Pageable pageable, @RequestParam(name = "search", required = false) String serach) {
		Page<Department> pgDepartment = dpService.getListDeparments(pageable, serach);
		List<DepartmentDTO> listDepartmentDTO = modelMapper.map(pgDepartment.getContent(), new TypeToken< List<DepartmentDTO> >() {}.getType());
		Page<DepartmentDTO> pgDepartmentDTO = new PageImpl(listDepartmentDTO, pageable, pgDepartment.getTotalElements());
		return pgDepartmentDTO;
	}
	
	
	
	
	
	
	
	
	
}
