package com.zhku.shopsystem.service;

import java.util.List;

import com.zhku.shopsystem.domain.CartItem;
import com.zhku.shopsystem.exception.MessageException;

public interface CartItemService {
	
	/**
	 * 添加购物车项
	 * @param cartItem 添加购物车项
	 * @throws MessageException 
	 */
	void addCartItem(CartItem cartItem) throws MessageException;
	
	/**
	 * 根据用户的id查找购物车项
	 * @param uid 用户id 
	 * @return 购物车项组成的集合
	 */
	List<CartItem> getCartItemsByUid(Integer uid);
	
	/**
	 * 根据购物车项id,为购物车项的商品数量增加1
	 * @param ciid 购物车项id 
	 * @throws MessageException 
	 */
	void increseByCiid(Integer ciid) throws MessageException;
	
	/**
	  * 根据购物车项id,为购物车项的商品数量减少1
	 * @param ciid 购物车项id 
	 */
	void decreaseByCiid(Integer ciid);
	
	/**
	 * 删除购物车项
	 * @param cartItem 购物车项
	 */
	void delete(CartItem cartItem);
	/**
	 * 根据用户id删除购物车项
	 * @param uid 用户id 
	 */
	void deleteCartItemsByUid(Integer uid);
	
	
	
}
