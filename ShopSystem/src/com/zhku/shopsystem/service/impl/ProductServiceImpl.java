package com.zhku.shopsystem.service.impl;

import java.util.List;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import com.zhku.shopsystem.dao.ProductDao;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.PageBean;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;

	@Override
	public List<Product> getHotProduct() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Product.class);
		detachedCriteria.add(Restrictions.eq("is_hot", '1'));
		return productDao.getPageList(detachedCriteria, 0, 10);
	}


	@Override
	public List<Product> getLatestProducts() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Product.class);
		detachedCriteria.addOrder(Order.desc("pdate"));
		return productDao.getPageList(detachedCriteria, 0, 10);
	}
	
	@Override
	public Product getProductById(Integer pid) {

		return productDao.getById(pid);
	}
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	@Override
	public PageBean getProductPageBeanByCid(Integer page, Integer pageSize,Integer cid) {
		//获得一级分类的商品的总条数
		Integer totalCount=productDao.getProductCountByCid(cid);
		//创建PageBean
		PageBean productPageBean=new PageBean(page, totalCount, pageSize);
		//获得一级分类的商品的集合
		List<Product> products=productDao.getPageProductByCid(productPageBean.getStart(),productPageBean.getPageSize(),cid);
		
		productPageBean.setList(products);
		
		return productPageBean;
	}


	
	
}
