package com.employeedb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeForm;
import com.employeedb.service.EmployeeService;

@Controller
@RequestMapping("/employeeInput")
public class EmployeeInputController {
	
	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		
		return "employeeInput";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(EmployeeForm employeeForm) {

		employeeService.create(employeeForm);
		return "redirect:../employeeList";
	}
	
}
