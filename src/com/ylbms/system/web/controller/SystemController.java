//package com.ylbms.system.web.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.shiro.authz.annotation.RequiresUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ylbms.common.util.EasyUiReturn;
//import com.ylbms.system.model.Menu;
//import com.ylbms.system.service.MenuService;
//import com.ylbms.system.service.SystemService;
//
//@Controller
//@RequestMapping("/sys")
//public class SystemController {
//
//	SystemService systemService;
//
//	MenuService menuService;
//
//	/**
//	 * 根据当前用户信息查询menu信息
//	 * 
//	 * @param request
//	 * @return
//	 */
//	// @RequiresUser
//	@RequestMapping(value = "/getTreeByUserID")
//	@ResponseBody
//	public List<Menu> getTreeByUserID(HttpServletRequest request) {
//		return systemService.findAllMenu();
//	}
//
//	@RequestMapping(value = "/roleList")
//	public String roleList(HttpServletRequest request) {
//		return "role/roleList";
//	}
//
//	@RequiresUser
//	@RequestMapping(value = "/menuList")
//	public String menuList(HttpServletRequest request) {
//		return "role/menuList";
//	}
//
//	@RequestMapping(value = "/listmenu")
//	@ResponseBody
//	public List<Menu> allMenu(HttpServletRequest request) {
//		return systemService.findAllMenu();
//	}
//
//	@Autowired
//	@RequestMapping(value = "/menuForm")
//	public String menuForm(HttpServletRequest request) {
//		return "role/menuForm";
//	}
//
//	@RequiresUser
//	@RequestMapping(value = "/addMenu")
//	@ResponseBody
//	public EasyUiReturn addMenu(Menu menu, HttpServletRequest request) {
//		EasyUiReturn easyUi = new EasyUiReturn();
//		try {
//			menuService.save(menu);
//			easyUi.setMsg("菜单信息添加成功！");
//			easyUi.setSuccess(true);
//		} catch (Exception e) {
//			easyUi.setMsg(e.getMessage());
//			easyUi.setSuccess(false);
//		}
//		return easyUi;
//	}
//
//	@RequiresUser
//	@RequestMapping(value = "/parentMenu")
//	@ResponseBody
//	public List<Menu> getParentMenu(HttpServletRequest request) {
//		return menuService.pMenuList();
//	}
//
//	@Autowired
//	public void setSystemService(SystemService systemService) {
//		this.systemService = systemService;
//	}
//
//	@Autowired
//	public void setMenuService(MenuService menuService) {
//		this.menuService = menuService;
//	}
//}
