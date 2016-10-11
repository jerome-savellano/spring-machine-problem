package com.qbryx.domain;

import org.springframework.stereotype.Component;

@Component("user")
public class User {
	
	private int userId;
	private int userType;
	private String username;
	private String password;
	
	public User(){}
			
	public User(int id, int user_type, String username, String password) {
		super();
		this.userId = id;
		this.userType = user_type;
		this.username = username;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUser_type() {
		return userType;
	}

	public void setUser_type(int user_type) {
		this.userType = user_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
