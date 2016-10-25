package com.qbryx.service;

import com.qbryx.domain.User;

public interface UserService {
	User getUser(String username);
	boolean authenticate(String username, String password);
}
