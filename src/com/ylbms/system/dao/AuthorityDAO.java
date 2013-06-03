package com.ylbms.system.dao;

import org.springframework.stereotype.Component;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Authority;

@Component
public class AuthorityDAO extends HibernateDao<Authority, Long> {

}
