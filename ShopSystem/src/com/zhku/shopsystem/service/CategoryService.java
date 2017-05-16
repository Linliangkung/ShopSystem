package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.Category;

public interface CategoryService {
	
	/**
	 * 获取所有的一级分类
	 * @return 所有的一级分类封装的集合
	 */
	List<Category> getCategories();

}
