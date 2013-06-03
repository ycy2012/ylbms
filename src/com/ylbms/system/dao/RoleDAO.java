package com.ylbms.system.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Role;

@Component("roleDao")
public class RoleDAO extends HibernateDao<Role, Long> {

	public List<Role> findByUserId(Long id) {
		return null;
	}

}
