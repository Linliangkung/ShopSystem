package com.zhku.shopsystem.domain;

import java.util.HashSet;
import java.util.Set;

/*
 * create table categoryseconds(
	csid int primary key auto_increment, #二级分类id
	csname varchar(20),		#二级分类名称
	cid int ,  				#一级分类的id(外键)
	foreign key (cid) references categorys (cid)
);

 */
public class CategorySecond {
	private Integer csid;
	private String csname;
	//二级分类对应的一级分类
	private Category category;
	//映射二级分类下的所有商品
	private Set<Product> products=new HashSet<Product>();
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "CategorySecond [csid=" + csid + ", csname=" + csname + "]";
	}
}
