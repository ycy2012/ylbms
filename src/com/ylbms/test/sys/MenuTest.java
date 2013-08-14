package com.ylbms.test.sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.system.model.Menu;
import com.ylbms.system.model.User;
import com.ylbms.system.service.MenuService;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
		"classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class MenuTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private MenuService mService;

	@Test
	public void createMenu() {
		Menu menu1 = mService.getMenuById(1l);
		for (int i = 0; i < 100; i++) {
			Menu menu = new Menu();
			menu.setName("test");
			menu.setParentIds("1,2,3");
			menu.setParent(menu1);
			menu.setUser(new User(1l));
			menu.setSort(10);

			mService.saveMenu(menu);
		}
	}

}
