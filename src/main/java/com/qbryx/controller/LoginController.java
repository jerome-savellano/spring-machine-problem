package com.qbryx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qbryx.domain.User;
import com.qbryx.exception.UserNotFoundException;
import com.qbryx.service.CustomerService;
import com.qbryx.service.ProductService;
import com.qbryx.service.UserService;
import com.qbryx.util.UserUtil;

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
		
		User user = UserUtil.getUser(request);
		
		return (user != null) ? "redirect:/" + user.getUserType().getType() : "login";
	}
	
	@RequestMapping(value="/processLogin", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("user") User loginUser,
			HttpServletRequest request, Model model){
		
		User user;
			
		try {
			
			user = userService.authenticate(loginUser.getUsername(), loginUser.getPassword());
			
			request.getSession().setAttribute("categories", productService.getCategories());
			request.getSession().setAttribute("user", user);		
			
			return "redirect:/" + user.getUserType().getType();
		} catch (UserNotFoundException e) {
			
			model.addAttribute("username", loginUser.getUsername());
			return "login";
		}	
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		
		request.getSession().invalidate();
		return "login";
	}
}
