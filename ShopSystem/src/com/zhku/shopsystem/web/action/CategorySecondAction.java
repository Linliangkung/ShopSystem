package com.zhku.shopsystem.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.service.CategoryService;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.PageBean;

public class CategorySecondAction extends ActionSupport {
	private Integer csid;
	private Integer page;

	private CategoryService categoryService;
	private ProductService productService;

	public String categorySecond() {
		// 1.查询所有一级分类
		List<Category> categories = categoryService.getCategories();
		// 2.查询一级分类下的商品的分页bean
		PageBean productPageBean = productService.getProductPageBeanByCsid(page, null, csid);
		// 3.将一级分类集合和商品集合存入ActionContext中
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("categories", categories);
		actionContext.put("productPageBean", productPageBean);

		return SUCCESS;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
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
