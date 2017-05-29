package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.Order;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.utils.Cart;
import com.zhku.shopsystem.utils.PageBean;

public interface OrderService {
	/**
	 * 根据购物车封装对象添加订单
	 * @param cartBean
	 * @throws MessageException 
	 */
	void addOrderByCart(Cart cartBean,User user) throws MessageException;
	
	/**
	 * 根据用户id获得所有订单
	 * @param uid 用户id 
	 * @return 
	 */
	List<Order> getOrdersByUid(Integer uid);
	
	/**
	 * 根据用户id,获取订单分页封装对象
	 * @param page 页数
	 * @param pageSize 每页显示条数
	 * @param uid 用户id
	 * @return
	 */
	PageBean getOrderPageBeanByUid(Integer page, Integer pageSize, Integer uid);
	
	/**
	 * 删除超时的未支付的订单,即订单状态为0的超时订单
	 */
	void deleteOrderTimeout();
	
	/**
	 * 根据订单id查询订单
	 * @param oid 订单id
	 * @return
	 */
	Order getOrderByOid(String oid);
	
	/**
	 * 根据订单id删除未支付订单
	 * @param oid 订单id 
	 */
	void deleteOrderUnpay(String oid);
	
	/**
	 * 更新订单
	 * @param queryOrder
	 */
	void updateOrder(Order queryOrder);

}
