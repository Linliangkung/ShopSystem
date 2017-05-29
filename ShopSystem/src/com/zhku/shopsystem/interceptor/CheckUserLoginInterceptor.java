package com.zhku.shopsystem.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zhku.shopsystem.domain.User;

public class CheckUserLoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//1.获取当前Action的名字
		String actionName = invocation.getProxy().getActionName();
		//2.判断当前Action是否是order或者cart模块
		if(actionName.startsWith("order")||actionName.startsWith("cart")){
			User existUser = (User) invocation.getInvocationContext().getSession().get("existUser");
			//判断当前用户是否登录
			if(existUser==null){
				//如果用户没有登录
				return "toLoginPage";
			}
		}
		return invocation.invoke();
	}

}
