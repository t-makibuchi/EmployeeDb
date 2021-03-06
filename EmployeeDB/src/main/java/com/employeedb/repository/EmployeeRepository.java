package com.employeedb.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeedb.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByDelFlgEquals(Long delFlg);
	
	Employee findByUsernameIsAndDelFlgEquals(String username, Long delFlg);
	
	List<Employee> findByUsername(String username);

}
