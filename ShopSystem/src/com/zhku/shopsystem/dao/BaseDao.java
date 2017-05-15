package com.zhku.shopsystem.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	//增
	void saveOrUpdate(T t);
	// 增
	void save(T t);

	// 删
	void delete(T t);

	// 删
	void delete(Serializable id);

	// 改
	void update(T t);

	// 根据id查对象
	T getById(Serializable id);

	// 根据条件查总记录数
	Integer getTotalCount(DetachedCriteria criteria);

	// 根据条件查分页列表
	List<T> getPageList(DetachedCriteria criteria, Integer start, Integer pageSize);
	
	//根据离线条件查询对象
	T getByDetachedCriteria(DetachedCriteria criteria);
}
