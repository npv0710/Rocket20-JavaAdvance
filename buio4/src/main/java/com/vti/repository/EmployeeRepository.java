package com.vti.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.vti.entity.ContractEmployee;
import com.vti.entity.RegularEmployee;
import com.vti.utils.HibernateUtils;

public class EmployeeRepository implements IEmployeeRepository{
	
	private HibernateUtils hibernateUtils;
	
	public EmployeeRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	@Override
	public List<RegularEmployee> getListRegularEmployeees() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			Query<RegularEmployee> query = session.createQuery("FROM RegularEmployee");
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<ContractEmployee> getListContractEmployeees() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			Query<ContractEmployee> query = session.createQuery("FROM ContractEmployee");
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void createRegularEmployee(RegularEmployee regularEmployee) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			session.save(regularEmployee);
			
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<RegularEmployee> getListRegularEmployeesWithSearch(String search) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			
			Criteria criteria = session.createCriteria(RegularEmployee.class);
			//criteria.add(Restrictions.ilike("name", "%" + search + "%"));
			//criteria.add(Restrictions.gt("salary", Integer.parseInt(search)));
			
			Criterion gtSalary = Restrictions.gt("salary", 2500);
			Criterion likeName = Restrictions.ilike("name", "%" + search + "%");
			
			//LogicalExpression andExp = Restrictions.and(gtSalary, likeName);
			LogicalExpression orExp = Restrictions.or(gtSalary, likeName);
			
			//criteria.add(andExp);
			criteria.add(orExp);
			
			return criteria.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
