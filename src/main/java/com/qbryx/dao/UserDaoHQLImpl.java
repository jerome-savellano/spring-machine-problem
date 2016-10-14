package com.qbryx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;
import com.qbryx.util.DAOQuery;
import com.qbryx.util.HibernateUtil;

@Repository("userDaoHQL")
public class UserDaoHQLImpl implements UserDao{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public User getUser(String username) {
		
		return (User) hibernateUtil.setUpQuery(DAOQuery.HQL_GET_USER)
							   .setParameter("username", username)
							   .getSingleResult();
	}
}
 