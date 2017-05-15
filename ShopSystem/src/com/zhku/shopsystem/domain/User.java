package com.zhku.shopsystem.domain;

import java.util.Date;

/*
 * create table users(
 	uid int primary key auto_increment,	
	username varchar(30),
	password varchar(30),
	name varchar(30),
	email varchar(30),
	phone varchar(30),
	addr varchar(30),
	sex char(1),
	birthday date,
	state int,
	code varchar(64)
);
 */
public class User {
	
	private Integer uid;
	private String username;
	private String password;
	//对应表单提交的字段,不不存进数据库中
	private String rePassword;
	private String name;
	private String email;
	private String phone;
	private String addr;
	private Character sex;
	private Date birthday;
	private Integer state;
	private String code;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", rePassword=" + rePassword
				+ ", name=" + name + ", email=" + email + ", phone=" + phone + ", addr=" + addr + ", sex=" + sex
				+ ", birthday=" + birthday + ", state=" + state + ", code=" + code + "]";
	}
	
	
}
