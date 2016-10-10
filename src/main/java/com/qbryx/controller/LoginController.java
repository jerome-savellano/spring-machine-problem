package com.qbryx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController{
	
	@RequestMapping("/initial")
	public ModelAndView trasdasday(){
		
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("name", "Jerome");
		
		return mv;
	}
	
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public void processLogin(@RequestParam("username") String username, 
			@RequestParam("password") String password){
		
		System.out.println(username + " " + password);
	}
}
