package com.zhku.shopsystem.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhku.shopsystem.domain.Seller;

public class CheckSellerLoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//1.从Session域中获取商家对象
		Seller existSeller=(Seller) ActionContext.getContext().getSession().get("existSeller");
		//2.判断商家对象是否为空
		if(existSeller==null){
			//3.说明商家未登录,重定向到登录页面
			return "toLoginPage";
		}
		return invocation.invoke();
	}
}
