package com.qbryx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qbryx.domain.User;
import com.qbryx.service.CustomerService;
import com.qbryx.service.ProductService;
import com.qbryx.service.UserService;

@Controller
public class LoginController{
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="customerService")
	private CustomerService customerService;
		
	@RequestMapping("/initial")
	public String trasdasday(HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("user");
		
		return (user != null) ? "redirect:/processLogin" : "login";
	}
	
	@RequestMapping(value="/processLogin", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("user") User user,
			HttpServletRequest request, Model model){
		
		boolean userValid = userService.authenticate(user.getUsername(), user.getPassword());
		
		if(userValid){
			
			User validUser = userService.getUser(user.getUsername());
			
			request.getSession().setAttribute("categories", productService.getCategories());
			request.getSession().setAttribute("user", validUser);
			
			return "redirect:/" + validUser.getUserType().getUserType();
		}
			
		model.addAttribute("username", user.getUsername());
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
}
