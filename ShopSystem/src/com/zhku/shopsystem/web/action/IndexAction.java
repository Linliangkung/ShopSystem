package com.zhku.shopsystem.web.action;

import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.service.CategoryService;
import com.zhku.shopsystem.service.ProductService;

public class IndexAction extends ActionSupport {

	private CategoryService categoryService;
	private ProductService productService;	
	
	/**
	 * 访问首页执行的方法
	 */
	public String index() throws Exception {
		//1.获取热门商品的集合
		List<Product> hotProducts=productService.getHotProduct();
		//2.获取最新的商品的集合
		List<Product> latestProducts=productService.getLatestProducts();
		//3.将集合存进ActionContext
		ActionContext.getContext().put("hotProducts", hotProducts);
		ActionContext.getContext().put("latestProducts", latestProducts);
		
		
		return "index";
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
}
