package com.qbryx.helper;

import javax.servlet.http.HttpServletRequest;

import com.qbryx.domain.User;

public class UserUtil {
	
	public static long getUserId(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("customer");
		return user.getUserId();
	}

	public static User getManager(HttpServletRequest request){
		return (User) request.getSession().getAttribute("manager");
	}
}
