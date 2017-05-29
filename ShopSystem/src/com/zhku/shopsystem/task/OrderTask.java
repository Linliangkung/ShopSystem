package com.zhku.shopsystem.task;


import com.zhku.shopsystem.service.OrderService;

public class OrderTask {
	private OrderService orderService;
	
	public void executeTask(){
		
		orderService.deleteOrderTimeout();
		
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
}
