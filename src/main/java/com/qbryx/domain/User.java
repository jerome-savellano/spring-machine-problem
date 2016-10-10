package com.qbryx.domain;

public class User {
	
	private int userId;
	private int user_type;
	private String username;
	private String password;
	
	public User(int id, int user_type, String username, String password) {
		super();
		this.userId = id;
		this.user_type = user_type;
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
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
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
