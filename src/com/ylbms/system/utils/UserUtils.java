package com.ylbms.system.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Maps;
import com.ylbms.common.utils.spring.SpringContextHolder;
import com.ylbms.system.dao.MenuDao;
import com.ylbms.system.dao.UserDao;
import com.ylbms.system.model.Menu;
import com.ylbms.system.model.User;
import com.ylbms.system.security.SystemRealm.Principal;
import com.ylbms.system.service.MenuService;
import com.ylbms.system.service.SystemService;
import com.ylbms.system.service.UserSerivice;

/**
 * 用户工具类
 * 
 * @author JackLiang
 * @version 1.0
 */
public class UserUtils  {

	private static final Log log = LogFactory.getLog(UserUtils.class);

	public static final String CACHE_USER = "user";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";

	private static UserSerivice userService=SpringContextHolder.getBean(UserSerivice.class);
	private static MenuService menuService=SpringContextHolder.getBean(MenuService.class);

	public static User getUser() {
		User user = (User) getCache(CACHE_USER);
		if (null == user) {
			Principal principal = (Principal) SecurityUtils.getSubject()
					.getPrincipal();
			if (null != principal) {
				user = userService.findUniqueBy("loginName",
						principal.getLoginName());
				putCache(CACHE_USER, user);
			}
		}
		if (user == null) {
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}

	public static User getUser(boolean isRefresh) {
		if (isRefresh) {
			removeCache(CACHE_USER);
		}
		return getUser();
	}

	/**
	 * get menu Info
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList() {
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
		if (menuList == null) {
			User user = getUser();
			if (user.isAdmin()) {
				menuList = menuService.getAll();
			} else {
				menuList = menuService.findByUserId(user.getId());
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	// public static List<Office> getOfficeList() {
	// @SuppressWarnings("unchecked")
	// List<Office> officeList = (List<Office>) getCache("officeList");
	// if (officeList == null) {
	// User user = getUser();
	// if (user.isAdmin()) {
	// officeList = officeDao.findAllList();
	// } else {
	// officeList = officeDao.findAllChild(user.getOffice().getId(),
	// "%," + user.getOffice().getId() + ",%");
	// }
	// putCache("officeList", officeList);
	// }
	// return officeList;
	// }

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj == defaultValue ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}

	private static Map<String, Object> getCacheMap() {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			return principal != null ? principal.getCacheMap() : map;
		} catch (UnavailableSecurityManagerException e) {
			return map;
		}
	}

}
