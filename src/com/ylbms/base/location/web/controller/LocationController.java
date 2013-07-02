package com.ylbms.base.location.web.controller;

import java.net.URLDecoder;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylbms.base.location.model.Location;
import com.ylbms.base.location.service.LocationService;
import com.ylbms.common.utils.DwzUtil;

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

	@RequestMapping(value = "/commUi")
	public String toCommUi() {
		return "base/location/commonTree";
	}

	/**
	 * to add location page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUi/{ids}/{all}")
	public String addUi(@PathVariable("ids") String ids,
			@PathVariable("all") String all, HttpServletRequest request,
			Model model) {
		String allName = getAllName(URLDecoder.decode(all)); // 解码
		String[] id = ids.split(",");
		Location l = locationService.getLocationById(Long
				.parseLong(id[id.length - 1]));
		model.addAttribute("obj", l); // 将其父节点放到model中
		model.addAttribute("all", allName);
		model.addAttribute("pids", ids);
		return "base/location/input";
	}

	/**
	 * to edit location's page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editUi/{ids}/{all}")
	public String editUi(@PathVariable("ids") String ids,
			@PathVariable("all") String all, HttpServletRequest request,
			Model model) {
		String[] id = ids.split(",");
		String allName = subAllName(URLDecoder.decode(all)); // 解码
		Location location = locationService.getLocationById(Long
				.parseLong(id[id.length - 1]));
		model.addAttribute("obj", location);
		model.addAttribute("all", allName);

		return "base/location/edit";
	}

	/**
	 * to list Ui
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
	 * 添加
	 * 
	 * @param request
	 * @param location
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, Location location) {
		try {
			location.setCreateDate(new Date());
			location.setAllName((location.getAllName() + location
					.getLocationName()));
			locationService.saveLocation(location);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "location");
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("system error!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "location",
					e.getMessage());
		}
	}

	/**
	 * delete
	 * 
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete/{ids}")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,
			@PathVariable("ids") String ids) {
		try {
			String[] id = ids.split(",");
			locationService.deleteLocation(Long.parseLong(id[id.length - 1]));
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "location");
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("system error!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "location",
					e.getMessage());
		}

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
			map.put("allName", l.getAllName()==null?"":l.getAllName());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 解析全称地址问题
	 * 
	 * @param values
	 * @return
	 */
	public String getAllName(String values) {
		String temp = "";
		String[] all = values.split(",");
		for (int i = 0, len = all.length; i < len; i++) {
			temp += all[i];
		}
		return temp == null ? "" : temp;
	}

	/**
	 * 截取比较特殊
	 * 
	 * @param values
	 * @return
	 */
	public String subAllName(String values) {
		String temp = "";
		String[] all = values.split(",");
		for (int i = 1, len = all.length - 1; i < len; i++) {
			temp += all[i];
		}
		return temp == null ? "" : temp;
	}
}
