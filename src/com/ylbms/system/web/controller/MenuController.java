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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.system.model.Menu;
import com.ylbms.system.service.MenuService;
import com.ylbms.system.service.SystemService;

/**
 * 菜单控制器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 二〇一三年五月三十一日 10:29:11
 * 
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	private static final Log log = LogFactory.getLog(MenuController.class);

	@Autowired
	MenuService menuService;
	
	@Autowired
	SystemService systemService;

	/**
	 * to add menu page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUi")
	public String addUi(HttpServletRequest request, Model model) {
		Menu menu = new Menu();
		model.addAttribute(menu);
		return "menu/input";
	}

	/**
	 * to edit menuInfo page
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/editUi/{id}")
	public String editUi(HttpServletRequest request,
			@PathVariable("id") Long id, Model model) {
		Menu menu = systemService.getMenu(id);
		model.addAttribute("menu", menu);
		return "menu/edit";
	}

	/**
	 * to queryUi page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryUi")
	public String queryUi(Model model) {

		return "menu/queryUi";
	}

	/**
	 * add or update menuInfo method
	 * 
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, Object> addMenu(HttpServletRequest request, Menu menu) {
		try {
			systemService.saveMenu(menu);
		} catch (Exception e) {
			log.error("system error!!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "menu", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK, "menu");
	}
	

	/**
	 * add next menu for it
	 * 
	 * @param menu
	 * @return
	 */

	@RequestMapping("/nextUi")
	public String addNextMenuUi(Menu menu, Model model) {
		if ((null == menu.getId()) || (null == menu.getParent().getId())) {
			menu.setParent(new Menu(1L));
		}
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		model.addAttribute("menu", menu);
		return "menu/input";
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
	public String list(HttpServletRequest request, Model model) {
		List<Menu> list = systemService.findAllMenu();
		model.addAttribute("page", list);
		return "menu/list";
	}

	/**
	 * 
	 * @param extId
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/treeData")
	@ResponseBody
	public Object treeData(@RequestParam(required = false) Long extId) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Menu> list = systemService.findAllMenu();
		for (int i = 0; i < list.size(); i++) {
			Menu e = list.get(i);
			if (extId == null
					|| (extId != null && !extId.equals(e.getId()) && e
							.getParentIds().indexOf("," + extId + ",") == -1)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent() != null ? e.getParent().getId()
						: 0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		log.info(JSON.toJSON(mapList));
		return mapList;
	}
	

	/**
	 * delete menuInfo by menuID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") Long id) {
		try {
			systemService.deleteMenu(id);
		} catch (Exception e) {
			log.error("system error!!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "menu", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK);
	}

	/**
	 * delete menuInfos by ids
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delByIds}")
	@ResponseBody
	public Map<String, Object> delByIds(@RequestParam("ids") String ids) {
		try {
			menuService.delByIds(ids);
		} catch (Exception e) {
			log.error("system error!!!", e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "menu", e.getMessage());
		}
		return DwzUtil.dialogAjaxDone(DwzUtil.OK);
	}
}
