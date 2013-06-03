package com.ylbms.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 取得最新登录ip
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月三十日 16:17:24
 * 
 */
public class GetLasterLoginIPUtil {
	private static final Log log = LogFactory
			.getLog(GetLasterLoginIPUtil.class);

	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		log.debug("request.getHeader(\"x-forwarded-for\")=" + ip);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
			log.debug("request.getHeader(\"X-Forwarded-For\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.debug("request.getHeader(\"Proxy-Client-IP\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			log.debug("request.getHeader(\"WL-Proxy-Client-IP\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			log.debug("request.getHeader(\"HTTP_CLIENT_IP\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			log.debug("request.getHeader(\"HTTP_X_FORWARDED_FOR\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			log.debug("request.getRemoteAddr()=" + ip);
		}
		return ip;
	}
}
