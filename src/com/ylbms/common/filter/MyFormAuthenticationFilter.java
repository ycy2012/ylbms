package com.ylbms.common.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 重写FormAuthenticationFilter部分方法 为了便于解决自己的Session过期处理
 * @author JackLiang
 * @version 1.0
 * @date 2013-9-6
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Log log = LogFactory.getLog(MyFormAuthenticationFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {

		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

		if (isLoginRequest(request, response)) {
			// true or false
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.info("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			/**
			 * 解决重新登录和Session 过期
			 */
			if (httpServletRequest.getRequestURI().endsWith("/index/relogin")) {
				httpServletRequest.getRequestDispatcher("/index/relogin")
						.forward(httpServletRequest, httpServletResponse);
				return false;
			}
			if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest
					.getHeader("X-Requested-With"))|| request.getParameter("ajax") != null) {
				log.info("It's an ajax request!");
				
				httpServletResponse
						.addHeader("WWW-Authentication", "ACME-AUTH");
				httpServletRequest.getRequestDispatcher("/index/timeout")
						.forward(httpServletRequest, httpServletResponse);
				
			} else {
				log.info("It's not an ajax request!");
				
				saveRequestAndRedirectToLogin(request, response);
			}
			return false;
		}
	}
	
}
