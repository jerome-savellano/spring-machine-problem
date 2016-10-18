package com.qbryx.util;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
	
	@Resource(name = "sessionFactory")
	private SessionFactory session;
	
	public SessionFactory getSessionFactory() {
        return session;
    }
	
	public Session getSession(){
		return session.getCurrentSession();
	}
	
	public void setUp(){
		getSessionFactory().openSession();
		getSession().beginTransaction();
	}
	
	public void commit(){
		getSession().getTransaction().commit();
//		getSession().flush();
		getSession().close();
	}
	
	public Query setUpQuery(String hqlQuery){	
		return session.getCurrentSession().createQuery(hqlQuery);
	}
	
	@SuppressWarnings("deprecation")
	public Query setUpSQLQuery(String hqlQuery){	
		return session.getCurrentSession().createSQLQuery(hqlQuery);
	}
}
