package com.zhku.shopsystem.web.action;

import java.util.Arrays;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.PageBean;

public class AdminProductAction extends ActionSupport {
	private Integer page;
	private ProductService productService;
	private Integer pid;
	
	public  String listProductPage(){
		//1.设置每页显示条数
		Integer pageSize=10;
		//2.调用ProductService方法根据is_hot的降序获得productPageBean
		PageBean productPageBean=productService.getProductPageBeanOrderByIsHotDesc(page,pageSize);
		//3.将productPageBean存入ActionContext域中
		ActionContext.getContext().put("productPageBean", productPageBean);
		return "listProductPage";
	}
	
	public String changeIsHot(){
		//1.根据商品id获取商品
		Product queryProduct = productService.getProductById(pid);
		//2.判断queryProduct是否热门商品
		if(queryProduct.getIs_hot()=='1'){
			//说明是热门商品,取消其热门商品
			queryProduct.setIs_hot('0');
		}else{
			//说明不是热门商品,设置其为热门商品
			//获取当前热门商品
			Integer hotProductCount=productService.getHotProductCount();
			if(hotProductCount>=10){
				//说明热门商品已经满了
				setActionErrors(Arrays.asList("热门商品不能超过10个"));
				return "chainToListProductPage";
			}
			queryProduct.setIs_hot('1');
		}
		productService.updateProduct(queryProduct);
		return "toListProductPage";
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
}
