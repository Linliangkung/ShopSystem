package com.zhku.shopsystem.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class AdminHomeAction extends ActionSupport{
	public String homePage(){
		return "homePage";
	}
	public String topPage(){
		return "topPage";
	}
	public String leftPage(){
		return "leftPage";
	}
	public String welcomePage(){
		return "welcomePage";
	}
	public String bottomPage(){
		return "bottomPage";
	}
}
