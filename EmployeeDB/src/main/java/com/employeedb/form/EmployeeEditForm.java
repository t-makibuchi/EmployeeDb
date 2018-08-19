package com.employeedb.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeEditForm {
	private Long seqNo;
	
	private String userName;
	
	private String password;
	
	private String role;
	
	@NotBlank(message="{NotBlank}")
	private String familyName;

	@NotBlank(message="{NotBlank}")
	private String givenName;
}
