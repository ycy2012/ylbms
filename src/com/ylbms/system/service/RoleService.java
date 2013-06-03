package com.ylbms.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.dao.RoleDAO;
import com.ylbms.system.model.Role;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-3
 */
@Repository
public class RoleService {

	@Autowired
	RoleDAO roleDao;

	

	@Transactional(readOnly = true)
	public Page<Role> searchUser(final Page<Role> page,
			final List<PropertyFilter> filters) {
		return roleDao.findPage(page, filters);
	}
}
