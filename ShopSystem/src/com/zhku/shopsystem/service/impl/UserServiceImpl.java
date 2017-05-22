package com.zhku.shopsystem.service.impl;

import javax.mail.MessagingException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.UserDao;
import com.zhku.shopsystem.domain.User;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.UserService;
import com.zhku.shopsystem.utils.MD5Utils;
import com.zhku.shopsystem.utils.MailUtils;
import com.zhku.shopsystem.utils.UUIDUtils;


public class UserServiceImpl implements UserService {
	private UserDao userDao;	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false)
	public void regist(User user) throws  MessagingException {
			//1.设置用户的状态:0代表未激活,1代表激活
			user.setState(0);
			//2.设置用户的激活码
			user.setCode(UUIDUtils.getUUID());
			//3.将密码转换成MD5码
			user.setPassword(MD5Utils.md5(user.getPassword()));
			//4.发送激活邮件
			MailUtils.sendMail(user.getEmail(), user.getCode(), user.getName());
			//5.保存用户
			userDao.save(user);
		}
	
		
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public boolean checkUserName(String username) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("username", username));
		return userDao.getByDetachedCriteria(criteria)==null?true:false;
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=MessageException.class)
	public void active(String code) throws MessageException {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("code", code));
		User user = userDao.getByDetachedCriteria(criteria);
		if(user==null){
			//说明此激活码对应的用户不存在,抛出激活失败的异常
			throw new MessageException("激活账户失败,激活码有误");
		}
		//判断账户是否已经激活过
		if(user.getState()==1){
			//说明用户已经激活过,抛出异常
			throw new MessageException("账户已经被激活,请勿重复激活");
		}
		//激活用户
		user.setState(1);
		userDao.update(user);
	}
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true)
	public boolean checkEmail(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("email", email));
		return userDao.getByDetachedCriteria(criteria)==null?true:false;
	}
	

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly=true,rollbackFor=MessageException.class)
	public User login(String username, String password) throws MessageException {
		//根据用户名或者邮箱
		DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.or(Restrictions.eq("username", username),Restrictions.eq("email", username)));
		User queryUser = userDao.getByDetachedCriteria(criteria);
		if(queryUser==null){
			//说明用户不存在
			//抛出用户不存在异常信息
			throw new MessageException("用户不存在");
		}
		//说明用户存在
		//校对密码是否正确
		if(!queryUser.getPassword().equals(password)){
			//校对密码不正确
			//抛出密码不正确的异常信息
			throw new MessageException("密码不正确");
		}
		//说明用户存在且密码正确
		//判断用户是否已经激活
		if(queryUser.getState()==0){
			//说明用户尚未激活
			//抛出用户尚未激活的异常信息
			throw new MessageException("用户尚未激活");
		}
		return queryUser;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
