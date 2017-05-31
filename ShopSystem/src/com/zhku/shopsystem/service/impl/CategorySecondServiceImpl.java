package com.zhku.shopsystem.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.CategorySecondDao;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.service.CategorySecondService;
import com.zhku.shopsystem.utils.PageBean;

public class CategorySecondServiceImpl implements CategorySecondService {
	private CategorySecondDao categorySecondDao;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public List<CategorySecond> getAll() {
		return categorySecondDao.getAll();
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public PageBean getCategorySecondPageBean(Integer page, Integer pageSize) {
		//1.获取二级分类总条数
		DetachedCriteria criteria=DetachedCriteria.forClass(CategorySecond.class);
		Integer totalCount=categorySecondDao.getTotalCount(criteria);
		//2.创建CategorySecondPageBean
		PageBean categorySecondPageBean=new PageBean(page, totalCount, pageSize);
		//3.获取二级分类分页集合
		List<CategorySecond> categorySeconds=categorySecondDao.getPageList(criteria, categorySecondPageBean.getStart(), pageSize);
		categorySecondPageBean.setList(categorySeconds);
		return categorySecondPageBean;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void addCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public CategorySecond getCategorySecondByCsid(Integer csid) {
		return categorySecondDao.getById(csid);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}


}
