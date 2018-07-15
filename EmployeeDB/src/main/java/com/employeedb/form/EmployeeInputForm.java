package com.employeedb.form;

import lombok.Data;

@Data
public class EmployeeInputForm {
	private Long seqNo;
	
	private String username;
	
	private String password;
	
	private String role;
	
	private String familyName;

	private String givenName;
}
