package com.vti.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
public class Department{
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "total_member", nullable = false)
	private int totalMember;
	
	@Column(name = "type", columnDefinition = "ENUM('Dev', 'Test', 'Scrum_Master', 'PM')")
	@Enumerated(EnumType.STRING)
	private DepartmentType type;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	@OneToMany(mappedBy = "department")
	private List<Account> accounts;
	
	public enum DepartmentType {
		Dev, Test, Scrumn_Master, PM;
		
		public static DepartmentType toEnum(String type) {
			for(DepartmentType item : DepartmentType.values()) {
				if (item.toString().equals(type)) return item;
			}
			return null;
		}
	}
}
