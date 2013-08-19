package com.ylbms.system.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequiresPermissions("sys:role:add")
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
	@RequiresPermissions("sys:role:edit")
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
	@RequestMapping("/permUi/{id}")
	public String permUi(@PathVariable("id") Long id, Model model) {
		String json = roleService.getMenuByRoleID(id);
		model.addAttribute("selectIds", json.replaceAll("", "")); // 将该角色目前拥有的权限展示给页面
		model.addAttribute("roleId", id);
		return "role/addPerm";
	}

	/**
	 * loading roleInfo for roleform
	 * 
	 * @return
	 */
	@RequestMapping("/roleData")
	@ResponseBody
	public List<Map<String, Object>> roleInfo() {
		List<Role> list = systemService.findAllRole();
		List<Map<String, Object>> roleData = new ArrayList<Map<String, Object>>();
		for (Role r : list) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("text", r.getName());
			value.put("value", r.getId());
			roleData.add(value);
		}
		return roleData;
	}

	/**
	 * add permInfo for role
	 * 
	 * @param request
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addPerm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPerm(HttpServletRequest request,
			@RequestParam("ids") String ids,
			@RequestParam("roleID") Long roleID, Model model) {
		try {
			String[] menuIds = ids.split(",");
			List<Long> mIds = new ArrayList<Long>();
			for (int i = 0, len = menuIds.length; i < len; i++) {
				mIds.add(i, Long.parseLong(menuIds[i]));
			}
			Role role = systemService.getRoleModel(roleID);

			role.setMenuIdList(mIds);
			// 持久化
			systemService.saveRole(role);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "role");
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("system error!!" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "role");
		}
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
	@RequiresPermissions("sys:role:delete")
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
