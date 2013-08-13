package com.ylbms.base.single.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylbms.base.single.dao.StateDao;
import com.ylbms.base.single.model.StateInfo;
import com.ylbms.common.utils.CacheUtils;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-12
 */
@Service
@Transactional
public class StateService {
	@Autowired
	private StateDao stateDao;

	/**
	 * 返回状态信息 maps
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getStateMapBystatus() {
		List<Map<String, Object>> states = Lists.newArrayList();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> maps = (List<Map<String, Object>>) CacheUtils
				.get("state");
		// 如果不为空则直接返回
		if (maps == null) {
			DetachedCriteria dc = stateDao.createDetachedCriteria();
			dc.add(Restrictions.eq("status", "0"));
			dc.addOrder(Order.asc("id"));

			List<StateInfo> list = stateDao.find(dc);
			for (StateInfo s : list) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("value", s.getId());
				map.put("text", s.getStateName());
				states.add(map);
			}
			CacheUtils.put("state", states);
			return states;
		} else {
			return maps;
		}
	}
	
}
