package com.employeedb.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeedb.entity.Employee;
import com.employeedb.form.employeeForm;
import com.employeedb.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll() {
		List<Employee> employeeList = repository.findAll();
		return employeeList;
	}
	
	public Employee findById(Long employeeId) {
		Employee employee = repository.findById(employeeId).orElse(null);
		return employee;
	}
	
	public void create(employeeForm employeeForm) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeForm, employee);
		employee.setDelFlg(0L);
		repository.save(employee);
	}
	
	public void edit(employeeForm employeeForm) {
		Employee employee = findById(employeeForm.getSeqNo());
		if(employee != null && employee.getDelFlg() == 0) {
			BeanUtils.copyProperties(employeeForm, employee);
			employee.setDelFlg(0L);
			repository.save(employee);
		}	
	}
}
