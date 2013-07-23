package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.CheckNotesDao;
import com.ylbms.base.check.model.CheckNotes;
import com.ylbms.base.check.model.CheckNotesInfo;
import com.ylbms.base.check.web.controller.NotesModel;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.model.User;
import com.ylbms.system.utils.UserUtils;

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
	public void saveCheckNotes(CheckNotes checkNotes, NotesModel notes) {
		User user = UserUtils.getUser();
		checkNotes.setCreateUser(user); // 添加制作人员信息
		for (CheckNotesInfo c : notes.getNotes()) {
			c.setCheckNotes(checkNotes);
		}
		checkNotes.setNotesInfo(notes.getNotes());
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

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void delByIds(String ids) {
		String delHQL = "delete CheckNotes where jdID in(" + ids + ")";
		checkNotesDao.getSession().createQuery(delHQL).executeUpdate();
	}

}
