package com.ylbms.system.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylbms.system.model.User;
import com.ylbms.system.service.SystemService;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	private static final Log log = LogFactory.getLog(RegisterController.class);

	@Autowired
	SystemService systemService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "user/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(HttpServletRequest requeset, User user,
			RedirectAttributes redirectAttributes) {
		systemService.saveUser(user);
		redirectAttributes.addFlashAttribute("username", user.getLoginName());
		return "redirect:/a/login";
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (systemService.getUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}

}
