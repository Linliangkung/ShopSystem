package com.zhku.shopsystem.utils;

import java.util.List;
import java.util.Map;

import com.zhku.shopsystem.domain.CartItem;

/**
 * 购物车,封装购物车项以及总金额
 * @author Administrator
 *
 */
public class Cart {
	/**
	 * Integer代表商户的id ，CartItem代表购物车项
	 */
	private Map<Integer,List<CartItem>> cart;
	private double totalMoney;
	
	
	public Map<Integer, List<CartItem>> getCart() {
		return cart;
	}
	public void setCart(Map<Integer, List<CartItem>> cart) {
		this.cart = cart;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
}
