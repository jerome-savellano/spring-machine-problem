package com.qbryx.dao;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;

@Repository("userDaoCriteria")
public class UserDaoCriteriaImpl implements UserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public User getUser(String username) {

		User user = null;

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(User.class)
								   .add(Restrictions.eq("username", username));

		user = (User) criteria.uniqueResult();

		return user;
	}
}
