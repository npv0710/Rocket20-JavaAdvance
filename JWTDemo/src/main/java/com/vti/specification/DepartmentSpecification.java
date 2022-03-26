package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Department;

public class DepartmentSpecification {
	public static Specification<Department> buildWhere(String search) {
		Specification<Department> where = null;
		
		if (!StringUtils.isEmpty(search)) {
			CustomSpecificationDepartment name = new CustomSpecificationDepartment("name", search.trim());
			where = Specification.where(name);
		}
		
		return where;
	}
}
