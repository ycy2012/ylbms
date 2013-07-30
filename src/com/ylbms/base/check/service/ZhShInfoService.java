package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.ZhShInfosDao;
import com.ylbms.base.check.model.ZhShInfosModel;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-30
 */
@Service
@Transactional
public class ZhShInfoService {

	@Autowired
	private ZhShInfosDao zsInfoDao;

	/**
	 * list page
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<ZhShInfosModel> findPage(Page<ZhShInfosModel> page,
			List<PropertyFilter> filters) {
		return zsInfoDao.findPage(page, filters);
	}

}
