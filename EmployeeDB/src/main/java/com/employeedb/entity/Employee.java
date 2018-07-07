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
public class Employee {
	
	public Employee (String username, String password, String role) {
		setSeqNo(0L);
		setUsername(username);
		setPassword(password);
		setRole(role);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_no", nullable = false, unique = true)
	private Long seqNo;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "family_name", nullable = true)
	private String familyName;
	
	@Column(name = "given_name", nullable = true)
	private String givenName;
	
	@Column(name = "department", nullable = true)
	private String department;
	
	@Column(name = "del_flg", nullable = false)
	private Long delFlg;

		
}
