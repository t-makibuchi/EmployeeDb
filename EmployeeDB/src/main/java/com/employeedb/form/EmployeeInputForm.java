package com.employeedb.form;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeInputForm {
	private Long seqNo;
	
	private String username;
	
	private String password;
	
	private String confirmPassword;
	
	private String role;
	
	@NotBlank(message="{NotBlank}")
	private String familyName;

	@NotBlank(message="{NotBlank}")
	private String givenName;
	

}
