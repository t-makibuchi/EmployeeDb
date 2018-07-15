package com.employeedb.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeDeleteForm;
import com.employeedb.form.EmployeeEditForm;
import com.employeedb.form.EmployeeInputForm;
import com.employeedb.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	public void create(EmployeeInputForm EmployeeInputForm) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(EmployeeInputForm, employee);
		employee.setDelFlg(0L);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		repository.save(employee);
	}
	
	public void edit(EmployeeEditForm EmployeeEditForm) {
		Employee employee = findById(EmployeeEditForm.getSeqNo());
		if(employee != null && employee.getDelFlg() == 0) {
			BeanUtils.copyProperties(EmployeeEditForm, employee);
			employee.setDelFlg(0L);
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
			repository.save(employee);
		}	
	}
	
	public void delete(EmployeeDeleteForm employeeDeleteForm) {
		Employee employee = repository.findById(employeeDeleteForm.getSeqNo()).orElse(new Employee());
		employee.setDelFlg(1L);
		repository.save(employee);

	}
}
