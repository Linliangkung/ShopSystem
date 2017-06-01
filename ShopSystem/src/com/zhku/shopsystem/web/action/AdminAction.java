package com.zhku.shopsystem.web.action;

import java.util.Arrays;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.Admin;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.AdminService;
import com.zhku.shopsystem.utils.MD5Utils;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	private Admin admin=new Admin();
	private AdminService adminService;
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String login(){
		try {
			//调用AdminService的登录方法
			Admin existAdmin=adminService.login(admin.getAaccount(),MD5Utils.md5(admin.getApassword()));
			//将返回的Seller对象存入Session域中
			ActionContext.getContext().getSession().put("existAdmin", existAdmin);
		} catch (MessageException e) {
			e.printStackTrace();
			setActionErrors(Arrays.asList(e.getMessage()));
			return "loginPage";
		}
		return "loginSuccess";
	}
	
	@Override
	public Admin getModel() {
		return admin;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
}
