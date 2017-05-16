package com.zhku.shopsystem.service.impl;

import java.util.List;

import com.zhku.shopsystem.dao.CategoryDao;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;

	
	@Override
	public List<Category> getCategories() {
		return categoryDao.getAll();
	}
	
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}
