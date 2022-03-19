package com.vti.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
	
	@Override
	public List<Account> getListAccountsWithSearch(String search) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			
			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.ilike("username", "%" + search + "%"));
			
			return criteria.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public Account getAccountById(int id) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			Account account = session.get(Account.class, id);
			//System.out.println(query.list());
			return account;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Account getAccountByUsername(String username) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			Query query = session.createQuery("FROM Account WHERE username = :parameterUsername");
			query.setParameter("parameterUsername", username);
			Account account = (Account) query.uniqueResult();
			//System.out.println(query.list());
			return account;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public void addNewAccount(Account ac) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			session.save(ac);
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
}
