package com.employeedb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeListController {

	@RequestMapping("/")
	public String index() {
		return "employeeList";
	}
	
}
