package com.ylbms.common.web;

import org.springframework.web.servlet.ModelAndView;

/**
 * 基控制器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月三十日 12:48:47
 * 
 */
public abstract class BaseController {
	
	public static final int OK = 200;
	public static final int FAIL = 300;
	public static final int TIMEOUT = 301;

	protected ModelAndView ajaxDone(int statusCode, String message) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		return mav;
	}

	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(OK, message);
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(FAIL, message);
	}
	
	protected ModelAndView ajaxDoneTimeOut(String message){
		return ajaxDone(TIMEOUT, message);
	}

}
