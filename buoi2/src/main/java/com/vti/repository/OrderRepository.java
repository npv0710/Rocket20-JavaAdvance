package com.vti.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vti.entity.Article;
import com.vti.entity.Order;
import com.vti.utils.HibernateUtils;

public class OrderRepository {
private HibernateUtils hibernateUtils;
	
	public OrderRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	public void createOrder(Order order) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtils.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(order);
			
			transaction.commit();
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
}
