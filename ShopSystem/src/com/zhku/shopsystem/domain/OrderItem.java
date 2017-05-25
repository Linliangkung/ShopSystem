package com.zhku.shopsystem.domain;


/*
 * create table orderitems(
	oiid int primary key auto_increment,#订单项id
	quantity int ,#订单项中的商品的数量
	subtotal double,#订单的小计
	pid int,#商品id
	oid varchar(100),#订单id
	foreign key (pid) references products (pid),
	foreign key (oid) references orders (oid)
);
 */
public class OrderItem {
	
	private Integer oiid;
	private Integer quantity;
	private Double subtotal;
	private Product product;
	private Order order;
	
	public Integer getOiid() {
		return oiid;
	}
	public void setOiid(Integer oiid) {
		this.oiid = oiid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
