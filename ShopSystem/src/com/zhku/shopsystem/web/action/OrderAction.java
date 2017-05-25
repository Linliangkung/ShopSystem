package com.zhku.shopsystem.web.action;

import java.util.Arrays;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Order;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.CartItemService;
import com.zhku.shopsystem.service.OrderService;
import com.zhku.shopsystem.utils.Cart;
import com.zhku.shopsystem.utils.PageBean;

public class OrderAction extends ActionSupport{
	private CartItemService cartItemService;
	private OrderService orderService;
	private Integer page;
	
	public String addOrder(){
		//1.从session中,获取当前用户
		User existUser=(User) ActionContext.getContext().getSession().get("existUser");
		//2.调用CartItemService的方法获取当前用户的购物车封装对象
		Cart cartBean=cartItemService.getCartBeanByUid(existUser.getUid());
		//3.调用OrderService的方法根据购物车封装对象生成订单
		try {
			orderService.addOrderByCart(cartBean,existUser);
		} catch (MessageException e) {
			setActionErrors(Arrays.asList(e.getMessage()));
			e.printStackTrace();
			return "chainToCartList";
		}catch (HibernateOptimisticLockingFailureException e) {
			System.out.println("商品库存已经被修改了,请重新提交订单");
			setActionErrors(Arrays.asList("商品库存已经被修改了,请重新提交订单"));
			e.printStackTrace();
			return "chainToCartList";
		}
		return "toOrderList";
	}
	
	public String orderListPage(){
		//1.从session中,获取当前用户
		User existUser=(User) ActionContext.getContext().getSession().get("existUser");
		//2.调用orderService根据用户id获得订单的分页封装对象
		Integer pageSize=5;
		PageBean orderPageBean=orderService.getOrderPageBeanByUid(page,pageSize,existUser.getUid());
		//3.将orderPageBean存入ActionContext域中
		ActionContext.getContext().put("orderPageBean", orderPageBean);
		return "orderListPage";
	}
	
	
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}


	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	
}
