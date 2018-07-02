package com.employeedb.form;

import lombok.Data;

@Data
public class EmployeeForm {
	private Long seqNo;
	
	private String userName;
	
	private String password;
	
	private String familyName;

	private String givenName;
}
