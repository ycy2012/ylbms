package com.ylbms.base.single.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 */
@Repository("SingleDao")
public class SingleInfoDao extends HibernateDao<SingleInfo, String> {

}
