package com.ylbms.common.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 基控制器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月三十日 12:48:47
 * 
 */
public abstract class BaseController {

	/**
	 * 初始化时间格式
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 转向
	 * 
	 * @param url
	 * @return 返回类型：String
	 */
	public String forward(String url) {
		return "forward:" + url;
	}

	/**
	 * 重定向
	 * 
	 * @param url
	 * @return 返回类型：String
	 */
	public String redirect(String url) {
		return "redirect:" + url;
	}
}
