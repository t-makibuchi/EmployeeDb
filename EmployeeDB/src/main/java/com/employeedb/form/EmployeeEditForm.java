package com.employeedb.form;

import lombok.Data;

@Data
public class EmployeeEditForm {
	private Long seqNo;
	
	private String userName;
	
	private String password;
	
	private String role;
	
	private String familyName;

	private String givenName;
}
