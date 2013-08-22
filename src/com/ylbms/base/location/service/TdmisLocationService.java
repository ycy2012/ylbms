package com.ylbms.base.location.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.location.dao.TdmisLocationDao;
import com.ylbms.base.location.model.TdmisLocation;
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
	public List<TdmisLocation> getTdmisLocation() {
		// 存放返回数组
		@SuppressWarnings("unchecked")
		List<TdmisLocation> list = (List<TdmisLocation>) CacheUtils
				.get("tdmisLocation");
		if (null == list) {
			DetachedCriteria dc = tdmislocationDao.createDetachedCriteria();
			dc.addOrder(Order.asc("wzCc"));
			list = tdmislocationDao.find(dc);
			CacheUtils.put("tdmisLocation", list);
		}
		return list;
	}
}
