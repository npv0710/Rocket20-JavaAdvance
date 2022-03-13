package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;

public class AccountRepository implements IAccountRepository{
	@SuppressWarnings("unused")
	private HibernateUtils hibernateUtils;
	
	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getListAccounts() {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			Query<Account> query = session.createQuery("FROM Account");
			//System.out.println(query.list());
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
}
