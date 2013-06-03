package com.ylbms.system.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.system.model.Role;
import com.ylbms.system.service.RoleService;
import com.ylbms.system.service.SystemService;

/**
 * 角色控制器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-3
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	SystemService systemService;

	@Autowired
	RoleService roleService;

	/**
	 * to add roleInfo page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUi")
	public String addUi(HttpServletRequest request, Model model) {

		return "role/input";
	}

	/**
	 * to edit roleInfo page
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editUi/{id}")
	public String editUi(@PathVariable("id") Long id, Model model) {
		Role role = systemService.getRoleModel(id);
		model.addAttribute("role", role);
		return "role/input";
	}

	/**
	 * add permInfo for role
	 * 
	 * @return
	 */
	@RequestMapping("/permUi")
	public String permUi() {
		return "role/addPerm";
	}

	/**
	 * update or add roleInfo method
	 * 
	 * @param Role
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addRole(Role role) {
		try {
			systemService.saveRole(role);
		} catch (Exception e) {
			log.error("system Error!!" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "role", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK, "role");
	}

	/**
	 * query roleInfo for list
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Page<Role> page, Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<Role> list = roleService.searchUser(page, filters);
		model.addAttribute("page", list);

		return "role/list";
	}

	/**
	 * delete roleInfo by roleID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") Long id) {
		try {
			systemService.deleteRole(id);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "role", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK, "role");
	}
}
