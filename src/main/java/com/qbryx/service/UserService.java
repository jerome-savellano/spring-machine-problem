package com.qbryx.service;

import com.qbryx.domain.User;
import com.qbryx.exception.UserNotFoundException;

public interface UserService {
	User authenticate(String username, String password) throws UserNotFoundException;
}
