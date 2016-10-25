package com.qbryx.dao;

import com.qbryx.domain.User;

public interface UserDao {
	
	User getUser(String username);
	String getPassword(String username);
}
