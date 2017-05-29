package com.zhku.shopsystem.service.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.SellerDao;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.SellerService;
import com.zhku.shopsystem.utils.MD5Utils;

public class SellerServiceImpl implements SellerService {
	
	private SellerDao sellerDao;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public boolean checkSaccount(String saccount) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Seller.class);
		criteria.add(Restrictions.eq("saccount", saccount));
		return sellerDao.getByDetachedCriteria(criteria)==null?true:false;
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void regist(Seller seller) {
		//1.将密码转换成md5码
		seller.setSpassword(MD5Utils.md5(seller.getSpassword()));
		//2.设置注册时间
		seller.setRegistdate(new Date());
		//3.保存商家
		sellerDao.save(seller);
	}
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public Seller login(String saccount, String password) throws MessageException{
		//根据商家账户查询商家
		DetachedCriteria criteria=DetachedCriteria.forClass(Seller.class);
		criteria.add(Restrictions.eq("saccount", saccount));
		Seller querySeller = sellerDao.getByDetachedCriteria(criteria);
		if(querySeller==null){
			//说明商家不存在
			throw new MessageException("账户不存在");
		}
		//校验密码是否正确
		if(!querySeller.getSpassword().equals(password)){
			//说明密码不一致
			throw new MessageException("密码不正确");
		}
		return querySeller;
	}
	
	public void setSellerDao(SellerDao sellerDao) {
		this.sellerDao = sellerDao;
	}
}
