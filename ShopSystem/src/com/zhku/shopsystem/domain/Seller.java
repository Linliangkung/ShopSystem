package com.zhku.shopsystem.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
create table sellers(
	sid int primary key auto_increment,#商家id
	saccount varchar(30),   #商家账号,登录时使用的账号   
	spassword varchar(30),  #商家密码,登录时使用的密码
	sname varchar(30),		#商家名称,显示给用户看的
	sphone varchar(30),  	#商家联系方式(指的是手机)
	sdesc  varchar(200)     #店铺描述
	registdate date,		#店铺注册的时间
);
 */
public class Seller {
	private Integer sid;
	private String saccount;
	private String spassword;
	private String sname;
	private String sphone;
	private String sdesc;
	private Date registdate;
	private Set<Product> products=new HashSet<Product>();
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSaccount() {
		return saccount;
	}
	public void setSaccount(String saccount) {
		this.saccount = saccount;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSdesc() {
		return sdesc;
	}
	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}
	public Date getRegistdate() {
		return registdate;
	}
	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Seller [sid=" + sid + ", saccount=" + saccount + ", spassword=" + spassword + ", sname=" + sname
				+ ", sphone=" + sphone + ", sdesc=" + sdesc + ", registdate=" + registdate + "]";
	}
	
	
}
