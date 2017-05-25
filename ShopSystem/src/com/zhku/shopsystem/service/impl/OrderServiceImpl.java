package com.zhku.shopsystem.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.DoubleListUIBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.CartItemDao;
import com.zhku.shopsystem.dao.OrderDao;
import com.zhku.shopsystem.dao.ProductDao;
import com.zhku.shopsystem.domain.CartItem;
import com.zhku.shopsystem.domain.Order;
import com.zhku.shopsystem.domain.OrderItem;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.OrderService;
import com.zhku.shopsystem.utils.Cart;
import com.zhku.shopsystem.utils.PageBean;

import sun.text.normalizer.UBiDiProps;

public class OrderServiceImpl implements OrderService{
	private OrderDao orderDao;
	private ProductDao productDao;
	private CartItemDao cartItemDao;
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=MessageException.class)
	public void addOrderByCart(Cart cartBean,User user) throws MessageException {
		Map<Integer, List<CartItem>> cart = cartBean.getCart();
		//判断集合是否为空,如果为空说明当前用户购物车项为空
		if(cart==null||cart.size()==0){
			throw new MessageException("你的购物车空空如也,请先购物");
		}
		//遍历购物车
		for(Map.Entry<Integer, List<CartItem>> entryCart :cart.entrySet()){
			//遍历购物车项
			Order order=new Order();
			Double total=0d;
			for(CartItem cartItem:entryCart.getValue()){
				//判断购物车项的商品数量是否大于商品的库存
				Product product=cartItem.getProduct();
				if(cartItem.getQuantity()>product.getPnum()){
						throw new MessageException("商品"+product.getPname()+"的数量超出库存");
				}
				System.out.println("version:"+product.getVersion());
				//封装OrderItem对象
				OrderItem orderItem=new OrderItem();
				orderItem.setOrder(order);
				orderItem.setProduct(product);
				orderItem.setQuantity(cartItem.getQuantity());
				orderItem.setSubtotal(product.getShop_price()*cartItem.getQuantity());
				total+=orderItem.getSubtotal();
				order.getOrderItems().add(orderItem);
				//重新设置商品库存
				product.setPnum(product.getPnum()-cartItem.getQuantity());
				productDao.saveOrUpdate(product);
				//删除购物车项
				cartItemDao.delete(cartItem);
			}
			//封装Order对象,调用orderDao保存order
			order.setTotal(total);
			order.setAddr(user.getAddr());
			order.setOrdertime(new Date());
			order.setPhone(user.getPhone());
			order.setState(0);
			Seller seller=new Seller();
			seller.setSid(entryCart.getKey());
			order.setSeller(seller);
			order.setUser(user);
			orderDao.save(order);
		}
	}
	@Override
	public List<Order> getOrdersByUid(Integer uid) {
		
		return orderDao.getOrdersByUid(uid);
		
	}
	
	@Override
	public PageBean getOrderPageBeanByUid(Integer page, Integer pageSize, Integer uid) {
		//获得用户订单的总条数
		Integer totalCount=orderDao.getOrderCountByUid(uid);
		//创建orderPageBean
		PageBean orderPageBean=new PageBean(page, totalCount, pageSize);
		//根据用户id获得对应订单集合
		DetachedCriteria criteria=DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.eq("user.uid", uid));
		criteria.addOrder(org.hibernate.criterion.Order.asc("state"));
		criteria.addOrder(org.hibernate.criterion.Order.desc("ordertime"));
		List<Order> orders=orderDao.getPageList(criteria, orderPageBean.getStart(),orderPageBean.getPageSize());
		orderPageBean.setList(orders);
		return orderPageBean;
	}
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}
	
}
