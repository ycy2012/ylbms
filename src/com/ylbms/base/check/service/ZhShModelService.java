package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.ZhShModelDao;
import com.ylbms.base.check.model.CheckZhShuModel;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-26
 */
@Service
@Transactional
public class ZhShModelService {
	@Autowired
	private ZhShModelDao zhShuDao;

	/**
	 * save method
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(CheckZhShuModel entity) {
		zhShuDao.save(entity);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteById(String id) {
		this.zhShuDao.delete(id);
	}

	/**
	 * list
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<CheckZhShuModel> findZhShByPage(Page<CheckZhShuModel> page,
			List<PropertyFilter> filters) {
		return this.zhShuDao.findPage(page, filters);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delByIds(String ids) {
		String delHQL = "delete CheckZhShuModel zId in(" + ids + ")";
		this.zhShuDao.getSession().createQuery(delHQL).executeUpdate();
	}

}
