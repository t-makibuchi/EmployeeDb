package com.employeedb.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeForm;
import com.employeedb.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll() {
		List<Employee> employeeList = repository.findByDelFlgEquals(0L);
		return employeeList;
	}
	
	public Employee findById(Long employeeId) {
		Employee employee = repository.findById(employeeId).orElse(null);
		if(employee != null) {
			if(employee.getDelFlg() == 1) {
				employee = null;
			}
		}
		return employee;
	}
	
	public void create(EmployeeForm EmployeeForm) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(EmployeeForm, employee);
		employee.setDelFlg(0L);
		repository.save(employee);
	}
	
	public void edit(EmployeeForm EmployeeForm) {
		Employee employee = findById(EmployeeForm.getSeqNo());
		if(employee != null && employee.getDelFlg() == 0) {
			BeanUtils.copyProperties(EmployeeForm, employee);
			employee.setDelFlg(0L);
			repository.save(employee);
		}	
	}
	
	public void delete(EmployeeForm employeeForm) {
		Employee employee = repository.findById(employeeForm.getSeqNo()).orElse(new Employee());
		employee.setDelFlg(1L);
		repository.save(employee);

	}
}
