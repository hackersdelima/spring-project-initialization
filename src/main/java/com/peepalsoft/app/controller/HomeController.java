package com.peepalsoft.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	
	//set url value to enter login page
	@RequestMapping(value = "/login-page", method = RequestMethod.GET)
	public ModelAndView login(String error, String logout) {
		
		//calling jsp file
		ModelAndView model=new ModelAndView("login");
		//message to display on login attempt
		String msg="";
		if(error!=null) {
			msg="Invalid Credentials!";
		}
		else if(logout!=null) {
			msg="Logout Successful!";
		}
		model.addObject("msg", msg);
		return model;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView model=new ModelAndView("dashboard");
		model.addObject("pagetitle","DASHBOARD");
		return model;
	}
	
	
}
