package com.employeedb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id", nullable = false)
	private Integer employeeId;
	
	@Column(name = "family_name", nullable = false)
	private String familyName;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "department", nullable = false)
	private String department;
	
//	public Integer getEmployeeId() {
//		return employeeId;
//	}
//	
//	public String getFamilyName() {
//		return familyName;
//	}
		
}
