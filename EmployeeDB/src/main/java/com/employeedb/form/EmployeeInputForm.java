package com.employeedb.form;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeInputForm {
	private Long seqNo;
	
	private String username;
	
	private String password;
	
	private String role;
	
	@NotBlank
	private String familyName;

	@NotBlank
	private String givenName;
}
