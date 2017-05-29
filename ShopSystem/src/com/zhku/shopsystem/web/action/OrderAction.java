package com.zhku.shopsystem.web.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.Order;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.CartItemService;
import com.zhku.shopsystem.service.OrderService;
import com.zhku.shopsystem.utils.Cart;
import com.zhku.shopsystem.utils.PageBean;
import com.zhku.shopsystem.utils.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private CartItemService cartItemService;
	private OrderService orderService;
	private Integer page;
	private Order order=new Order();
	
	private String pd_FrpId;
	
	private String hmac;
	private String p1_MerId;
	private String r0_Cmd;
	private String r1_Code;
	private String r2_TrxId;
	private String r3_Amt;
	private String r4_Cur;
	private String r5_Pid;
	private String r6_Order;
	private String r7_Uid;
	private String r8_MP;
	private String r9_BType;
	
	
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
	
	public String detail(){
		//1.调用OrderService,根据订单id获得订单
		Order queryOrder=orderService.getOrderByOid(order.getOid());
		if(queryOrder.getState()!=0){
			return "toOrderList";
		}
		//2.将Order存入ActionCotext对象中
		ActionContext.getContext().put("order", queryOrder);
		return "orderDetailPage";
	}
	
	public String deleteUnpayOrder(){
		//1.调用OrderService,根据订单id删除未支付订单
		try {
		orderService.deleteOrderUnpay(order.getOid());
		}catch(HibernateOptimisticLockingFailureException e){
			setActionErrors(Arrays.asList("订单删除失败,请重新删除"));
			e.printStackTrace();
			return "chainToOrderList";
		}
		return "toOrderList";
	}
	
	public String payOrder() throws Exception{
		//1.根据订单id获得订单
		Order queryOrder = orderService.getOrderByOid(order.getOid());
		//2.判断当前order的状态是否等于0
		if(queryOrder.getState()==0){
			//3.更新order信息
			queryOrder.setAddr(order.getAddr());
			queryOrder.setConsignee(order.getConsignee());
			queryOrder.setPhone(order.getPhone());
			orderService.updateOrder(queryOrder);
			//4.支付订单
			String p0_Cmd="Buy";
			String p1_MerId="10001126856";
			String p2_Order=order.getOid();
			String p3_Amt="0.01";
			String p4_Cur="CNY";
			String p5_Pid="";
			String p6_Pcat="";
			String p7_Pdesc="";
			String p8_Url="http://localhost/ShopSystem/order_payCallBack.action";
			String p9_SAF="";
			String pa_MP="";
			String pd_FrpId=this.pd_FrpId;
			String pr_NeedResponse="1";
			String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
			StringBuilder sBuilder=new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node?");
			sBuilder.append("p0_Cmd=").append(p0_Cmd).append("&");
			sBuilder.append("p1_MerId=").append(p1_MerId).append("&");
			sBuilder.append("p2_Order=").append(p2_Order).append("&");
			sBuilder.append("p3_Amt=").append(p3_Amt).append("&");
			sBuilder.append("p4_Cur=").append(p4_Cur).append("&");
			sBuilder.append("p5_Pid=").append(p5_Pid).append("&");
			sBuilder.append("p6_Pcat=").append(p6_Pcat).append("&");
			sBuilder.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			sBuilder.append("p8_Url=").append(p8_Url).append("&");
			sBuilder.append("p9_SAF=").append(p9_SAF).append("&");
			sBuilder.append("pa_MP=").append(pa_MP).append("&");
			sBuilder.append("pd_FrpId=").append(pd_FrpId).append("&");
			sBuilder.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sBuilder.append("hmac=").append(hmac);
			ServletActionContext.getResponse().sendRedirect(sBuilder.toString());
			return null;
		}
		return "toOrderList";
	}
	
	public String payCallBack(){
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		if(PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue)){
			//修改订单的状态
			Order queryOrder = orderService.getOrderByOid(r6_Order);
			if(queryOrder.getState()==0){
				queryOrder.setState(1);
				orderService.updateOrder(queryOrder);
				String msg="您的订单:"+r6_Order+",支付成功";
				setActionMessages(Arrays.asList(msg));
				return "msgPage";
			}
		}
		return "toOrderList";
	}
	
	public String firmOrder(){
		//1.根据订单id查询订单
		Order queryOrder = orderService.getOrderByOid(order.getOid());
		//2.判断订单的状态是否等于2
		if(queryOrder.getState()==2){
			queryOrder.setState(3);
			orderService.updateOrder(queryOrder);
			String msg="您的订单:"+order.getOid()+",确认成功";
			setActionMessages(Arrays.asList(msg));
			return "msgPage";
		}
		return "toOrderList";
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
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	
	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}

	public void setR0_Cmd(String r0_Cmd) {
		this.r0_Cmd = r0_Cmd;
	}

	public void setR1_Code(String r1_Code) {
		this.r1_Code = r1_Code;
	}

	public void setR2_TrxId(String r2_TrxId) {
		this.r2_TrxId = r2_TrxId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR4_Cur(String r4_Cur) {
		this.r4_Cur = r4_Cur;
	}

	public void setR5_Pid(String r5_Pid) {
		this.r5_Pid = r5_Pid;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR7_Uid(String r7_Uid) {
		this.r7_Uid = r7_Uid;
	}

	public void setR8_MP(String r8_MP) {
		this.r8_MP = r8_MP;
	}

	public void setR9_BType(String r9_BType) {
		this.r9_BType = r9_BType;
	}

	
	
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	@Override
	public Order getModel() {
		return order;
	}
	
	
}
