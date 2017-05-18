package com.zhku.shopsystem.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;

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
}
