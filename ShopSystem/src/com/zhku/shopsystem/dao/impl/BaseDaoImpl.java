package com.zhku.shopsystem.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhku.shopsystem.dao.BaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	// 用于接收运行时泛型类型
	private Class clazz;

	public BaseDaoImpl() {
		super();
		// 获得当前类型的带有泛型的父类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得当前类的泛型类型
		clazz = (Class) type.getActualTypeArguments()[0];

	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}
	
	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}
	
	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(Serializable id) {
		T t = getById(id);
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		// 设置查询的聚合函数
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		// 清空聚合函数
		criteria.setProjection(null);
		if (list != null && list.size() > 0) {
			Long count = list.get(0);
			return count.intValue();
		}
		return null;
	}

	@Override
	public List<T> getPageList(DetachedCriteria criteria, Integer start, Integer pageSize) {
		return (List<T>) getHibernateTemplate().findByCriteria(criteria, start, pageSize);
	}

	@Override
	public T getByDetachedCriteria(DetachedCriteria criteria) {
		List<T> list=(List<T>) getHibernateTemplate().findByCriteria(criteria);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	

}
