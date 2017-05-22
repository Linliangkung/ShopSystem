package com.zhku.shopsystem.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.service.CategoryService;
import com.zhku.shopsystem.service.ProductService;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	Product product=new Product();
	private ProductService productService;
	private CategoryService categoryService;
	
	public String detail(){
		//1.根据商品id查询商品
		Product detailProduct = productService.getProductById(product.getPid());
		//2.查询一级分类
		List<Category> categories = categoryService.getCategories();
		//3.将商品、一级分类存入ActionContext中
		ActionContext actionContext=ActionContext.getContext();
		actionContext.put("detailProduct", detailProduct);
		actionContext.put("categories", categories);
		return "detailSuccess";
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
