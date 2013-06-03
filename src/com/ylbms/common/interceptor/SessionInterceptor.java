package com.ylbms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Session拦截器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月三十日 12:19:55
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	private static final Log log = LogFactory.getLog(SessionInterceptor.class);

	/**
	 * 可以根据ex是否为null判断是否发生了异常，进行日志记录。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 有机会修改ModelAndView
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("======================拦截器=======================");
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {  
            if(request.getSession() == null){  
            modelAndView.setViewName("/a/login");
            }  
        }  
		
	}

	/**
	 * 可以进行编码、安全控制等处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

}
