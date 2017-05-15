package com.zhku.shopsystem.web.action;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.User;

public class IndexAction extends ActionSupport{

	@Override
	/**
	 * 访问首页执行的方法
	 */
	public String execute() throws Exception {
		return "index";
	}
	
}
