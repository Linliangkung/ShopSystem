package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.Category;

public interface CategoryService {
	
	/**
	 * 获取所有的一级分类
	 * @return 所有的一级分类封装的集合
	 */
	List<Category> getCategories();
	
	/**
	 * 添加一级分类
	 * @param category 一级分类
	 */
	void addCategory(Category category);
	
	/**
	 * 根据一级分类id获取一级分类
	 * @param cid 一级分类id 
	 * @return
	 */
	Category getCategoryByCid(Integer cid);
	
	/**
	 * 更新一级分类
	 * @param category
	 */
	void update(Category category);
	
	/**
	 * 根据一级分类id删除一级分类
	 * @param category
	 */
	void delete(Category category);

}
