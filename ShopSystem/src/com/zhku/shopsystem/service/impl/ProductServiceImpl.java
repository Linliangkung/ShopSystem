package com.zhku.shopsystem.service.impl;

import java.util.List;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.TrueFalseType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.ProductDao;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.PageBean;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Product> getHotProduct() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Product.class);
		detachedCriteria.add(Restrictions.eq("is_hot", '1'));
		return productDao.getPageList(detachedCriteria, 0, 10);
	}


	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Product> getLatestProducts() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Product.class);
		detachedCriteria.addOrder(Order.desc("pdate"));
		return productDao.getPageList(detachedCriteria, 0, 10);
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public Product getProductById(Integer pid) {

		return productDao.getById(pid);
	}
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
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


	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean getProductPageBeanByCsid(Integer page, Integer pageSize, Integer csid) {
		
		//获得二级分类的商品的总条数
		Integer totalCount=productDao.getProductCountByCsid(csid);
		//创建PageBean
		PageBean productPageBean=new PageBean(page, totalCount, pageSize);
		//获得二级分类的商品的集合
		List<Product> products=productDao.getPageProductByCsid(productPageBean.getStart(),productPageBean.getPageSize(),csid);
		
		productPageBean.setList(products);
		
		return productPageBean;
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean getProuctPageBeanBySid(Integer page, Integer pageSize, Integer sid) {
		//根据商家id获得商品的总条数
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("seller.sid", sid));
		Integer totalCount = productDao.getTotalCount(criteria);
		//创建PageBean
		PageBean productPageBean=new PageBean(page, totalCount, pageSize);
		//根据商家id获得商品的集合
		List<Product> products=productDao.getPageList(criteria, productPageBean.getStart(), productPageBean.getPageSize());
		productPageBean.setList(products);
		
		return productPageBean;
	}
	
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void addProduct(Product product) {
		productDao.save(product);
	}
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateProduct(Product product) {
		productDao.update(product);
	}
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}



	
}
