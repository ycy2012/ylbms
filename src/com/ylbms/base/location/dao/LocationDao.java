package com.ylbms.base.location.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.base.location.model.Location;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * location Dao class
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-6
 */
@Repository
public class LocationDao extends HibernateDao<Location, Long> {

}
