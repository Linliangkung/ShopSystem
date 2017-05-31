package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.utils.PageBean;

public interface CategorySecondService {
	
	/**
	 * 获取所有的二级分类
	 * @return
	 */
	List<CategorySecond> getAll();
	
	/**
	 * 获取二级分类显示的分页对象
	 * @param page 当前页数
	 * @param pageSize 每页显示条数
	 * @return
	 */
	PageBean getCategorySecondPageBean(Integer page, Integer pageSize);
	
	/**
	 * 添加二级分类
	 * @param categorySecond 二级分类
	 */
	void addCategorySecond(CategorySecond categorySecond);
	/**
	 * 根据二级分类id获取二级分类
	 * @param csid 二级分类id
	 * @return
	 */
	CategorySecond getCategorySecondByCsid(Integer csid);
	
	/**
	 * 更新二级分类
	 * @param categorySecond 二级分类
	 */
	void update(CategorySecond categorySecond);
	
	/**
	 * 删除二级分类
	 * @param categorySecond 二级分类
	 */
	void delete(CategorySecond categorySecond);

}
