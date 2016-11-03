package com.qbryx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qbryx.domain.User;
import com.qbryx.exception.FailedLoginException;
import com.qbryx.service.CustomerService;
import com.qbryx.service.ProductService;
import com.qbryx.service.UserService;
import com.qbryx.util.LoginValidator;
import com.qbryx.util.UserUtil;

@Controller
public class LoginController{
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="customerService")
	private CustomerService customerService;

	@Autowired
	private LoginValidator loginValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}
		
	@RequestMapping("/initial")
	public String trasdasday(HttpServletRequest request){
		
		User user = UserUtil.getUser(request);
		
		return (user != null) ? "redirect:/" + user.getUserType().getType() : "redirect:/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@Validated @ModelAttribute("user") User loginUser, BindingResult bindingResult,
			HttpServletRequest request, Model model){
		
		if(bindingResult.hasErrors()){
			return "login";
		}
			
		User user;
			
		try {
			
			user = userService.authenticate(loginUser.getUsername(), loginUser.getPassword());
			
			request.getSession().setAttribute("categories", productService.getCategories());
			request.getSession().setAttribute("user", user);
			
			return "redirect:/" + user.getUserType().getType();
		} catch (FailedLoginException e) {
			
			model.addAttribute("username", loginUser.getUsername());
			return "login";
		}	
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		
		request.getSession().invalidate();
		return new ModelAndView("login", "user", new User());
	}
}
