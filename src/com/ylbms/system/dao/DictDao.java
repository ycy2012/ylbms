package com.ylbms.system.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Dict;
/**
 * 字典表dao类
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-4
 */
@Repository
public class DictDao extends HibernateDao<Dict, Long> {

}
