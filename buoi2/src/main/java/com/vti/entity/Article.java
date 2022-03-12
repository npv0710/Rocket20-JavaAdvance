package com.vti.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.vti.entity.Article.Status;

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
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@GenericGenerator(
//		name = "article-sequence-generator",
//		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//		parameters = {
//			@Parameter(name = "sequence_name", value = "article_sequence"),
//			@Parameter(name = "initial_value", value = "10"),
//			@Parameter(name = "increment_size", value = "1")
//		}
//	)
//	@GeneratedValue(generator = "article-sequence-generator")
//	private int id;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;
	
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
	
	@Column(name = "`code`", length = 10)
	private String code;
	
//	@Column(name = "status", nullable = false)
//	@Enumerated(EnumType.ORDINAL)
//	private Status status;
	
	//@Column(columnDefinition = "enum('OPEN', 'REVEW', 'APPROVED', 'REJECTED')")
	@Column(name ="status", nullable = false)
	@Convert(converter = ArticleStatusConverter.class)
	//@Enumerated(EnumType.STRING)
	private Status status;
	
	@Override
	public String toString() {
		return "[id = " + id + "; title = " + title + "; type = " + type + "; status: " + status + "]"; 
	}
	
	public enum Status {
		OPEN("O"), REVIEW("REV"), APPROVED("A"), REJECTED("REJ");
		
		private String status;
		
		private Status(String status) {
			this.status = status;
		}
		
		public String getStatus() {
			return status;
		}
		
		public static Status toEnum(String sqlStatus) {
			for (Status item : Status.values()) {
				if (item.getStatus().equals(sqlStatus)) {
					return item;
				}
			}
			return null;
		}
	}
}
