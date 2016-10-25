package com.qbryx.dao;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;
import com.qbryx.util.DAOQuery;

@Repository("userDaoHQL")
public class UserDaoHQLImpl implements UserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public User getUser(String username) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_GET_USER).setParameter("username", username);

		return (User) query.getSingleResult();
	}

	@Override
	public String getPassword(String username) {
		throw new UnsupportedOperationException();
	}
}
