package com.zhku.shopsystem.service;

import com.zhku.shopsystem.domain.Admin;
import com.zhku.shopsystem.exception.MessageException;

public interface AdminService {
	
	/**
	 * 管理员根据账号和密码进行登录
	 * @param aaccount 管理员账号
	 * @param apassword 管理员密码
	 * @return
	 * @throws MessageException 
	 */
	Admin login(String aaccount, String apassword) throws MessageException;

}
