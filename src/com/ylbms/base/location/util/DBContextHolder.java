
package com.ylbms.base.location.util;

/**
 * 动态切换数据源工具类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-16
 */
public class DBContextHolder {

	public static final String DB_YLBMS = "ylbmDataSource";
	public static final String DB_TDMIS = "wzDataSource";

	private static final ThreadLocal<Object> CON_LOCAL = new ThreadLocal<Object>();

	public static void setDB(String type) {
		CON_LOCAL.set(type);
	}

	public static String getDB() {
		return (String) CON_LOCAL.get();
	}

	/**
	 * 从ThreadLocal中移除DB
	 */
	public static void clearDB() {
		CON_LOCAL.remove();
	}
}
