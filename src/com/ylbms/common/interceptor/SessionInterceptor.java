package com.ylbms.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.system.security.SystemRealm.Principal;

/**
 * Session拦截器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月三十日 12:19:55
 */

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static final Log log = LogFactory.getLog(SessionInterceptor.class);

	private static final String IGNORE = "^.+\\.(png|gif|jpg|js|css|jspx|jpeg|swf|ico)$";

	/**
	 * 可以根据ex是否为null判断是否发生了异常，进行日志记录。 在Controller方法后进行拦截
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
		// 后台session控制
		/**
		 * <h1>温馨提示：<h1>
		 * 今天下午就开始放假，祝各位同志端午节快乐，注意安全，特别注意如下三点； 1、外出人员给办公室说一下，留好联系方式.
		 * 2、各项目组负责人，请安排好假日值班事宜. 3、值班人，请准时上下班，注意交通安全.
		 * 4、大家在休息期间，时刻与公司保持联系（特别是大二线项目、卫星小站项目组）.
		 */
	}

	/**
	 * 可以进行编码、安全控制等处理 在Controller方法前进行拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		boolean flag = true;
		response.setCharacterEncoding("UTF-8");
		Principal principal = (Principal) SecurityUtils.getSubject()
				.getPrincipal();
		String currentURL = request.getRequestURI();
		if (currentURL.equalsIgnoreCase("/ylbms/a/login")
				|| currentURL.equalsIgnoreCase("/ylbms/login_dialog.html")
				|| currentURL.matches(IGNORE) || request.getSession() != null) {

			flag = true;
		} else {
			response.sendRedirect(request.getContextPath() + "/a/login");
			flag = false;
		}
		if (request != null
				&& "XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With"))
				|| request.getParameter("ajax") != null) {
			if (request.getSession() == null || principal == null) {
				Map<String, Object> ret = DwzUtil.dialogAjaxDoneTimeOut();
				response.getWriter().print(JSON.toJSON(ret));
				flag = false;
			}
		}
		return flag;
	}
}
