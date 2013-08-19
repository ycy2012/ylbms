package com.ylbms.system.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.User;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-14
 */
@Repository("userDao")
public class UserDao extends HibernateDao<User, Long> {

	public void updatePasswordById(String entryptPassword, Long id) {
		User user = get(id);
		user.setPassword(entryptPassword);
		save(user);
	}

	public void updateLoginInfo(String host, Date date, Long id) {
		User user = get(id);
		user.setLoginIP(host);
		user.setLoginDate(date);
		save(user);
	}

	/**
	 * 批量删除
	 * 
	 * @param HQL
	 * @param values
	 * @return
	 */
	public int createQuery(String HQL) {
		return getSession().createQuery(HQL).executeUpdate();
	}
}
