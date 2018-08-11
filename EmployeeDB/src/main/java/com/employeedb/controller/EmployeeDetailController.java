package com.employeedb.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeDeleteForm;
import com.employeedb.form.EmployeeInputForm;
import com.employeedb.service.EmployeeService;

@Controller
@RequestMapping("/employeeDetail")
public class EmployeeDetailController {
	
	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @RequestParam("seqNo") String seqNo) {
		
		Employee employee = employeeService.findById(Long.parseLong(seqNo));
		if (employee != null) {
			mav.setViewName("employeeDetail");
			mav.addObject("employee", employee);
		} else {
			mav.setViewName("illegalUrl");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(EmployeeDeleteForm employeeDeleteForm) {

		employeeService.delete(employeeDeleteForm);
		return "redirect:../employeeList";

	}
	
}
