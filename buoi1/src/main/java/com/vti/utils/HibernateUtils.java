package com.vti.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entity.Article;

public class HibernateUtils {
	private static HibernateUtils instance;
	
	private Configuration configuration;
	
	private SessionFactory sessionFactory;
	
	public static HibernateUtils getInstance() {
		if(instance == null) {
			instance = new HibernateUtils();
		}
		return instance;
	}
	
	private HibernateUtils() {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		configuration.addAnnotatedClass(Article.class);
	}
	
	private SessionFactory buildSessionFactory() {
		if (sessionFactory == null || sessionFactory.isClosed()) {
			ServiceRegistry serviceRegistry = 
					new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}
	
	public Session openSession() {
		buildSessionFactory();
		return sessionFactory.openSession();
	}
	
	public void closeSessionFactory() {
		if (sessionFactory != null && sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}
	
}
