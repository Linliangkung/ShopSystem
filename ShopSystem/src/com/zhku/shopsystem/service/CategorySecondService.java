package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.CategorySecond;

public interface CategorySecondService {
	
	/**
	 * 获取所有的二级分类
	 * @return
	 */
	List<CategorySecond> getAll();

}
