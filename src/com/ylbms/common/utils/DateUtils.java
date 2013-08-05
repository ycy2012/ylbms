package com.ylbms.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-5
 */
public class DateUtils {
	/**
	 * 根据格式返回当前时间信息
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getDate(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
}
