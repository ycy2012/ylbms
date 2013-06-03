package com.ylbms.system.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.dao.MenuDao;
import com.ylbms.system.model.Menu;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-5-31
 */
@Component
@Transactional
public class MenuService {

	private static final Log log = LogFactory.getLog(MenuService.class);

	@Autowired
	MenuDao menuDao;

	/**
	 * save menu method
	 * 
	 * @param menu
	 */
	public void saveMenu(Menu menu) {
		menuDao.save(menu);
	}

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
