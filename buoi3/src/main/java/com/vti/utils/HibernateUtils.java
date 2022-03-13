package com.vti.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entity.Account;
import com.vti.entity.Address;


public class HibernateUtils {
	private static HibernateUtils instance;
	
	public static HibernateUtils getInstance() {
		if(instance == null) {
			instance = new HibernateUtils();
		}
		return instance;
	}
	
	private HibernateUtils() {
		//load configuration
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		//add entity
		configuration.addAnnotatedClass(Account.class);
		configuration.addAnnotatedClass(Address.class);
	}
	
	private Configuration configuration;
	private SessionFactory sessionFactory;
	
	
	private SessionFactory buildSessinFactory() {
		if (sessionFactory == null || sessionFactory.isClosed()) {
			ServiceRegistry serviceRegistry = 
			new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		return sessionFactory;
	}
	
	public Session openSession() {
		buildSessinFactory();
		return sessionFactory.openSession();
	}
	
	public void colseSessionFactory() {
		if (sessionFactory != null && sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}
	
}
