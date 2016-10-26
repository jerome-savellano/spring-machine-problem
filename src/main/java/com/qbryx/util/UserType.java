package com.qbryx.util;

public enum UserType {
	
	CUSTOMER ("customer"),
	MANAGER  ("manager")
	;
	
	private String userType;
	
	private UserType(String userType){
		this.setUserType(userType);
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
