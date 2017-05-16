package com.zhku.shopsystem.domain;
/*
 * create table products(
	pid int primary key auto_increment,#商品id
	pname varchar(20), #商品名称
	market_price double, #商品市场价
	shop_price double,  #商品商城价
	imageurl varchar(200), #商品图片对象的url地址
	pnum int(11), 		#商品的库存 
	pdesc varchar(200), #商品的描述
	is_hot char(1),	#是否是热门商品:1.代表是热门商品,2.代表不是热门商品
	pdate datetime, #商品上架时间
	csid int(11),  #商品对应的二级分类id
	sid	 int(11),   #商品属于的商户id
	foreign key (csid) references categoryseconds (csid),
	foreign key (sid) references sellers (sid)
);
 * 
 */

import java.util.Date;

public class Product {
	private Integer pid;
	private String pname;
	private Double market_price;
	private Double shop_price;
	private String imageurl;
	private Integer pnum;
	private String pdesc;
	private Character is_hot;
	private Date pdate;
	private CategorySecond categorySecond;
	private Seller seller;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Character getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Character is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	
	
	
}
