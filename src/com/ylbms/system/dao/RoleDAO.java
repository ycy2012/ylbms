package com.ylbms.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Role;

@Repository("roleDao")
public class RoleDAO extends HibernateDao<Role, Long> {

	public List<Role> findByUserId(Long id) {
		return null;
	}

}
