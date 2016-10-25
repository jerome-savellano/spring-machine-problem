package com.qbryx.helper;

import javax.servlet.http.HttpServletRequest;

import com.qbryx.domain.User;

public class UserUtil {
	
	public static User getUser(HttpServletRequest request){	
		return (User) request.getSession().getAttribute("user");
	}
}
