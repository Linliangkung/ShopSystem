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
	
	/**
	 * 查询二级分类商品的总记录条数
	 * @param csid 二级分类id
	 * @return
	 */
	Integer getProductCountByCsid(Integer csid);
	
	/**
	 * 分页查询二级分类的商品
	 * @param start 开始条数
	 * @param pageSize 查询条数
	 * @param csid 二级分类id 
	 * @return
	 */
	List<Product> getPageProductByCsid(Integer start, Integer pageSize, Integer csid);
	/**
	 * 根据商品id，在ShareMode下查询商品
	 * @param pid 商品id
	 * @return
	 */
	Product getByIdInShareMode(Integer pid);

}
