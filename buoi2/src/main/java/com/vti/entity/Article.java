package com.vti.entity;

import java.io.Serializable;
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
import org.hibernate.annotations.Type;

@Entity
@Table(name="Article")
public class Article implements Serializable{
	
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
	
//	@Column(name="id")
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;
//	
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;
	
//	@Column(name = "`code`", length = 10)
//	@Id
//	@GenericGenerator(
//		name = "generator-code",
//		strategy = "com.vti.entity.ArticleCodeGenerator"
//	)
//	@GeneratedValue(generator = "generator-code")
//	private String code; 
	
	@Column(name = "title", length = 50, nullable = false)
	private String title;
	
	@Column(name = "type", length = 50, nullable = false)
	private String type;
	
//	@Column(name = "status", nullable = false)
//	//@Enumerated(EnumType.ORDINAL)
//	@Enumerated(EnumType.STRING)
//	private Status status;
	
//	@Column(columnDefinition = "enum('OPEN', 'REVIEW', 'APPROVED', 'REJECTED')")
//	@Enumerated(EnumType.STRING)
//	private Status status;
	
	@Column(name = "status", nullable = false)
	@Convert(converter = ArticleStatusConverter.class)
	private Status status;
	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
//
//	public String getTitle() {
//		return title;
//	}

//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[id = " + id + "; title = " + title + "; type = " + type + "; status = " + status + "]"; 
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
