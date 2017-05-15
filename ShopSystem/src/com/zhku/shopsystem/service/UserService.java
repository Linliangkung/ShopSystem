package com.zhku.shopsystem.service;

import javax.mail.MessagingException;

import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;

public interface UserService {
	/**
	 * 注册用户
	 * @param user 用户
	 * @throws MessageException 
	 * @throws MessagingException 
	 */
	void regist(User user) throws  MessagingException;
	
	/**
	 * 校验用户是否存在
	 * @param username 用户名
	 * @return true说明用户名不存在,false说明用户名已经存在
	 */
	boolean checkUserName(String username);
	
	/**
	 * 激活用户
	 * @param code 用户激活码
	 * @throws MessageException 
	 */
	void active(String code) throws MessageException;
	
	/**
	 * 校验邮箱是否已经被注册
	 * @param email 邮箱
	 * @return true说明邮箱没有被注册,false说明邮箱已经被注册
	 */
	boolean checkEmail(String email);
	
	/**
	 * 用户登录
	 * @param username 用户名或者邮箱
	 * @param password 密码(密码必须已经通过md5加密)
	 * @throws MessageException 
	 * @return User 登录成功的用户
	 */
	User login(String username, String password) throws MessageException;

}
