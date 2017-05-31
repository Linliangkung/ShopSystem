package com.zhku.shopsystem.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhku.shopsystem.domain.CategorySecond;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.service.CategorySecondService;
import com.zhku.shopsystem.service.ProductService;
import com.zhku.shopsystem.utils.PageBean;

public class SellerProductAction extends ActionSupport implements ModelDriven<Product> {
	private Integer page;
	private Product product = new Product();
	private ProductService productService;
	private CategorySecondService categorySecondService;
	private File image;
	private String imageContentType;
	private String imageFileName;

	public String listProductPage() {
		// 1.获取当前登录商家
		Seller existSeller = (Seller) ActionContext.getContext().getSession().get("existSeller");
		// 2.设置每页显示条数
		Integer pageSize = 10;
		// 3.根据当前登录商家id获得分页bean
		PageBean productPageBean = productService.getProuctPageBeanBySid(page, pageSize, existSeller.getSid());
		// 4.将分页bean保存到ActionContext域中
		ActionContext.getContext().put("productPageBean", productPageBean);
		return "listProductPage";
	}

	public String addPage() {
		// 1.获取所有的二级分类
		List<CategorySecond> categorySeconds = categorySecondService.getAll();
		// 2.将二级分类集合存入ActionContext域中
		ActionContext.getContext().put("categorySeconds", categorySeconds);
		return "addPage";
	}

	/**
	 * 添加商品
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addProduct() throws IOException {
		// 1.上传图片图片到磁盘
		String realpath = ServletActionContext.getServletContext().getRealPath("WEB-INF/products");
		String uuidname = UUID.randomUUID().toString() + "_" + imageFileName;
		String hashStr = Integer.toHexString(uuidname.hashCode());
		String path = "WEB-INF/products";
		for (char c : hashStr.toCharArray()) {
			realpath += "\\" + c;
			path += "/" + c;
		}
		path += "/" + uuidname;
		realpath += "\\" + uuidname;
		FileUtils.copyFile(image, new File(realpath));
		System.out.println(realpath);
		System.out.println(path);
		// 2.保存商品信息到数据库
		Seller existSeller = (Seller) ActionContext.getContext().getSession().get("existSeller");
		product.setImageurl(path);
		product.setIs_hot('0');
		product.setPdate(new Date());
		product.setSeller(existSeller);
		productService.addProduct(product);
		return "toListProductPage";
	}

	public String editProductPage() {
		// 1.根据商品id获取商品
		product = productService.getProductById(product.getPid());
		// 2.获取所有的二级分类
		List<CategorySecond> categorySeconds = categorySecondService.getAll();
		// 3.将二级分类集合存入ActionContext域中
		ActionContext.getContext().put("categorySeconds", categorySeconds);
		return "editProductPage";
	}
	
	public String editProduct() throws IOException{
		//1.根据商品id获取商品
		Product queryProduct=productService.getProductById(product.getPid());
		//2.删除商品原有的图片
		new File(ServletActionContext.getServletContext().getRealPath(queryProduct.getImageurl())).delete();
		//3.上传商品图片到磁盘
		String realpath = ServletActionContext.getServletContext().getRealPath("WEB-INF/products");
		String uuidname = UUID.randomUUID().toString() + "_" + imageFileName;
		String hashStr = Integer.toHexString(uuidname.hashCode());
		String path = "WEB-INF/products";
		for (char c : hashStr.toCharArray()) {
			realpath += "\\" + c;
			path += "/" + c;
		}
		path += "/" + uuidname;
		realpath += "\\" + uuidname;
		FileUtils.copyFile(image, new File(realpath));
		System.out.println(realpath);
		System.out.println(path);
		//4.更新商品信息
		queryProduct.setCategorySecond(product.getCategorySecond());
		queryProduct.setImageurl(path);
		queryProduct.setMarket_price(product.getMarket_price());
		queryProduct.setPdesc(product.getPdesc());
		queryProduct.setPname(product.getPname());
		queryProduct.setPnum(product.getPnum());
		queryProduct.setShop_price(product.getShop_price());
		productService.updateProduct(queryProduct);
		return "toListProductPage";
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Override
	public Product getModel() {
		return product;
	}
}
