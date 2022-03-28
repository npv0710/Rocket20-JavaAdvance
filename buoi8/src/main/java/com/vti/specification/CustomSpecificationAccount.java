package com.vti.specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Account;

import lombok.Data;
import lombok.NonNull;

@SuppressWarnings("serial")
@Data
public class CustomSpecificationAccount implements Specification<Account>{
	@NonNull
	private String field;
	
	@NonNull
	private Object value;
	
	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (field.equalsIgnoreCase("userName")) {
			return criteriaBuilder.like(root.get("userName"), "%" + value.toString() + "%");
		}else if (field.equalsIgnoreCase("firstName")) {
			return criteriaBuilder.like(root.get("firstName"), "%" + value.toString() + "%");
		}else if (field.equalsIgnoreCase("lastName")) {
			return criteriaBuilder.like(root.get("lastName"), "%" + value.toString() + "%");
		}else if (field.equalsIgnoreCase("role")) {
			return criteriaBuilder.equal(root.get("role"), Account.AccountRole.toEnum(value.toString()));
		}else if (field.equalsIgnoreCase("departmentId")) {
			return criteriaBuilder.equal(root.get("department").get("id"), value);
		}
		return null;
	}
}
