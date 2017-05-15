package com.zhku.shopsystem.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.org.apache.xml.internal.security.Init;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.utils.MD5Utils;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	private Session session;
	
	private Transaction transaction;
	
	
	@Before
	public void init(){
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
	}
	@Test
	public void testSaveUser(){
		User user=new User();
		user.setUsername("jsako");
		user.setName("林良劲");
		user.setSex('1');
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1994-10-06"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session.save(user);
	}
	
	
	@Test
	public void testGetUser(){
		User user = session.get(User.class, 1);
		
		
		System.out.println(user.getName());
	}
	
	@Test
	public void testLogin(){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("username", "359270069@qq.com"),Restrictions.eq("password", MD5Utils.md5("123"))),
				Restrictions.and(Restrictions.eq("email", "359270069@qq.com"),Restrictions.eq("password", MD5Utils.md5("123")))));
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object obj = criteria.uniqueResult();
		System.out.println(obj);
	}
	
	
	@After
	public void destory(){
		transaction.commit();
		session.close();
		
	}
}
