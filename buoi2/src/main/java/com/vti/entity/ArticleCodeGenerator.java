package com.vti.entity;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.vti.repository.ArticleRepository;

public class ArticleCodeGenerator implements IdentifierGenerator{
	
	private ArticleRepository articleRepository;
	
	public ArticleCodeGenerator() {
		articleRepository = new ArticleRepository();
	}
	
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Article article = (Article) object;
		String type = article.getType();
		
		int count = articleRepository.getCountByType(type);
		
		return type.charAt(0) + "-" + (count + 1); // F-3
	}
	
	
	
}
