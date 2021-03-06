package com.employeedb.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeedb.entity.Employee;
import com.employeedb.repository.EmployeeRepository;
import com.employeedb.security.LoginUserDetails;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

		if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }
		
        Employee employee = repository.findByUsernameIsAndDelFlgEquals(username, 0L);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
       
        LoginUserDetails loginUserDetails = new LoginUserDetails(employee, getAuthorities(employee)); 

        return loginUserDetails;
    }
	
	private Collection<GrantedAuthority> getAuthorities(Employee employee){
		
		String role = employee.getRole();

		switch (role) {
			case "admin":
				return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
			case "user":
			default :
				return AuthorityUtils.createAuthorityList("ROLE_USER");
		}
		
	}
	
	@Transactional
    public void registerAdmin(String username, String password) {
        Employee employee = new Employee(username, passwordEncoder.encode(password),"admin");
        repository.save(employee);
    }

    @Transactional
    public void registerUser(String username, String password) {
    	Employee employee = new Employee(username, passwordEncoder.encode(password),"user");
        repository.save(employee);
    }
	
}
