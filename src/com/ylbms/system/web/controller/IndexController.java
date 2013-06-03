package com.ylbms.system.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月十七日 17:11:46
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "sysLogin";
	}

}
