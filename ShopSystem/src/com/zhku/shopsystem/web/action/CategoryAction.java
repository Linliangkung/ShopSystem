package com.zhku.shopsystem.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.service.CategoryService;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.PageBean;

public class CategoryAction extends ActionSupport{

	private Integer cid;
	private Integer page;
	
	private CategoryService categoryService;
	private ProductService productService;
	
	public String category() throws Exception {
		//1.查询所有一级分类
		List<Category> categories = categoryService.getCategories();
		//2.查询一级分类下的商品的分页bean
		PageBean productPageBean=productService.getProductPageBeanByCid(page,null,cid);
		//3.将一级分类集合和商品集合存入ActionContext中
		ActionContext actionContext=ActionContext.getContext();
		actionContext.put("categories", categories);
		actionContext.put("productPageBean", productPageBean);
		return super.execute();
	}
	
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
