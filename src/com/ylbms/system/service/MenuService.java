package com.ylbms.system.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.system.dao.MenuDao;
import com.ylbms.system.model.Menu;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-5-31
 */
@Service
@Transactional
public class MenuService {

	private static final Log log = LogFactory.getLog(MenuService.class);

	@Autowired
	MenuDao menuDao;


	public Menu getMenuById(Long id) {
		return menuDao.get(id);
	}

	public void deleteMenu(Long id) {
		menuDao.delete(id);
	}

	/**
	 * 
	 * @param ids
	 */
	public void delByIds(String ids) {
		String delHQL = "delete Menu where id in(" + ids + ")";
		menuDao.createQuery(delHQL);
	}


}
