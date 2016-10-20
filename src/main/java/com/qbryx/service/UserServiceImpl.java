package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbryx.dao.UserDao;
import com.qbryx.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDaoCriteria")
	private UserDao userDao;
	
	public User getUser(String username) {
		return userDao.getUser(username);
	}

}
