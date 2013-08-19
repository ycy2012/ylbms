package com.tedmis.location.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedmis.location.dao.TdmisLocationDao;
import com.tedmis.util.DBContextHolder;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-16
 */
@Service
@Transactional(readOnly = true, value = "tdmisTxManager")
public class TdmisLocationService {
	private Logger log = LoggerFactory.getLogger(TdmisLocationDao.class);

	@Autowired
	private TdmisLocationDao tdmislocationDao;

	@Transactional(readOnly = false)
	public Object getTdmisLocation(String id) {
		String querySQL="select t.位置编号,t.位置名称 from tetdmis.bas_位置表 t ";
		Session session=tdmislocationDao.getSession();
		Query query=session.createSQLQuery(querySQL);
		@SuppressWarnings("unchecked")
		List<Object[]> list=query.list();
		for(int i=0,len=list.size();i<len;i++){
			log.info("-------------"+list.get(i)[0].toString());
			log.info("-------------",list.get(i)[1].toString());
		}
		DBContextHolder.clearDB();
		return query.list().size();
	}

}
