package com.ylbms.base.check.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.CheckNotesDao;
import com.ylbms.base.check.model.CheckNotes;
import com.ylbms.base.check.model.CheckNotesInfo;
import com.ylbms.base.check.web.controller.NotesModel;
import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.model.SingleInfo;
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

	@Autowired
	private SingleInfoDao singleDao;

	/**
	 * 
	 * @param checkNotes
	 */
	@Transactional(readOnly = false)
	public void saveCheckNotes(CheckNotes master, NotesModel notes) {
		Long i = 1L;
		User user = UserUtils.getUser();
		master.setCreateUser(user); // 添加制作人员信息
		List<CheckNotesInfo> list = new ArrayList<CheckNotesInfo>();
		for (CheckNotesInfo c : notes.getNotes()) {
			c.setCheckNotes(master);
			c.setOrder(i++);
			list.add(c);
		}
		master.setNotesInfo(list);
		checkNotesDao.save(master);
		
		updateSingle(notes.getNotes()); // 更新单件信息
	}

	/**
	 * update single info by checkNotes
	 * 
	 * @param info
	 */
	public void updateSingle(List<CheckNotesInfo> list) {
		for (CheckNotesInfo c : list) {
			SingleInfo single = singleDao.get(c.getSingle().getMid());
			single.setJdtime(c.getJdDate());
			single.setYxTime(c.getYxDate());
		    single.setAzLocation(c.getAzLocation());
			singleDao.save(single);
		}
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

	/**
	 * 通过ID查询检定记录信息
	 * 
	 * @param jdId
	 * @return
	 */
	public CheckNotes getCheckById(String jdId) {
		return checkNotesDao.get(jdId);
	}

}
