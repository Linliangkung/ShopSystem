package com.zhku.shopsystem.domain;

public class CartItem {
	private Integer ciid;
	private Integer quantity;
	private Product product;
	private User user;

	public Integer getCiid() {
		return ciid;
	}

	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
