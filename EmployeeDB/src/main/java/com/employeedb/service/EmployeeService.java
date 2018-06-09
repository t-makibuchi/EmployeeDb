package com.employeedb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeedb.entity.Employee;
import com.employeedb.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll() {
		List<Employee> employeeList = repository.findAll();
		return employeeList;
	}
	
}
