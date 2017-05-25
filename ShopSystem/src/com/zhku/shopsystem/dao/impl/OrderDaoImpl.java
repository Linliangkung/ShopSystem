package com.zhku.shopsystem.dao.impl;


import java.util.List;

import com.zhku.shopsystem.dao.OrderDao;
import com.zhku.shopsystem.domain.Order;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {
	@Override
	public List<Order> getOrdersByUid(Integer uid) {
		return (List<Order>) getHibernateTemplate().find("FROM Order o WHERE o.user.uid=? ORDER BY o.state asc", uid);
		
	}

	@Override
	public Integer getOrderCountByUid(Integer uid) {
		List<Long> list=(List<Long>) getHibernateTemplate().find("SELECT count(*) FROM Order o WHERE o.user.uid=? ", uid);
		return list.get(0).intValue();
	}
}
