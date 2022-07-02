package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.vti.entity.Article;
import com.vti.utils.HibernateUtils;

public class ArticleRepository {
	private HibernateUtils hibernateUtils;
	
	public ArticleRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	public void createArticle(Article article) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = hibernateUtils.openSession();
			
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(article);
			
			transaction.commit();
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getListArticles() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			Query<Article> query = session.createQuery("FROM Article");
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public int getCountByType(String type) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Article WHERE type = :typeParameter");
			query.setParameter("typeParameter", type);
			
			return query.uniqueResult().intValue();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
//	public void deleteArticle(int id) {
//		Session session = null;
//		Transaction transaction = null;
//		
//		try {
//			session = hibernateUtils.openSession();
//			
//			transaction = session.getTransaction();
//			transaction.begin();
//			
//			Article article = (Article) session.load(Article.class, id);
//			session.delete(article);
//			
//			transaction.commit();
//		}finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//	}
//	
//	public void updateArticle(int id, String title) {
//		Session session = null;
//		Transaction transaction = null;
//		
//		try {
//			session = hibernateUtils.openSession();
//			
//			transaction = session.getTransaction();
//			transaction.begin();
//			
//			Article article = session.get(Article.class, id);
//			article.setTitle(title);
//			
//			session.save(article);
//			
//			transaction.commit();
//		}finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
