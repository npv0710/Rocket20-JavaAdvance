package com.vti;

import java.util.List;

import com.vti.entity.Article;
import com.vti.repository.ArticleRepository;

public class Program {
	public static void main(String[] args) {
		System.out.println("Java advance buoi2");
		
		ArticleRepository articleRepository = new ArticleRepository();
		
//		Article article = new Article("Vue.js", "Frontend", Article.Status.APPROVED);
//		
//		articleRepository.createArticle(article);
		
//		Article article2 = new Article("Java Advance", "Backend", Article.Status.APPROVED);
//		
//		articleRepository.createArticle(article2);
		
//		List<Article> articles = articleRepository.getListArticles();
//		
//		for (Article item : articles) {
//			System.out.println(item);
//		}
		
		//articleRepository.deleteArticle(1);
		
		articleRepository.updateArticle(2, "HTML && CSS");
		
	}
}
