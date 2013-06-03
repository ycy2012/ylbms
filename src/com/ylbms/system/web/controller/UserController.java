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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;
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
		return "user/input";
	}

	@RequiresUser
	@RequestMapping(value = "/editPwdUi")
	public String editModify(HttpServletRequest request) {
		return "user/editPwd";
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
	 * add userInfo method
	 * 
	 * @param user
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		try {
			systemService.saveUser(user);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK, "user");
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> updateUser(User user) {
		try {
			systemService.saveUser(user);
		} catch (Exception e) {
			log.error("system error!!", e);
			return DwzUtil.dialogAjaxDone(FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(OK, "user", "修改成功");
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
			return DwzUtil.dialogAjaxDone(FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(OK);
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
			return DwzUtil.dialogAjaxDone(FAIL, "user", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(OK);
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
