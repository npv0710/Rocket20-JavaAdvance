package com.vti;

import java.util.List;

import com.vti.entity.Article;
import com.vti.entity.Order;
import com.vti.repository.ArticleRepository;
import com.vti.repository.OrderRepository;

public class Program {
	public static void main(String[] args) {
		System.out.println("Buoi2 Java Advance");
		
//		ArticleRepository articleRepository = new ArticleRepository();
//		
//		Article article = new Article("Java Advance", "Backend", Article.Status.OPEN);
//		
//		articleRepository.createArticle(article);
		
//		Article article2 = new Article("Java Advance", "Backend", Article.Status.APPROVED);
//		
//		articleRepository.createArticle(article2);
		
//		List<Article> articles = articleRepository.getAllArticles();
//		
//		for (Article article : articles) {
//			System.out.println(article);
//		}
		
		OrderRepository orderRepository = new OrderRepository();
		
		Order.OrderPK pk = new Order.OrderPK();
		pk.setOrderId(2);
		pk.setProductId(5);
		
		Order order = new Order();
		order.setId(pk);
		order.setTitle("Order 1");
		
		orderRepository.createOrder(order);
	}
}
