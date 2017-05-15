package com.zhku.shopsystem.web.action;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.code.kaptcha.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.UserService;
import com.zhku.shopsystem.utils.MD5Utils;
import com.zhku.shopsystem.utils.MailUtils;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	private UserService userService;
	
	//接收验证码字段
	private String captcha;
	
	//接收是否自动登录字段
	private boolean autologin;
	

	/**
	 * 执行跳转到登录页面的方法
	 * @return
	 * @throws Exception
	 */
	public String loginPage()throws Exception{
		//判断当前是否已经登录
		User user = (User) ActionContext.getContext().getSession().get("existUser");
		if(user!=null){
			//说明用户已经登录了
			return "loginSuccess";
		}
		return "loginPage";
	}
	
	@InputConfig(resultName="loginInput")
	public String login(){
		//校验验证码是否已经过期
		Date captchaDate=(Date) ActionContext.getContext().getSession().get(Constants.KAPTCHA_SESSION_DATE);
		if(captchaDate==null){
			//说明没有生成验证码或者其他原因导致session中没有验证码信息
			setActionErrors(Arrays.asList("验证码已过期"));
			return "loginInput";
		}
		long capthchaTime=captchaDate.getTime();
		long nowTime=System.currentTimeMillis();
		//如果验证码时间超过60秒算超时
		if(nowTime-capthchaTime>=60*1000){
			setActionErrors(Arrays.asList("验证码已过期"));
			return "loginInput";
		}
		//验证验证码是否正确
		String sessionCaptcha=(String) ActionContext.getContext().getSession().get(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equals(sessionCaptcha)){
			setActionErrors(Arrays.asList("验证码不正确"));
			return "loginInput";
		}
		User existUser=null;
		try {
			existUser=userService.login(user.getUsername(),MD5Utils.md5(user.getPassword()));
		} catch (MessageException e) {
			List<String> errorMessages=new ArrayList<String>();
			errorMessages.add(e.getMessage());
			setActionErrors(errorMessages);
			e.printStackTrace();
			return "loginInput";
		}
		//登录成功
		//将登录成功的用户保存到session域中
		ActionContext.getContext().getSession().put("existUser", existUser);
		
		//判断是否需要自动登录
		if(autologin){
			//需要自动登录将登录的用户的信息保存到cookie中
			String autoLoginCookie=existUser.getUsername()+":"+existUser.getPassword();
			Cookie cookie=new Cookie("autologin", autoLoginCookie);
			//cookie信息保存30天
			cookie.setMaxAge(24*3600*30);
			//cookie访问路径为当前应用/ShopSystem
			cookie.setPath(ServletActionContext.getRequest().getContextPath());
			ServletActionContext.getResponse().addCookie(cookie);
		}
		return "loginSuccess";
	}
	
	/**
	 * 执行跳转到注册页面的方法
	 * @return 
	 */
	public String registPage() throws Exception{
		return "registPage";
	}
	
	/**
	 * 注册用户的方法
	 * @return
	 */
	@InputConfig(resultName="registInput")
	public String regist(){
		//判断用户名是否已经存在
		boolean userExistOrNot=userService.checkUserName(user.getUsername());
		//判断邮箱是否已经被注册
		boolean emailRegistOrNot=userService.checkEmail(user.getEmail());
		if(!userExistOrNot||!emailRegistOrNot){
			Map<String,List<String>> errorMap=new HashMap<String,List<String>>();
			if(!userExistOrNot){
				// 用户已存在
				// 设置用户已存在信息提示
				errorMap.put("username", Arrays.asList("用户名已存在"));
			}
			if(!emailRegistOrNot){
				//邮箱已被注册
				//设置邮箱已被注册提示信息
				errorMap.put("email", Arrays.asList("邮箱已被注册"));
			}
			setFieldErrors(errorMap);
			return "registInput";
		}
		
		//说明用户名不存在,邮箱没有被注册,调用userService注册用户
		try {
			userService.regist(user);
		}  catch (MessagingException e) {
			//如果捕获了发送邮箱的异常
			Map<String,List<String>> errorMap=new HashMap<String,List<String>>();
			errorMap.put("email", Arrays.asList("发送邮件失败"));
			setFieldErrors(errorMap);
			e.printStackTrace();
			return "registInput";
		}
		//设置注册成功提示信息
		List<String> messages=new ArrayList<String>();
		String url=MailUtils.getEmailUrl(user.getEmail());
		String message;
		if(url==null){
			message="注册成功,前往邮箱激活";
		}else{
			message="注册成功,<a href='"+url+"'>点击前往邮箱激活</a>";
		}
		messages.add(message);
		setActionMessages(messages);
		//转发到注册成功页面
		return SUCCESS;
		
	}
	
	/**
	 * 校验用户名是否已经存在的方法
	 * @return
	 */
	public String checkUserName() throws Exception{
		boolean result=userService.checkUserName(user.getUsername());
		ServletActionContext.getResponse().getWriter().print(result);
		return null;
	}
	/**
	 * 校验邮箱是否已经被注册
	 * @return 
	 * @throws Exception
	 */
	public String checkEmail()throws Exception{
		boolean result=userService.checkEmail(user.getEmail());
		ServletActionContext.getResponse().getWriter().print(result);
		return null;
	}
	
	public String active(){
		List<String> messages=new ArrayList<String>();
		try {
			userService.active(user.getCode());
			messages.add("激活账户成功,请去登录");
		} catch (MessageException e) {
			messages.add(e.getMessage());
			e.printStackTrace();
		}
		setActionMessages(messages);
		return SUCCESS;
	}
	
	/**
	 * 用户退出登录的方法
	 * @return
	 */
	public String logout(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		if(session!=null&&session.getAttribute("existUser")!=null){
			//说明用户已经登录
			//销毁session
			session.invalidate();
			//发送cookie销毁客户端保存的cookie,取消自动登录功能
			Cookie autologinC=new Cookie("autologin","");
			autologinC.setPath(ServletActionContext.getRequest().getContextPath());
			autologinC.setMaxAge(0);
			ServletActionContext.getResponse().addCookie(autologinC);
		}
		return "logoutSuccess";
	}
	
	@Override
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public boolean isAutologin() {
		return autologin;
	}

	public void setAutologin(boolean autologin) {
		this.autologin = autologin;
	}
}
