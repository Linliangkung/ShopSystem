package com.zhku.shopsystem.dao;

import com.zhku.shopsystem.domain.CategorySecond;

public interface CategorySecondDao extends BaseDao<CategorySecond>{
	
	/**
	 * 根据一级分类id,删除二级分类
	 * @param cid 一级分类id
	 */
	void deleteByCid(Integer cid);

}
