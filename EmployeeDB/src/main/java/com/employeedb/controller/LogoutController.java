package com.employeedb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeInputForm;
import com.employeedb.service.EmployeeService;

@Controller
@RequestMapping("")
public class LogoutController {
	
	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("iserror",false);
		return "login";
	}
	
}
