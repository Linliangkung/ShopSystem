package com.zhku.shopsystem.web.action;

import java.util.List;

import org.hibernate.sql.Delete;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private Category category=new Category();
	private CategoryService categoryService;
	
	
	public String listCategoryPage(){
		//1.获取所有一级分类的集合
		List<Category> categories = categoryService.getCategories();
		//2.将一级分类的集合存入ActionContext中
		ActionContext.getContext().put("categories", categories);
		return "listCategoryPage";
	}
	
	public String addPage(){
		return "addPage";
	}
	
	public String addCategory(){
		//1.调用CategoryService的addCategory方法添加一级分类
		categoryService.addCategory(category);
		return "toListCategoryPage";
	}
	
	public String editPage(){
		//1.根据一级分类id获取一级分类
		category=categoryService.getCategoryByCid(category.getCid());
		return "editPage";
	}
	
	public String edit(){
		//1.调用CategoryService的update方法更新一级分类
		categoryService.update(category);
		return "toListCategoryPage";
	}
	
	public String delete(){
		//1.根据一级分类id删除一级分类
		categoryService.delete(category);
		return "toListCategoryPage";
	}
	@Override
	public Category getModel() {
		return category;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
