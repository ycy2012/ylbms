package com.ylbms.system.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Org;

@Repository
public class OrgDAO extends HibernateDao<Org, Long> {

}
