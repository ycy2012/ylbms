package com.ylbms.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
@Transactional
public class RoleService {

	@Autowired
	RoleDAO roleDao;

	@Transactional(readOnly = true)
	public Page<Role> searchUser(final Page<Role> page,
			final List<PropertyFilter> filters) {
		return roleDao.findPage(page, filters);
	}

	/**
	 * 根据roleID来查询menu信息
	 * 
	 * @return
	 */
	public String getMenuByRoleID(Long id) {
		Role role = roleDao.get(id);
		List<Long> ids = role.getMenuIdList();
		String value = null;
		for (int i = 0, len = ids.size(); i < len; i++) {
			value += (ids.get(i).toString() + ",");
		}
		return value == null ? "" : value;
	}
}
