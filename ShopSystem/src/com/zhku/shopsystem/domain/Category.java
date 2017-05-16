package com.zhku.shopsystem.domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * create table category(
	cid int primary key auto_increment,
	cname varchar(20)
);
 */
public class Category {
	
	private Integer cid;
	private String cname;
	//映射一级分类下的所有二级分类
	private Set<CategorySecond> categoryseconds=new HashSet<CategorySecond>();
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategoryseconds() {
		return categoryseconds;
	}
	public void setCategoryseconds(Set<CategorySecond> categoryseconds) {
		this.categoryseconds = categoryseconds;
	}
	
}
