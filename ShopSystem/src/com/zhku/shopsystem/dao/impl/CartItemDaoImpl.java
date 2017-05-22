package com.zhku.shopsystem.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.zhku.shopsystem.dao.CartItemDao;
import com.zhku.shopsystem.domain.CartItem;

public class CartItemDaoImpl extends BaseDaoImpl<CartItem> implements CartItemDao {

	@Override
	public List<CartItem> getCartItemsByUid(Integer uid) {
		String hql="FROM CartItem c WHERE c.user.uid=?";
		return  (List<CartItem>) getHibernateTemplate().find(hql, uid);
	}

	@Override
	public void deleteCartItemsByUid(Integer uid) {
		getHibernateTemplate().execute(new HibernateCallback<Void>() {
			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				String hql="DELETE FROM CartItem c WHERE c.user.uid=?";
				Query createQuery = session.createQuery(hql);
				createQuery.setInteger(0, uid);
				createQuery.executeUpdate();
				return null;
			}
		});
	}


}
