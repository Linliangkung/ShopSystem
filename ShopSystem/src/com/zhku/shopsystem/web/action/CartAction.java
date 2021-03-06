package com.zhku.shopsystem.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.swing.event.CaretListener;

import org.hibernate.sql.Delete;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.CartItem;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.CartItemService;
import com.zhku.shopsystem.utils.Cart;

public class CartAction extends ActionSupport implements ModelDriven<CartItem>{
	
	private CartItem cartItem=new CartItem();
	
	private CartItemService cartItemService;
	
	public String addCartItem(){
		//1.从session中,获取当前用户
		User existUser=(User) ActionContext.getContext().getSession().get("existUser");
		//2.将购物车项和用户关联
		cartItem.setUser(existUser);
		//3.调用cartItemService方法，为用户添加一个购物车项
		try {
			cartItemService.addCartItem(cartItem);
		} catch (MessageException e) {
			//说明库存不足或者购物车项中商品的数量超过库存数量
			e.printStackTrace();
			setActionErrors(Arrays.asList(e.getMessage()));
			return "productDetail";
		}
		return "toCartList";
	}

	/**
	 * 显示当前登录用户所有购物车项
	 * @return
	 */
	public String cartListPage(){
		//1.从session中,获取当前用户
		User existUser=(User) ActionContext.getContext().getSession().get("existUser");
		//2.调用cartService获得购物车封装对象
		Cart cartBean =cartItemService.getCartBeanByUid(existUser.getUid());
		//3.将购物车封装对象存到ActionContext域中
		ActionContext.getContext().put("cartBean", cartBean);
		return "cartListPage";
	}
	
	/**
	 * 根据ciid为购物车项的商品数量增加1
	 * @return
	 */
	public String increse(){
		try {
			cartItemService.increseByCiid(cartItem.getCiid());
		} catch (MessageException e) {
			setActionErrors(Arrays.asList(e.getMessage()));
			e.printStackTrace();
			return "chainToCartList";
		}
		return "toCartList";
	}
	/**
	 * 根据ciid为购物车项的商品数量减少1
	 * @return
	 */
	public String decrease(){
		cartItemService.decreaseByCiid(cartItem.getCiid());
		return "toCartList";
	}
	
	public String delete(){
		cartItemService.delete(cartItem);
		return "toCartList";
	}
	/**
	 * 清空购物车项
	 * @return
	 */
	public String deleteAll(){
		//1.从session中,获取当前用户
		User existUser=(User) ActionContext.getContext().getSession().get("existUser");
		cartItemService.deleteCartItemsByUid(existUser.getUid());
		
		return "toCartList";
	}
	@Override
	public CartItem getModel() {
		return cartItem;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	
}
