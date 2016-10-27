package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.UserDao;
import com.qbryx.domain.User;
import com.qbryx.exception.UserNotFoundException;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	public User authenticate(String username, String password) throws UserNotFoundException{
		
		User user = userDao.getUser(username);
		
		if(user == null || !user.getPassword().equals(password)){
			throw new UserNotFoundException();
		}
				
		return user;
	}
}
