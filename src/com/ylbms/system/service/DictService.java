package com.ylbms.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.dao.DictDao;
import com.ylbms.system.model.Dict;

/**
 * 字典 信息的Service类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-4
 */
@Component
@Transactional
public class DictService {

	@Autowired
	DictDao dictDao;

	public Page<Dict> searchUser(Page<Dict> page, List<PropertyFilter> filters) {
		return dictDao.findPage(page, filters);
	}
	
}
