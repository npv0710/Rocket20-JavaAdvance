package com.vti.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Department;
import com.vti.entity.Department.DepartmentType;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;



@SuppressWarnings("serial")
@RequiredArgsConstructor
public class CustomSpecificationDepartment implements Specification<Department>{
	
	@NonNull
	private String field;
	
	@NonNull
	private Object value;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}else if (field.equalsIgnoreCase("type")) {
			return criteriaBuilder.equal(root.get("type"), DepartmentType.toEnum(value.toString()));
		}else if (field.equalsIgnoreCase("minDate")) {
			   Expression es = root.<Date>get("createdAt");
			   return criteriaBuilder.greaterThanOrEqualTo(es, (Date)value);
			
		}else if (field.equalsIgnoreCase("maxDate")) {
			   Expression es = root.<Date>get("createdAt");
			   return criteriaBuilder.lessThanOrEqualTo(es, (Date)value);
		}
		
		
		/* Filter by DateTime */
//		else if (field.equalsIgnoreCase("minDate")) {
//			   Expression es = root.<Instant>get("createdAt");
//			   
//			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			   
//			   System.out.println(value.toString().trim());
//			   
//			   LocalDateTime date1 = LocalDateTime.parse(value.toString(), dtf);
//			   
//			   Instant instant1 = date1.toInstant(ZoneOffset.UTC);
//			   
//			   return criteriaBuilder.greaterThanOrEqualTo(es, instant1);
//			
//		}else if (field.equalsIgnoreCase("maxDate")) {
//			   Expression es = root.<Instant>get("createdAt");
//				
//			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				   
//			   System.out.println(value.toString().trim());
//				   
//			   LocalDateTime date2 = LocalDateTime.parse(value.toString(), dtf);
//				   
//			   Instant instant2 = date2.toInstant(ZoneOffset.UTC);
//			   return criteriaBuilder.lessThanOrEqualTo(es, instant2);
//		}
		return null;
	}
}
