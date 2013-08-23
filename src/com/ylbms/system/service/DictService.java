package com.ylbms.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
@Transactional
public class DictService {

	@Autowired
	DictDao dictDao;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Dict> searchUser(Page<Dict> page, List<PropertyFilter> filters) {
		return dictDao.findPage(page, filters);
	}

	/**
	 * save dictInfo
	 * 
	 * @param dict
	 */
	public void saveDict(Dict dict) {
		dictDao.save(dict);
	}

	/**
	 * get dictInfo by ID
	 * 
	 * @param Id
	 * @return
	 */
	public Dict getDictById(Long Id) {
		return dictDao.get(Id);
	}

	/**
	 * delete dictInfo by ID
	 * 
	 * @param id
	 */
	public void deleteDictByID(Long id) {
		dictDao.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delByIds(String ids) {
		String delHQL = "delete Dict where id in(" + ids + ")";
		dictDao.getSession().createQuery(delHQL).executeUpdate();
	}
}
