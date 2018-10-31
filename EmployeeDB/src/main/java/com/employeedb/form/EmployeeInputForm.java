package com.employeedb.form;


import javax.validation.constraints.AssertTrue;
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
	
	@AssertTrue(message="Password does not match the confirm password.")
	public boolean isConfirmPasswordMatched() {
		if(confirmPassword == null || confirmPassword.isEmpty()) {
			return true;
		}
		return confirmPassword.equals(password);
	}
	
}
