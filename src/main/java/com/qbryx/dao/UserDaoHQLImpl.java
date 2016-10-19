package com.qbryx.dao;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;
import com.qbryx.util.DAOQuery;

@Repository("userDaoHQL")
public class UserDaoHQLImpl implements UserDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public User getUser(String username) {
		
		User user = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(DAOQuery.HQL_GET_USER)
								 .setParameter("username", username);
			
			user = (User) query.getSingleResult();
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) 
				transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return user;
	}
}
 