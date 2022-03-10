package com.vti;

import java.util.List;

import com.vti.entity.Article;
import com.vti.repository.ArticleRepository;

public class Program {
	public static void main(String[] args) {
		System.out.println("Demo hibernate java advance");
		
		ArticleRepository articleRepository = new ArticleRepository();
		
		//articleRepository.updateArticleType(1, "Backend");
		
		//System.out.println(articleRepository.getArticleById(2));
		
		//System.out.println(articleRepository.getCountByType("Backend"));
		
		Article article = new Article("Java Advance", "Backend", Article.Status.APPROVED);
		articleRepository.createArticle(article);
		
//		Article article2 = new Article("ReactJS", "Frontend", Article.Status.OPEN);
//		articleRepository.createArticle(article2);
		
//		List<Article> articles = articleRepository.getAllArticles();
//		
//		for (Article article : articles) {
//			System.out.println(article);
//		}
	}
}
