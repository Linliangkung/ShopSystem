package com.zhku.shopsystem.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailUtils {
	
	
	
	
	public static String ACCOUNT="13143350142@sina.cn";
	public static String PASSWORD="linliangjin";
	public static String SUBJECT="来自SHOP的官网激活邮件";
	public static String IP_ADDRESS="10.50.63.51";
	public static String ACTIVE_URL="http://10.50.63.51/ShopSystem/user_active.action?code=";
	
	/**
	 * 发送邮件
	 * @param to 收件人地址
	 * @param code 注册用户的激活码
	 * @throws MessagingException 
	 */
	public static void sendMail(String to,String code,String name) throws MessagingException{
		//1.创建Properties 对象，设置连接stmp服务器的参数
		Properties properties=new Properties();
		
		//设置debug调试
		properties.setProperty("mail.debug", "true");
		//设置服务器需要身份验证
		properties.setProperty("mail.smtp.auth", "true");
		//设置邮件stmp服务器主机名
		properties.setProperty("mail.host", "smtp.sina.cn");
		//设置发送邮件协议名称  
		properties.setProperty("mail.transport.protocol", "smtp");  
		
		//2.创建Session对象,与邮箱stmp服务器的连接
		Session session=Session.getInstance(properties);
		
		//3.创建邮件Message对象
		Message message=new MimeMessage(session);
		//设置主题(标题)
		message.setSubject(SUBJECT);
		//设置正文内容
		String url=MailUtils.ACTIVE_URL+code;
		message.setText("恭喜您,"+name+",注册SHOP账户成功!请点击链接去激活你的账户,如果无法点击请复制链接到浏览器访问,链接如下:"+url);
		//设置发件人
		message.setFrom(new InternetAddress(MailUtils.ACCOUNT));
		//4.创建传输对象Transport 
		Transport transport=session.getTransport();
		//设置发件人的账户和密码
		transport.connect(MailUtils.ACCOUNT, MailUtils.PASSWORD);
		//发送邮件
		transport.sendMessage(message, new Address[]{new InternetAddress(to)});
	}
	
	
	public static String getEmailUrl(String email){
		String result =null;
		//获取邮箱地址@后面的数据
		String postfix=email.substring(email.indexOf('@'));
		switch(postfix){
		case "@163.com":
		case "@126.com":
		case "@163.net":
		case "@188.com":
			result="http://mail.163.com/";
			break;
		
		case "@yeah.net":
			result="http://www.yeah.net/";
			break;
		case "@gmail.com":
		case "@googlemail.com":
			result="https://www.google.com/intl/zh-CN_cn/gmail/about/#";
			break;
		case "@sina.com":
			result="http://mail.sina.com.cn/";
			break;
		case "@qq.com":
			result="https://mail.qq.com/";
			break;
		case "@sohu.com":
			result="https://mail.sohu.com/fe/#/login";
			break;
		}
		return result;
	}
	
}
