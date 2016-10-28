package com.qbryx.service;

import com.qbryx.domain.User;
import com.qbryx.exception.FailedLoginException;

public interface UserService {
	User authenticate(String username, String password) throws FailedLoginException;
}
