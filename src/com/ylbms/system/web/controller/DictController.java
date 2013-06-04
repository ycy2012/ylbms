package com.ylbms.system.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.model.Dict;
import com.ylbms.system.service.DictService;

/**
 * 字典信息控制器
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-4
 */
@Controller()
@RequestMapping(value = "/dict")
public class DictController {

	private static final Log log = LogFactory.getLog(DictController.class);
	
	@Autowired
	DictService dictService;

	/**
	 * to add dictInfo page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUi")
	public String addUi(HttpServletRequest request, Model model) {
		return "dict/input";
	}

	/**
	 * to edit dictInfo page
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/editUi")
	public String editUi(HttpServletRequest request, Model model) {
		return "dict/input";
	}
	/**
	 * 分页查询
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresUser
	public String list(HttpServletRequest request,Page<Dict> page,Model model){
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<Dict> list = dictService.searchUser(page, filters);
		model.addAttribute("page", list);
		return "dict/list";
	}
}
