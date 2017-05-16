package com.zhku.shopsystem.interceptor;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.service.CategoryService;
import com.zhku.shopsystem.service.UserService;

public class InitCategoryInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 0.判断session域中是否已经保存了所有一级分类
		if (session.get("categories") == null) {
			// 1.获得所有的一级分类
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			CategoryService categoryService=(CategoryService) applicationContext.getBean("categoryService");
			List<Category> categories = categoryService.getCategories();
			// 2.将所有的一级分类保存到session域中
			session.put("categories", categories);
		}
		//所有情况都放行
		return invocation.invoke();
	}

}
