package com.zhku.shopsystem.utils;

import java.util.List;

/**
 * 封装分页显示的实体类
 * @author Administrator
 *
 */
public class PageBean {
	//封装实体信息集合
	private List list;
	//当前页数
	private Integer currentPage;
	//总条数
	private Integer totalCount;
	//每页显示条数
	private Integer pageSize;
	//总页数
	private Integer totalPage;
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if(this.currentPage==null){
			//如果当前页面没有指定,则显示第一页
			this.currentPage=1;
		}
		if(this.pageSize==null){
			//如果页面条数没有指定,则每页显示12条记录
			this.pageSize=12;
		}
		//计算总页数
		this.totalPage=(this.totalCount+this.pageSize-1)/this.pageSize;
		//判断当前页数是否合法
		if(this.currentPage<1){
			//如果当前页数小于1
			this.currentPage=1;
		}else if(this.currentPage>this.totalPage){
			//如果当前页数大于总页数
			this.currentPage=this.totalPage;
		}
	}
	
	//获得起始条数
	public Integer getStart() {
		return (this.currentPage-1)*this.pageSize;
	}
	
	
	
	
}
