package com.qbryx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.domain.Customer;
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
		
		if(request.getSession().getAttribute("user") != null){
			return "redirect:/processLogin";
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value="/processLogin", method=RequestMethod.POST)
	public String processLogin(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			HttpServletRequest request, Model model){
		
		User user = userService.getUser(username);
	
		if(user != null && user.getPassword().equals(password)){
			if(user.getUser_type() == 1){
				Customer customer = new Customer(user);
				customer.setCartId(customerService.getCartId(customer.getUserId()));
				
				request.getSession().setAttribute("customer", customer);
				request.getSession().setAttribute("categories", productService.getCategories());
				return "redirect:/customer";
			}else{
				
				request.getSession().setAttribute("categories", productService.getCategories());
				request.getSession().setAttribute("manager", user);
				return "redirect:/management";
			}
		}else{
		
			model.addAttribute("userDoesNotExist", true);
			model.addAttribute("username", username);
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
}
