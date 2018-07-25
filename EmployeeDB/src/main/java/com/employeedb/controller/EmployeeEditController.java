package com.employeedb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeEditForm;
import com.employeedb.form.EmployeeInputForm;
import com.employeedb.service.EmployeeService;

@Controller
@RequestMapping("/employeeEdit")
public class EmployeeEditController {
	
	@Autowired
	public EmployeeService employeeService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or (principal.seqNo == new Long(#seqNo))")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @RequestParam("seqNo") String seqNo) {
		
		Employee employee = employeeService.findById(Long.parseLong(seqNo));
		if (employee != null) {
			mav.setViewName("employeeEdit");
			mav.addObject("employee", employee);
		} else {
			mav.setViewName("illegalUrl");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(EmployeeEditForm employeeEditForm) {

		Employee employee = employeeService.findById(employeeEditForm.getSeqNo());
		if (employee != null) {
			employeeService.edit(employeeEditForm);
			return "redirect:../employeeList";
		}
		
		return "illegalUrl";
	}
	
}
