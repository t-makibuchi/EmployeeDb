package com.employeedb.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.employeedb.entity.Employee;
import com.employeedb.service.EmployeeService;

public class LoginUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 0L;
	
	private Employee user;
	private Collection<GrantedAuthority> authorities;
	
	protected LoginUserDetails() {}
	
	public LoginUserDetails(Employee employee, Collection<GrantedAuthority> authorities) {
		this.user = employee;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.authorities;
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public Long getSeqNo() {
		return user.getSeqNo();
	}
	
	public String getDisplayname() {
		return CipherManager.decrypt(user.getFamilyName()) + CipherManager.decrypt(user.getGivenName());
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

}
