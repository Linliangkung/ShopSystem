package com.zhku.shopsystem.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.CategorySecondDao;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.service.CategorySecondService;

public class CategorySecondServiceImpl implements CategorySecondService {
	private CategorySecondDao categorySecondDao;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public List<CategorySecond> getAll() {
		return categorySecondDao.getAll();
	}
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
}
