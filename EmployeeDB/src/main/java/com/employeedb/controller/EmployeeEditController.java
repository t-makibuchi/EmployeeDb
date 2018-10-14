package com.employeedb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.employeedb.entity.Employee;
import com.employeedb.form.EmployeeEditForm;
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
		mav.addObject("form", new EmployeeEditForm());
		if (employee != null) {
			mav.setViewName("employeeEdit");
			mav.addObject("employee", employee);
		} else {
			mav.setViewName("illegalUrl");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("form") @Validated EmployeeEditForm employeeEditForm,
			BindingResult bindingResult,
			ModelAndView mav
			) {

		ModelAndView res = null;
		if (bindingResult.hasErrors()) {
			mav.setViewName("employeeEdit");
			mav.addObject("form", employeeEditForm);
			Employee employee = employeeService.findById(employeeEditForm.getSeqNo());
			mav.addObject("employee", employee);
			res = mav;
		} else {
			if (employeeService.existsById(employeeEditForm.getSeqNo())) {
				employeeService.edit(employeeEditForm);
				res = new ModelAndView("redirect:../employeeList");
			} else {
				res = new ModelAndView("illegalUrl");
			}
		}		
		return res;
	}
	
}
