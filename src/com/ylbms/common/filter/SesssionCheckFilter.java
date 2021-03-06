package com.ylbms.common.filter;



import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.env.WebEnvironment;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.util.WebUtils;

/**
 * 系统Session检测过滤器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
public class SesssionCheckFilter extends UserFilter {

	private static final Log log = LogFactory.getLog(SesssionCheckFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest
				.getHeader("X-Requested-With"))
				|| request.getParameter("ajax") != null) {
			log.info("It's an ajax request!");
			httpServletResponse.addHeader("WWW-Authentication", "ACME-AUTH");
			httpServletRequest.getRequestDispatcher("/index/timeout").forward(
					httpServletRequest, httpServletResponse);
		} else {
			log.info("It's not an ajax request!");
			redirectToLogin(httpServletRequest, httpServletResponse);
		}
		return false;
	}


}
