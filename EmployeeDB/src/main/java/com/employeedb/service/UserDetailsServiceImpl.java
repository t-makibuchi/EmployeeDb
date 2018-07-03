package com.employeedb.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employeedb.dao.UserDao;
import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeForm;
import com.employeedb.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeRepository repository;
	
	@Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        Employee employee = repository.findByUserNameIsAndDelFlgEquals(userName, 0L);
        if (employee == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
        }

        return new UserDao(employee);
    }
}
