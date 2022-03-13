package com.vti.entity;

import javax.persistence.AttributeConverter;

import com.vti.entity.Article.Status;

public class ArticleStatusConverter implements AttributeConverter<Article.Status, String>{

	public String convertToDatabaseColumn(Status attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getStatus();
	}

	public Status convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return Article.Status.toEnum(dbData);
	}
	
}
