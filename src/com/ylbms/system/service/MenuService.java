package com.ylbms.system.service;

import java.util.List;

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
@Transactional(readOnly = true)
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

	/**
	 * get all menus
	 * 
	 * @return
	 */
	public List<Menu> getAll() {
		return menuDao.getAll();
	}

	/**
	 * 查询菜单信息根据用户ID
	 * 
	 * @param id
	 * @return
	 */
	public List<Menu> findByUserId(Long id) {
		return menuDao.findByUserId(id);
	}

}
