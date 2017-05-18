package com.zhku.shopsystem.web.action;


import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.service.ProductService;

public class LoadProductImageAction extends ActionSupport {
	private String filePath;
	
	private Integer pid;
	
	private ProductService productService;
	
	
	public String loadProductImage(){
		Product product = productService.getProductById(pid);
		filePath=product.getImageurl();
		System.out.println(filePath);
		return SUCCESS;
	}
	
	
	public String getFilePath() {
		return filePath;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	

}
