package com.zhku.shopsystem.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.web.context.request.NativeWebRequest;

import com.zhku.shopsystem.dao.CategorySecondDao;
import com.zhku.shopsystem.domain.CategorySecond;

public class CategorySecondDaoImpl extends BaseDaoImpl<CategorySecond> implements CategorySecondDao {
	@Override
	public void deleteByCid(Integer cid) {
		getHibernateTemplate().execute(new HibernateCallback<Void>() {
			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				String hql="DELETE FROM CategorySecond cs WHERE cs.category.cid=?";
				Query query = session.createQuery(hql);
				query.setInteger(0, cid);
				query.executeUpdate();
				return null;
			}
		});
	}
}
