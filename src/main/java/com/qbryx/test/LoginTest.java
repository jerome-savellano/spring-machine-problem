package com.qbryx.test;

import javax.annotation.Resource;

import org.dbunit.IDatabaseTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.qbryx.dao.UserDao;
import com.qbryx.domain.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class LoginTest {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Autowired
	private IDatabaseTester databaseTester;
		
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testLogin(){
		
		User user = null;
		
		Assert.assertEquals(user, userDao.getUser("wadwd"));
	}
	
}
