package com.qbryx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qbryx.enums.UserType;

@Entity
@Table(name = "USER")
public class User {
	
	private long id;
	
	private UserType userType;
	
	private String username;
	
	private String password;
	
	public User(){}
			
	public User(long id, UserType userType, String username, String password) {
		super();
		this.id = id;
		this.userType = userType;
		this.username = username;
		this.password = password;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getUserId() {
		return id;
	}
	public void setUserId(long userId) {
		this.id = userId;
	}

	@Column(name = "user_type")
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType user_type) {
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
