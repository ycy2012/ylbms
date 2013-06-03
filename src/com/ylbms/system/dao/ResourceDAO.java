package com.ylbms.system.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Resource;

//@Component("menuDao")
public class ResourceDAO extends HibernateDao<Resource, Long> {

	public List<Resource> findAllMenus() {
		return getAll();
	}

	public List<Resource> findByUserId(Long id) {

		return null;
	}

}
