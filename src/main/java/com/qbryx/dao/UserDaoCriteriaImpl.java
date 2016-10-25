package com.qbryx.dao;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class)
									   .add(Restrictions.eq("username", username));
			
			user = (User) criteria.uniqueResult();
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		

		
		return user;
	}

	@Override
	public String getPassword(String username) {
		throw new UnsupportedOperationException();
	}
}
