package com.employeedb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeDeleteForm;
import com.employeedb.form.EmployeeEditForm;
import com.employeedb.form.EmployeeInputForm;
import com.employeedb.repository.EmployeeRepository;
import com.employeedb.security.CipherManager;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Employee> findAll() {
		List<Employee> employeeList = repository.findByDelFlgEquals(0L);
		return decryptEmployeeList(employeeList);
	}
	
	public Employee findById(Long seqNo) {
		Employee employee = repository.findById(seqNo).orElse(null);
		if(employee != null) {
			if(employee.getDelFlg() == 1) {
				employee = null;
			}
		}	
		return decryptEmployee(employee);
	}
	
	public Boolean existsById(Long seqNo) {
		Boolean exists = repository.existsById(seqNo);
		return exists;
	};
	
	public void create(EmployeeInputForm EmployeeInputForm) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(EmployeeInputForm, employee);
		employee.setDelFlg(0L);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		repository.save(encryptEmployee(employee));
	}
	
	public void edit(EmployeeEditForm employeeEditForm) {
		Employee tmpEmployee = findById(employeeEditForm.getSeqNo());
		Employee employee = new Employee();
		if(tmpEmployee != null && tmpEmployee.getDelFlg() == 0) {
			BeanUtils.copyProperties(tmpEmployee, employee);
			BeanUtils.copyProperties(employeeEditForm, employee);
			employee.setDelFlg(0L);
			if(employeeEditForm.getPassword() != null && !employeeEditForm.getPassword().isEmpty()) {
				employee.setPassword(passwordEncoder.encode(employee.getPassword()));
			} else {
				employee.setPassword(tmpEmployee.getPassword());
			}
			if(employeeEditForm.getRole() == null) {
				employee.setRole(tmpEmployee.getRole());
			}
			repository.save(encryptEmployee(employee));
		}	
	}
	
	public void delete(EmployeeDeleteForm employeeDeleteForm) {
		Employee employee = repository.findById(employeeDeleteForm.getSeqNo()).orElse(new Employee());
		employee.setDelFlg(1L);
		repository.save(employee);

	}
	
	public Employee encryptEmployee(Employee employee) {
		employee.setFamilyName(CipherManager.encrypt(employee.getFamilyName()));
		employee.setGivenName(CipherManager.encrypt(employee.getGivenName()));
		return employee;
	}
	
	public Employee decryptEmployee(Employee employee) {
		employee.setFamilyName(CipherManager.decrypt(employee.getFamilyName()));
		employee.setGivenName(CipherManager.decrypt(employee.getGivenName()));
		return employee;
	}
	
	public List<Employee> encryptEmployeeList(List<Employee> employeeList) {
		return employeeList.stream()
				.map(this :: encryptEmployee).collect(Collectors.toList());
	};
	
	public List<Employee> decryptEmployeeList(List<Employee> employeeList) {
		return employeeList.stream()
				.map(this :: decryptEmployee).collect(Collectors.toList());
	};
}
