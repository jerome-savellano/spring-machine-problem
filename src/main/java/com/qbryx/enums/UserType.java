package com.qbryx.enums;

public enum UserType {
	
	CUSTOMER ("customer"),
	MANAGER  ("manager")
	;
	
	private String type;
	
	private UserType(String userType){
		this.setType(userType);
	}

	public String getType() {
		return type;
	}

	public void setType(String userType) {
		this.type = userType;
	}
}
