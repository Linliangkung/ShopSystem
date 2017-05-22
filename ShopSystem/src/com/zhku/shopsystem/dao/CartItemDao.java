package com.zhku.shopsystem.dao;

import java.util.List;

import com.zhku.shopsystem.domain.CartItem;

public interface CartItemDao extends BaseDao<CartItem>{
	/**
	 * 根据用户id获得购物车项
	 * @param uid 用户id
	 * @return
	 */
	List<CartItem> getCartItemsByUid(Integer uid);
	
	/**
	 * 根据用户id删除购物车项
	 * @param uid
	 */
	void deleteCartItemsByUid(Integer uid);

}
