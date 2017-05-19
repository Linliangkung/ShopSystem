package com.zhku.shopsystem.dao.impl;

import java.io.Serializable;
import java.util.List;


import com.zhku.shopsystem.dao.ProductDao;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.utils.PageHibernateCallback;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	@Override
	public Integer getProductCountByCid(Integer cid) {
		String hql="SELECT count(*) FROM Product p INNER JOIN p.categorySecond cs ON cs.category.cid=?";
		List<Long> count= (List<Long>) getHibernateTemplate().find(hql, cid);
		return count.get(0).intValue();
	}

	@Override
	public List<Product> getPageProductByCid(Integer start, Integer pageSize, Integer cid) {
		String hql="SELECT p FROM Product p INNER JOIN p.categorySecond cs ON cs.category.cid=? ORDER BY p.pid asc";
		return getHibernateTemplate().execute(new PageHibernateCallback<Product>(
				hql, 
				new Object[]{cid}, 
				start,
				pageSize));
	}

	@Override
	public Integer getProductCountByCsid(Integer csid) {
		String hql="SELECT count(*) FROM Product p WHERE p.categorySecond.csid=?";
		List<Long> count= (List<Long>) getHibernateTemplate().find(hql, csid);
		
		return count.get(0).intValue();
	}

	@Override
	public List<Product> getPageProductByCsid(Integer start, Integer pageSize, Integer csid) {
		String hql="SELECT p FROM Product p WHERE p.categorySecond.csid=? ORDER BY p.pid asc";
		return getHibernateTemplate().execute(new PageHibernateCallback<Product>(
				hql, 
				new Object[]{csid}, 
				start,
				pageSize));
	}
}
