package com.zhku.shopsystem.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

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
import com.zhku.shopsystem.dao.CategoryDao;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.utils.MD5Utils;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	
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
	
	@Test
	public void testGetAllCategory(){
		
		List<Category> categories = categoryDao.getAll();
		System.out.println(categories.size());
	}
	
	@Test
	public void testGetCategory(){
		Category category = session.get(Category.class, 1);
		for(CategorySecond categorySecond:category.getCategoryseconds()){
			System.out.println(categorySecond);
		}
	}
	
	@Test
	public void testGetCategorySecond(){
		CategorySecond categorySecond=session.get(CategorySecond.class, 1);
		
		System.out.println(categorySecond.getCategory().getCname());
		
		for(Product product:categorySecond.getProducts()){
			
			System.out.println(product.getPname());
			
		}
	}
	
	@Test
	public void testGetSeller(){
		Seller seller = session.get(Seller.class, 1);
		System.out.println(seller);
		Set<Product> products = seller.getProducts();
		for(Product product:products){
			
			System.out.println(product.getPname());
			
		}
	}
	
	@Test
	public void testGetProduct(){
		
		Product product=session.get(Product.class, 1);
		Seller seller = product.getSeller();
		CategorySecond categorySecond = product.getCategorySecond();
		
		System.out.println(seller);
		
		System.out.println(categorySecond);
	}
	
	@After
	public void destory(){
		transaction.commit();
		session.close();
		
	}
}
