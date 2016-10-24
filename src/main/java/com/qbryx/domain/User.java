package com.qbryx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	private long userId;
	
	private String userType;
	
	private String username;
	
	private String password;
	
	public User(){}
			
	public User(long id, String user_type, String username, String password) {
		super();
		this.userId = id;
		this.userType = user_type;
		this.username = username;
		this.password = password;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String user_type) {
		this.userType = user_type;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
