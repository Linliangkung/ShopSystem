package com.zhku.shopsystem.domain;

/*
 *create table admins(
	aid int primary key auto_increment,#管理员id
	aaccount varchar(30),   #管理员账号,登录时使用的账号   
	apassword varchar(50)   #管理员密码,登录时使用的密码   
);
 */
public class Admin {
	private Integer aid;
	private String aaccount;
	private String apassword;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAaccount() {
		return aaccount;
	}
	public void setAaccount(String aaccount) {
		this.aaccount = aaccount;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
}
