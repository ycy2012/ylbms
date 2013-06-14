package com.ylbms.base.bill.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@Repository
public class BillHeadDao extends HibernateDao<BillHeadModel, String> {

}
