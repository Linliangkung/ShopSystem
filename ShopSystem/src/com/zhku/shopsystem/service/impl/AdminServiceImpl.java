package com.zhku.shopsystem.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.AdminDao;
import com.zhku.shopsystem.domain.Admin;
import com.zhku.shopsystem.domain.Seller;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public Admin login(String aaccount, String apassword) throws MessageException {
		// 根据商家账户查询商家
		DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
		criteria.add(Restrictions.eq("aaccount", aaccount));
		Admin queryAdmin = adminDao.getByDetachedCriteria(criteria);
		if (queryAdmin == null) {
			// 说明商家不存在
			throw new MessageException("账户不存在");
		}
		// 校验密码是否正确
		if (!queryAdmin.getApassword().equals(apassword)) {
			// 说明密码不一致
			throw new MessageException("密码不正确");
		}
		return queryAdmin;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}
