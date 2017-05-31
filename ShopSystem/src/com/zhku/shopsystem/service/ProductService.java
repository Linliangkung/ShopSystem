package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.utils.PageBean;

public interface ProductService {
	
	/**
	 * 获取10个热门商品
	 * @return
	 */
	List<Product> getHotProduct();
	
	/**
	 * 获取10个最新商品 
	 * @return
	 */
	List<Product> getLatestProducts();
	
	/**
	 * 根据商品id查询商品
	 * @param pid 商品id
	 */
	Product getProductById(Integer pid);
	/**
	 * 根据一级分类id和当前页数来获取商品的分页bean
	 * @param page 当前页数
	 * @param cid 一级分类id
	 * @return
	 */
	PageBean getProductPageBeanByCid(Integer page, Integer pageSize,Integer cid);
	
	/**
	 * 根据二级分类id和当前页数来获取商品的分页bean
	 * @param page 	当前页数
	 * @param pageSize 每页显示条数
	 * @param csid	二级分类id
	 * @return
	 */
	PageBean getProductPageBeanByCsid(Integer page, Integer pageSize, Integer csid);
	
	/**
	 * 根据商家id获取商品的分页bean
	 * @param page 当前页数
	 * @param pageSize 每页显示条数
	 * @param sid 商家id
	 * @return  
	 */
	PageBean getProuctPageBeanBySid(Integer page, Integer pageSize, Integer sid);
	
	/**
	 * 添加商品
	 * @param product 商品
	 */
	void addProduct(Product product);
	
	/**
	 * 更新商品信息 
	 * @param product 商品
	 */
	void updateProduct(Product product);

}
