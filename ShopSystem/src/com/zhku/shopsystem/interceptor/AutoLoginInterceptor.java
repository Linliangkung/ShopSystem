package com.zhku.shopsystem.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.service.UserService;

public class AutoLoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext context=invocation.getInvocationContext();
		HttpServletRequest request=ServletActionContext.getRequest();
		//先判断是否已经登录
		User existUser = (User) context.getSession().get("existUser");
		if(existUser==null){
			//如果没有登录
			//获取保存用户信息的cookie
			Cookie[] cookies = request.getCookies();
			Cookie findC=null;
			if(cookies!=null){
				for(Cookie cookie:cookies){
					if("autologin".equals(cookie.getName())){
						findC=cookie;
						break;
					}
				}
			}
			if(findC!=null){
				//如果找到保存用户信息的cookie
				//解析cookie
				String userInfo=findC.getValue();
				String username=userInfo.split(":")[0];
				String password=userInfo.split(":")[1];
				
				//获取userService
				ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
				UserService userService=(UserService) applicationContext.getBean("userService");
				//获取登录
				try{
				existUser = userService.login(username, password);
				if(existUser!=null){
				context.getSession().put("existUser", existUser);
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		//调用其他拦截器
		return invocation.invoke();
	}

}
