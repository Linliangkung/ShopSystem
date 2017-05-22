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
		//2.调用cartItemService根据用户的id查询出所有购物车项
		List<CartItem> cartItems=cartItemService.getCartItemsByUid(existUser.getUid());
		//3.封装购物车对象
		Map<Integer,List<CartItem>> cart=new HashMap<Integer,List<CartItem>>();
		for(CartItem cartItem:cartItems){
			Integer sid=cartItem.getProduct().getSeller().getSid();
			//先判断购物车中是否已经存在对应商家的商品
			if(cart.containsKey(sid)){
				//如果存在
				List<CartItem> cartItemList = cart.get(sid);
				if(cartItemList!=null){
					cartItemList.add(cartItem);
				}
			}else{
				//如果不存在
				List<CartItem> cartItemList=new ArrayList<CartItem>();
				cartItemList.add(cartItem);
				cart.put(sid, cartItemList);
			}
		}
		Double totalMoney=0d;
		for(CartItem cartItem:cartItems){
			Double money=cartItem.getProduct().getShop_price()*cartItem.getQuantity();
			totalMoney=totalMoney+money;
		}
		
		Cart cartBean=new Cart();
		cartBean.setCart(cart);
		cartBean.setTotalMoney(totalMoney);
		
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
