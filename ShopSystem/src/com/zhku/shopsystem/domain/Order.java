package com.zhku.shopsystem.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/*
 * create table orders(
	oid varchar(100) primary key,#订单id,使用uuid
	total double,#订单金额
	ordertime datetime,#订单下单时间
	state int,#订单状态:0.代表未支付或待支付 1.未发货或已支付 2.已发货或待收货 3.确认收货
	addr varchar(30),#收货地址
	phone varchar(30),#收货电话
	uid int,#用户id
	sid int,#商户id
	foreign key (uid) references users (uid),
	foreign key (sid) references sellers (sid)
);
 */
public class Order {
	private String oid;
	private Double total;
	private Date ordertime;
	private Integer state;
	private String addr;
	private String phone;
	private String consignee;
	private User user;
	private Seller seller;
	private Set<OrderItem> orderItems=new HashSet<OrderItem>();
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
