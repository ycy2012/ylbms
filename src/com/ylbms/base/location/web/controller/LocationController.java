package com.ylbms.base.location.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylbms.base.location.model.Location;
import com.ylbms.base.location.service.LocationService;

/**
 * location's controller
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-6
 */
@Controller
@RequestMapping(value = "/location")
public class LocationController {

	private static final Log log = LogFactory.getLog(LocationController.class);

	@Autowired
	LocationService locationService;

	/**
	 * to add location page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUi")
	public String addUi(HttpServletRequest request, Model model) {
		return "base/location/input";
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping("/listUi")
	public String listUi(HttpServletRequest request, Model model) {
		return "base/location/list";
	}

	/**
	 * 加载位置信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request, Model model) {
		List<Map<String, Object>> mapList = Lists.newArrayList(); // 返回对象
		List<Location> list = locationService.getAllLocation();
		for (Location l : list) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", l.getId());
			map.put("pId", l.getParent() != null ? l.getParent().getId() : 0);
			map.put("name", l.getLocationName());
			mapList.add(map);
		}
		return mapList;
	}
}
