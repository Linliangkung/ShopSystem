package com.zhku.shopsystem.dao;

import java.util.List;

import com.zhku.shopsystem.domain.Order;

public interface OrderDao extends BaseDao<Order>{
	
	/**
	 * 根据用户id获得所有订单
	 * @param uid 用户id
	 */
	List<Order> getOrdersByUid(Integer uid);
	
	/**
	 * 根据用户id获得对应订单的总条数
	 * @param uid 用户id 
	 * @return
	 */
	Integer getOrderCountByUid(Integer uid);

}
