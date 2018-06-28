package com.employeedb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.employeedb.entity.Employee;
import com.employeedb.service.EmployeeService;

@Controller
@RequestMapping("/employeeList")
public class EmployeeListController {
	
	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		
		mav.setViewName("employeeList");
		List<Employee> employeeList = employeeService.findAll();
		mav.addObject("employeeList", employeeList);
		
		return mav;
	}
	
}
