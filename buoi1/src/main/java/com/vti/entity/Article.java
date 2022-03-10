package com.vti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Article")
public class Article {
	
	public Article() {
		
	}
	
	public Article(String title, String type, Status status) {
		this.title = title;
		this.type = type;
		this.status = status;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}

	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title", length = 50, nullable = false)
	private String title;
	
	@Column(name = "type", length = 50, nullable = false)
	private String type;
	
//	@Column(name = "`code`", length = 10)
//	@Id
//	@GenericGenerator(
//		name = "generator-code",
//		strategy = "com.vti.entity.ArticleCodeGenerator"
//	)
//	@GeneratedValue(generator = "generator-code")
//	private String code;
	
//	@Column(name = "status", nullable = false)
//	@Enumerated(EnumType.ORDINAL)
//	private Status status;
	
	@Column(columnDefinition = "enum('OPEN', 'REVIEW', 'APPROVED', 'REJECTED')")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Override
	public String toString() {
		return "[id = " + id + "; title = " + title + "; type = " + type + "; status: " + status + "]"; 
	}
	
	public enum Status {
		OPEN, REVIEW, APPROVED, REJECTED;
	}
}
