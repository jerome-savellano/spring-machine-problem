package com.qbryx.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.qbryx.domain.Customer;

@Component("customerHelper")
public class CustomerHelper {

	public Customer getCustomer(HttpServletRequest request){
		return (Customer) request.getSession().getAttribute("customer");
	}
}
