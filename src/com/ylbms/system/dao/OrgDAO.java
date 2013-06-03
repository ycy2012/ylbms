package com.ylbms.system.dao;

import org.springframework.stereotype.Component;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Org;

@Component
public class OrgDAO extends HibernateDao<Org, Long> {

}
