package com.employeedb.dao;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.employeedb.entity.Employee;

public class UserDao extends User {
	
	private static final long serialVersionUID = 1L;
	
	public Long seqNo;
	
	public String userName;
	
	public String password;
	
	public UserDao(Employee employee) {
		super(employee.getUsername(), employee.getPassword(), true, true, true, true, new ArrayList<GrantedAuthority>());
        userName = employee.getUsername();
        password = employee.getPassword();
        seqNo = employee.getSeqNo();
	}

}
