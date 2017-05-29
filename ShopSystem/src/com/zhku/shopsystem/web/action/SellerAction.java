package com.zhku.shopsystem.web.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.SellerService;
import com.zhku.shopsystem.service.UserService;
import com.zhku.shopsystem.utils.MD5Utils;

public class SellerAction extends ActionSupport implements ModelDriven<Seller>{
	private SellerService sellerService;
	

	private Seller seller=new Seller();
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String registPage(){
		return "registPage";
	}
	
	public String regist(){
		//1.判断商家的账号是否已经被注册
		boolean saccountExistOrNot=sellerService.checkSaccount(seller.getSaccount());
		if(!saccountExistOrNot){
			//说明商家账号已经存在
			Map<String,List<String>> errorMap=new HashMap<String,List<String>>();
			errorMap.put("saccount", Arrays.asList("账号已存在"));
			setFieldErrors(errorMap);
			return "registPage";
		}
		//2.注册商家
		sellerService.regist(seller);
		//3.重定向到商家登录界面
		return "toLoginPage";
	}
	
	public String login(){
		try {
			//调用SellerService的登录方法
			Seller existSeller=sellerService.login(seller.getSaccount(),MD5Utils.md5(seller.getSpassword()));
			//将返回的Seller对象存入Session域中
			ActionContext.getContext().getSession().put("existSeller", existSeller);
		} catch (MessageException e) {
			e.printStackTrace();
			setActionErrors(Arrays.asList(e.getMessage()));
			return "loginPage";
		}
		return "loginSuccess";
	}

	
	@Override
	public Seller getModel() {
		return seller;
	}
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}
}
