package com.employeedb.form;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeInputForm {
	private Long seqNo;
	
	private String username;
	
	private String password;
	
	private String role;
	
	@NotBlank(message="family name is required")
	private String familyName;

	@NotBlank(message="given name is required")
	private String givenName;
}
