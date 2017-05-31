package com.zhku.shopsystem.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
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

import com.zhku.shopsystem.dao.CategoryDao;
import com.zhku.shopsystem.domain.Admin;
import com.zhku.shopsystem.domain.CartItem;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.domain.Order;
import com.zhku.shopsystem.domain.OrderItem;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.service.CartItemService;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.MD5Utils;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	
	@Resource(name="productService")
	private ProductService productService;
	@Resource(name="cartItemService")
	private CartItemService cartItemService;
	
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
	
	@Test
	public void addSellers(){
		for(int i=1;i<=7;i++){
			Seller seller=new Seller();
			seller.setSaccount("abc"+i);
			seller.setSpassword("123");
			seller.setRegistdate(new Date());
			seller.setSdesc("本商铺出售个种精美商铺");
			seller.setSname("大妹丸子"+i);
			seller.setSphone("13143350142");
			
			session.save(seller);
		}
	}
	
	@Test
	public void addProducts(){
		for(int i=41;i<=100;i++){
			Product product=new Product();
			product.setImageurl("/WEB-INF/products/cs30006.png");
			product.setIs_hot('0');
			product.setMarket_price(100d);
			product.setPdate(new Date(System.currentTimeMillis()+i));
			product.setPname("商品"+i);
			product.setPnum(1000);
			product.setShop_price(95d);
			CategorySecond second=new CategorySecond();
			second.setCsid(1);
			product.setCategorySecond(second);
			product.setPdesc("这是一个无与伦比的商品");
			
			session.save(product);
		}
	}
	@Test
	public void testGetProductOrderByPDateDesc(){
		
		List<Product> latestProducts = productService.getLatestProducts();
	}
	
	
	@Test
	public void testGetProductCountByCid(){
		
		String hql="SELECT count(*) FROM Product p INNER JOIN p.categorySecond cs ON cs.category.cid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, 1);
		Long count=(Long) query.uniqueResult();
		System.out.println(count);
		
	}
	
	@Test 
	public void testGetPageProductByCid(){
		
		String hql="SELECT p FROM Product p INNER JOIN p.categorySecond cs ON cs.category.cid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, 1);
		query.setMaxResults(12);
		query.setFirstResult(0);
		
		List<Product> list = query.list();
		
		for(Product product:list){
			System.out.println(product.getPname());
		}
		
	}
	
	@Test
	public void testGetCartItem(){
		Query query = session.createQuery("FROM CartItem c WHERE c.product.pid=? and c.user.uid=?");
		query.setInteger(0, 2);
		query.setInteger(1, 1);
		
		CartItem cartItem=(CartItem) query.uniqueResult();
		System.out.println(cartItem.getQuantity());
		cartItem.setQuantity(101);
		
		
	}
	
	@Test
	public void testSaveCartItem(){
		CartItem cartItem=new CartItem();
		Product product=new Product();
		product.setPid(3);
		cartItem.setProduct(product);
		cartItem.setUser(session.get(User.class, 1));
		
		cartItem.setQuantity(100);
		session.save(cartItem);
		
	}
	
	@Test
	public void testGetInShareMode(){
		Product product = session.get(Product.class,1,new LockOptions(LockMode.PESSIMISTIC_READ));
		System.out.println(product.getPnum());
	
	}
	
	@Test
	public void testSaveOrder(){
		
		Order order=new Order();
		order.setAddr("广州市平沙村富力城");
		order.setOrdertime(new Date());
		order.setPhone("13143350142");
		order.setSeller(session.get(Seller.class, 1));
		order.setState(0);
		order.setTotal(1000d);
		order.setUser(session.get(User.class, 1));
		
		OrderItem orderItem=new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(session.get(Product.class, 2));
		orderItem.setQuantity(10);
		orderItem.setSubtotal(940d);
		
		order.getOrderItems().add(orderItem);
		
		session.save(order);
		
	}
	@Test
	public void testDeleteOrder(){
		session.delete(session.get(Order.class,"4028b8815c39555a015c395562f50000"));
	}
	
	
	@Test
	public void testLazy(){
		Order order=new Order();
		CartItem cartItem = session.get(CartItem.class,14);
		transaction.commit();
		transaction=session.beginTransaction();
		
		Product product = cartItem.getProduct();
		OrderItem orderItem=new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		orderItem.setQuantity(cartItem.getQuantity());
		orderItem.setSubtotal(cartItem.getQuantity()*product.getShop_price());
		order.getOrderItems().add(orderItem);
		product.setPnum(product.getPnum()-cartItem.getQuantity());
		
		order.setAddr("广州市平沙村富力城");
		order.setOrdertime(new Date());
		order.setPhone("13143350142");
		order.setState(0);
		User user=new User();
		user.setUid(1);
		order.setUser(user);
		Seller seller=new Seller();
		seller.setSid(3);
		order.setSeller(seller);
		
		session.save(order);
		
	}
	
	@Test
	public void testProductVersion(){
		
		//CartItem cartItem=session.get(CartItem.class, 39);
		Product product=session.get(Product.class, 30);
		product.setPnum(product.getPnum()-1);
		session.saveOrUpdate(product);
		
		
	}
	
	@Test
	public void testGetOrder(){
		DetachedCriteria criteria=DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.eq("user.uid", 1));
		Criteria executableCriteria = criteria.getExecutableCriteria(session);
		System.out.println(executableCriteria.list().size());
	}
	
	@Test
	public void testGetAdmin(){
		
		DetachedCriteria criteria=DetachedCriteria.forClass(Admin.class);
		criteria.add(Restrictions.eq("aaccount", "admin"));
		Criteria executableCriteria = criteria.getExecutableCriteria(session);
		Admin admin= (Admin) executableCriteria.uniqueResult();
		System.out.println(admin.getAaccount());
	}
	
	@After
	public void destory(){
		transaction.commit();
		session.close();
		
	}
}
