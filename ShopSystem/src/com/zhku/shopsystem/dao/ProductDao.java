package com.zhku.shopsystem.dao;

import java.util.List;

import com.zhku.shopsystem.domain.Product;

public interface ProductDao extends BaseDao<Product> {
	
	/**
	 * 查询一级分类商品的总记录条数
	 * @param cid 一级分类id
	 * @return
	 */
	Integer getProductCountByCid(Integer cid);

	/**
	 * 分页查询一级分类的商品
	 * @param start 开始条数
	 * @param pageSize 查询条数
	 * @param cid 一级分类id 
	 * @return
	 */
	List<Product> getPageProductByCid(Integer start, Integer pageSize, Integer cid);

}
