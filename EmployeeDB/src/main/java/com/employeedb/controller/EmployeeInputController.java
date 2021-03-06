package com.employeedb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.employeedb.exception.DuplicateUsernameException;
import com.employeedb.form.EmployeeInputForm;
import com.employeedb.service.EmployeeService;

@Controller
@RequestMapping("/employeeInput")
public class EmployeeInputController {
	
	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("form", new EmployeeInputForm());
		mav.setViewName("employeeInput");
		return mav;
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("form") @Validated EmployeeInputForm employeeInputForm, 
			BindingResult bindingResult,
			ModelAndView mav) {
		
		ModelAndView res = null;
		if (bindingResult.hasErrors()) {
			mav.setViewName("employeeInput");
			mav.addObject("form", employeeInputForm);
			res = mav;
		} else {
			
			// ユーザ名のDB突合せチェック
			try {
				employeeService.checkUsernameDuplication(employeeInputForm.getUsername());
			} catch (DuplicateUsernameException e) {
				String[] params = {"sername"};
			    bindingResult.rejectValue(
			    		"username", "DatabaseDuplication", params, "");
				mav.setViewName("employeeInput");
				mav.addObject("form", employeeInputForm);
				return mav;
			}
			
			employeeService.create(employeeInputForm);
			res = new ModelAndView("redirect:../employeeList");
			return res;
		}
		return res;
	}
	
}
