package com.zhku.shopsystem.service;

import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.exception.MessageException;

public interface SellerService {
	/**
	 * 根据商家账户判断该商家账户是否已经存在
	 * @param saccount 商家账户
	 * @return true说明商家账户不存在,false说明商家账户已经存在
	 */
	boolean checkSaccount(String saccount);
	
	/**
	 * 注册商家
	 * @param seller
	 */
	void regist(Seller seller);
	
	/**
	 * 根据商家账号和密码查询商家
	 * @param saccount 商家账号
	 * @param password 商家密码
	 * @return
	 * @throws MessageException 
	 */
	Seller login(String saccount, String password) throws MessageException;

}
