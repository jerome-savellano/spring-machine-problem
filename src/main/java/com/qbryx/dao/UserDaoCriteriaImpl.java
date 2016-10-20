package com.qbryx.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;

@Repository("userDaoCriteria")
public class UserDaoCriteriaImpl implements UserDao {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

//	@SuppressWarnings("deprecation")
	public User getUser(String username) {
		
//		User user = null;
//		
//		Session session = sessionFactory.openSession();
//		
//		Transaction transaction = null;
//		
//		try{
//			transaction = session.beginTransaction();
//			
//			Criteria criteria = session.createCriteria(User.class)
//									   .add(Restrictions.eq("username", username));
//			
//			user = (User) criteria.uniqueResult();
//			
//			transaction.commit();
//		}catch(HibernateException e){
//			if(transaction != null) transaction.rollback();
//			
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserService");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		criteria.select(user);
		criteria.where(cb.equal(user.get("username"), username));
		TypedQuery<User> q = em.createQuery(criteria);
		
		return (User) q.getSingleResult();
	}
}
