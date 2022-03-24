package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Address;
import com.vti.utils.HibernateUtils;

public class AddressRepository implements IAddressRepository{
	
	private HibernateUtils hibernateUtils;
	
	public AddressRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getListAddresses() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			Query<Address> query = session.createQuery("FROM Address");
			
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
