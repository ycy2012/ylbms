package com.ylbms.base.single.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.base.single.model.StateInfo;
import com.ylbms.common.orm.hibernate.HibernateDao;
/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-12
 */
@Repository
public class StateDao extends HibernateDao<StateInfo, String> {

}
