package com.ylbms.system.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;
import com.ylbms.system.model.User;
import com.ylbms.system.utils.UserUtils;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * @author jackLiang
 * @date 2013年9月5日 12:58:38
 * @version 1.0
 */
@Controller
@RequestMapping("/index")
public class LoginController extends BaseController {
	private static final Log log = LogFactory.getLog(LoginController.class);

	/**
	 * 登录系统
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getId() != null) {
			return "redirect:/index";
		}
		return "sysLogin";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = UserUtils.getUser();
		if (user.getId() != null) {
			return redirect("/index");
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				userName);
		return "sysLogin";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresUser
	@RequestMapping(value = "")
	public String index(HttpServletRequest request,HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (user.getId() == null) {
			return redirect("/index/login");
		}
		log.debug("登录成功！");
		// 登录成功
		return "sysIndex";
	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		session.invalidate(); // 清理Session
		return "sysLogin";
	}

	/**
	 * 系统Session过期
	 * 
	 * @return
	 */
	@RequestMapping(value = "timeout")
	@ResponseBody
	public Map<String, Object> timeOut() {
		return DwzUtil.dialogAjaxDoneTimeOut();
	}
}
