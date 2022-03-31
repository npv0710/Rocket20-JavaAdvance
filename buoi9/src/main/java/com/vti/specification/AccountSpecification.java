package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;

public class AccountSpecification {
	public static Specification<Account> buildWhere(String search, AccountFilterForm acFF){
		Specification<Account> where = null;
		
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecificationAccount userName = new CustomSpecificationAccount("userName", search);
			CustomSpecificationAccount firstName = new CustomSpecificationAccount("firstName", search);
			CustomSpecificationAccount lastName = new CustomSpecificationAccount("lastName", search);
			
			where = Specification.where(userName).or(firstName).or(lastName);
		}
		
		if (acFF != null && !StringUtils.isEmpty(acFF.getRole())) {
			CustomSpecificationAccount accountRole = new CustomSpecificationAccount("role", acFF.getRole());
			if (where == null) where = accountRole;
			else where = where.and(accountRole);
		}
		
		if (acFF != null && acFF.getDepartmentId() != 0) {
			CustomSpecificationAccount accountDepartmentId = new CustomSpecificationAccount("departmentId", acFF.getDepartmentId());
			if (where == null) where = accountDepartmentId;
			else where = where.and(accountDepartmentId);
		}
		System.out.println(where);
		return where;
	}
}
