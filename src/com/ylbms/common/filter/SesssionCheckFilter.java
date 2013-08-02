package com.ylbms.common.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.filter.RequestContextFilter;

import com.alibaba.fastjson.JSON;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.system.security.SystemRealm.Principal;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
public class SesssionCheckFilter extends RequestContextFilter {

	private static final Log log = LogFactory.getLog(SesssionCheckFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Principal principal = (Principal) SecurityUtils.getSubject()
				.getPrincipal(); // 当前的用户信息
		String currentURL = request.getRequestURI();

		log.info("--------" + currentURL);

		if (request != null
				&& "XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With"))
				|| request.getParameter("ajax") != null) {
			if (request.getSession() == null || principal == null) {
				Map<String, Object> ret = DwzUtil.dialogAjaxDoneTimeOut();
				response.getWriter().print(JSON.toJSON(ret));
			}
		}
		filter.doFilter(request, response);
	}

}
