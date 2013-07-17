package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.CheckNotesDao;
import com.ylbms.base.check.model.CheckNotes;
import com.ylbms.base.check.model.CheckNotesInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-16
 */
@Service
@Transactional
public class CheckNotesService {

	@Autowired
	private CheckNotesDao checkNotesDao;

	/**
	 * 
	 * @param checkNotes
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateCheckNotes(CheckNotes checkNotes) {
		checkNotesDao.save(checkNotes);
	}

	/**
	 * 
	 * @param jdId
	 */
	@Transactional(readOnly = false)
	public void deleteNotesById(String jdId) {
		checkNotesDao.delete(jdId);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<CheckNotes> findPage(Page<CheckNotes> page,
			List<PropertyFilter> filters) {
		return checkNotesDao.findPage(page, filters);
	}

}
