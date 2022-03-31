package com.vti.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentFilterForm {
	private String type;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date minDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxDate;
	
	/* Filter by DateTime */
//	private String minDate;
//	
//	private String maxDate;
}
