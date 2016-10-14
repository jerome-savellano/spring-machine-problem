package com.qbryx.helper;

import javax.servlet.http.HttpServletRequest;

import com.qbryx.domain.User;

public class UserUtil {
	
	public static User getCustomer(HttpServletRequest request){
		return (User) request.getSession().getAttribute("customer");
	}

	public static User getManager(HttpServletRequest request){
		return (User) request.getSession().getAttribute("manager");
	}
}
