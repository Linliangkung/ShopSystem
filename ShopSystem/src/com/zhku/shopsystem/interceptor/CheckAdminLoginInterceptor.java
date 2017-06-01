package com.zhku.shopsystem.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhku.shopsystem.domain.Admin;
import com.zhku.shopsystem.domain.Seller;

public class CheckAdminLoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//1.从Session域中获取管理员对象
		Admin existAdmin=(Admin) ActionContext.getContext().getSession().get("existAdmin");
		//2.判断管理员对象是否为空
		if(existAdmin==null){
			//3.说明管理员未登录,重定向到登录页面
			return "toLoginPage";
		}
		return invocation.invoke();
	}
}
