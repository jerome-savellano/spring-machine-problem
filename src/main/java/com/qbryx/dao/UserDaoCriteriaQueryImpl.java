package com.qbryx.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;

@Repository("userDaoCriteriaQuery")
public class UserDaoCriteriaQueryImpl implements UserDao {
		
	public User getUser(String username) {
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		
		return (User) criteria.uniqueResult();*/
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
