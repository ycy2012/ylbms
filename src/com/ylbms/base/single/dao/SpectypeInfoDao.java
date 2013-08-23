package com.ylbms.base.single.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-5
 */
@Repository("spectypeDao")
public class SpectypeInfoDao extends HibernateDao<SpectypeInfo, Long> {
	/**
	 * 批量删除
	 * @param ids
	 */
	public int delSpectypeInfo(String HQL) {
		return getSession().createQuery(HQL).executeUpdate();
	}
	
}

