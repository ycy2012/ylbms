package com.tedmis.location.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tedmis.location.dao.TdmisLocationDao;
import com.tedmis.location.model.TdmisLocationDTO;
import com.tedmis.location.model.TreeBean;
import com.tedmis.util.DBContextHolder;
import com.ylbms.common.utils.CacheUtils;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-16
 */
@Service
@Transactional(readOnly = true)
public class TdmisLocationService {
	private Logger log = LoggerFactory.getLogger(TdmisLocationDao.class);

	@Autowired
	private TdmisLocationDao tdmislocationDao;

	/**
	 * 查询位置信息
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<TdmisLocationDTO> getTdmisLocation() {
		// 存放返回数组
		List<TdmisLocationDTO> list = Lists.newArrayList();
		if (null == list || list.isEmpty()) {
			String querySQL = "select t.位置编号,t.位置名称,t.位置层次  from tetdmis.bas_位置表 t order by t.位置层次 ";
			Session session = tdmislocationDao.getSession();
			Query query = session.createSQLQuery(querySQL);
			@SuppressWarnings("unchecked")
			List<Object[]> obj = query.list();
			for (int i = 0, len = obj.size(); i < len; i++) {
				TdmisLocationDTO tdmis = new TdmisLocationDTO();
				tdmis.setWzId(obj.get(i)[0].toString());
				tdmis.setWzName(obj.get(i)[1].toString());
				tdmis.setWzCc(obj.get(i)[2].toString());
				list.add(tdmis);
			}
			CacheUtils.put("treeList", list);
		}
		DBContextHolder.clearDB(); // 清除
		return list;
	}
}
