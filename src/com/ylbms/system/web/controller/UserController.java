package com.ylbms.system.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;
import com.ylbms.system.model.Role;
import com.ylbms.system.model.User;
import com.ylbms.system.service.SystemService;
import com.ylbms.system.service.UserSerivice;

/**
 * 
 * @author jackLiang
 * @version 1.0
 * @date 二〇一三年五月二十二日 17:02:20
 * 
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	SystemService systemService;

	@Autowired
	UserSerivice userService;

	@ModelAttribute
	public User get(@RequestParam(required = false) Long id) {
		if (null != id) {
			return systemService.getUser(id);
		} else {
			return new User();
		}
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping("/addUi")
	public String addUi(HttpServletRequest request, Model model) {
		model.addAttribute("allRoles", systemService.findAllRole());

		return "user/input";
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/editPwdUi")
	public String editModify(HttpServletRequest request) {
		return "user/editPwd";
	}

	/**
	 * to add roleInfo page
	 * 
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/roleUi/{id}")
	public String roleUi(@PathVariable("id") Long id,
			HttpServletRequest request, Model model) {
		// query currentUser's roleInfo
		User user = systemService.getUser(id);
		List<Role> roles = user.getRoleList();
		String roleIds = "";
		for (Role r : roles) {
			roleIds += (r.getId() + ",");
		}
		model.addAttribute("roleIds", roleIds);
		model.addAttribute("userId", id);
		return "user/addRole";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/editUi/{id}")
	public String editUi(HttpServletRequest request,
			@PathVariable("id") Long id, Model model) {
		User user = systemService.getUser(id);
		model.addAttribute("obj", user);
		return "user/edit";
	}

	/**
	 * add roleInfo for user
	 * 
	 * @param user
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public Map<String, Object> addRole(HttpServletRequest request, User user,
			String roleIds) {
		try {
			List<Role> roleList = new ArrayList<Role>();
			String[] role = roleIds.split(",");
			for (int i = 0, len = role.length; i < len; i++) {
				roleList.add(new Role(Long.parseLong(role[i])));
			}
			user.setRoleList(roleList);
			systemService.saveUser(user);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK, "user");
	}

	/**
	 * add userInfo method
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		try {
			if (systemService.getUserByLoginName(user.getLoginName()) != null) {
				return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "user", "该用户已经存在！");
			}
			user.setCreateDate(new Date());
			systemService.saveUser(user);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "user");
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "user", e.getMessage());
		}

	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> updateUser(User user, String oldPwd) {
		try {
			if(StringUtils.isNotBlank(user.getPassword())){
				user.setPassword(systemService.entryptPassword(user.getPassword()));
			}else{
				user.setPassword(oldPwd);
			}
			systemService.updateUser(user);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK, "user", "修改成功");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") Long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/delByIds/{ids}")
	@ResponseBody
	public Map<String, Object> delByIds(@RequestParam("ids") String ids) {
		try {
			userService.deleteUserByIds(ids);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK);
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Page<User> page, Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<User> list = userService.searchUser(page, filters);
		model.addAttribute("page", list);
		return "user/list";
	}

}
