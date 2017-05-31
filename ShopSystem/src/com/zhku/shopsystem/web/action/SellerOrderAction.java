package com.zhku.shopsystem.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Order;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.service.OrderService;
import com.zhku.shopsystem.utils.PageBean;

public class SellerOrderAction extends ActionSupport{
	private Integer page;
	private Integer state=0;
	private String oid;
	private OrderService orderService;
	
	public String listOrderPage(){
		//1.获取当前登录的商家
		Seller existSeller = (Seller) ActionContext.getContext().getSession().get("existSeller");
		//2.设置每页显示条数
		Integer pageSize = 5;
		//3.根据当前登录商家id已经订单状态state获得分页bean
		PageBean orderPageBean=orderService.getOrderPageBeanBySidAndState(page, pageSize, existSeller.getSid(),state);
		//4.将orderPageBean保存到ActionContext域中
		ActionContext.getContext().put("orderPageBean", orderPageBean);
		return "listOrderPage";
	}

	
	public String delivery(){
		//1.根据订单id获得订单
		Order queryOrder =orderService.getOrderByOid(oid);
		//2.判断当前订单状态是否为1
		if(queryOrder.getState()==1){
			//说明订单已经支付了
			//设置订单状态为2(已发货)
			queryOrder.setState(2);
			orderService.updateOrder(queryOrder);
		}
		state=1;
		page=1;
		return "toListOrderPage";
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	

	public Integer getState() {
		return state;
	}


	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getPage() {
		return page;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
