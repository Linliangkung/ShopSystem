package com.zhku.shopsystem.web.action;

import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.service.CategoryService;

public class IndexAction extends ActionSupport {

	private CategoryService categoryService;

	
	/**
	 * 访问首页执行的方法
	 */
	public String index() throws Exception {
		return "index";
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
