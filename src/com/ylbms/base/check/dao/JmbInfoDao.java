package com.ylbms.base.check.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * 精密表数据访问层
 * @author zhangjl
 * @version 1.0
 * @date 2013-7-26
 */
@Repository
public class JmbInfoDao extends HibernateDao<JmylbModel, Long> {

}
