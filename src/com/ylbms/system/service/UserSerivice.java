package com.ylbms.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.dao.UserDao;
import com.ylbms.system.model.User;

@Service
@Transactional
public class UserSerivice {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public Page<User> searchUser(final Page<User> page,
			final List<PropertyFilter> filters) {
		return userDao.findPage(page, filters);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void deleteUserByIds(String ids) {
		String delHQL = "delete User where id in (" + ids + ")";
		userDao.createQuery(delHQL);
	}

	/**
	 * 删除一个
	 * 
	 * @param ID
	 */
	@Transactional(readOnly = true)
	public void deleteUser(Long ID) {
		userDao.delete(ID);
	}
	

}
