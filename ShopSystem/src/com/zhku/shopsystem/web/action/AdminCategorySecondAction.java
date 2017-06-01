package com.zhku.shopsystem.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.sql.Delete;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.Category;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.service.CategorySecondService;
import com.zhku.shopsystem.service.CategoryService;
import com.zhku.shopsystem.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	private Integer page;

	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;

	public String listCategorySecondPage() {
		// 1.设置每页显示条数
		Integer pageSize = 10;
		// 2.调用CategorySecondService的方法获取CategorySecond的PageBean
		PageBean categorySecondPageBean = categorySecondService.getCategorySecondPageBean(page, pageSize);
		// 3.将categorySecondPageBean存入ActionContext域中
		ActionContext.getContext().put("categorySecondPageBean", categorySecondPageBean);
		return "listCategorySecondPage";
	}

	public String addPage() {
		// 1.获取所有一级分类的集合
		List<Category> categories = categoryService.getCategories();
		// 2.将一级分类的集合存入ActionContext域中
		ActionContext.getContext().put("categories", categories);
		return "addPage";
	}

	public String addCategorySecond() {
		// 1.调用CategoryService的addCategorySecond方法添加二级分类
		categorySecondService.addCategorySecond(categorySecond);
		return "toListCategorySecondPage";

	}

	public String editPage() {
		// 1.根据scid获取二级分类
		categorySecond = categorySecondService.getCategorySecondByCsid(categorySecond.getCsid());
		// 2.获取所有一级分类的集合
		List<Category> categories = categoryService.getCategories();
		// 3.将一级分类的集合存入ActionContext域中
		ActionContext.getContext().put("categories", categories);
		return "editPage";
	}
	
	public String edit(){
		//调用CategoryService的update方法更新二级分类
		categorySecondService.update(categorySecond);
		return "toListCategorySecondPage";
	}
	
	public String delete(){
		//调用CategoryService的delete 方法删除二级分类
		categorySecondService.delete(categorySecond);
		return "toListCategorySecondPage";
	}

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
