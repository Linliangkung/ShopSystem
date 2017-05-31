package com.zhku.shopsystem.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.CategoryDao;
import com.zhku.shopsystem.dao.CategorySecondDao;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	private CategorySecondDao categorySecondDao;

	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public List<Category> getCategories() {
		return categoryDao.getAll();
	}
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void addCategory(Category category) {
		 categoryDao.save(category);
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public Category getCategoryByCid(Integer cid) {
		return categoryDao.getById(cid);
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void update(Category category) {
	   categoryDao.update(category);
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(Category category) {
		//1.删除一级分类关联的二级分类
		categorySecondDao.deleteByCid(category.getCid());
		//2.删除一级分类
		categoryDao.delete(category);
	}
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	

}
