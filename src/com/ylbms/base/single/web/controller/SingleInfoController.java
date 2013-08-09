package com.ylbms.base.single.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.ylbms.base.location.model.Location;
import com.ylbms.base.location.service.LocationService;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.base.single.service.SpectypeService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DateUtils;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.utils.excel.ExportExcel;
import com.ylbms.common.web.BaseController;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 */

@Controller
@RequestMapping("/single")
public class SingleInfoController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(SingleInfoController.class);
	private static final String NAV_TAB_ID = "singleInfo";

	@Autowired
	SingleInfoService singleInfoService;

	@Autowired
	SpectypeService spectypeService;

	@Autowired
	LocationService locationservice;

	/**
	 * to import page
	 * 
	 * @return
	 */
	@RequestMapping(value = "importUi")
	public String importUi() {
		return "base/singleinfo/import";
	}

	/**
	 * download excel template
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "import/template")
	@ResponseBody
	public Map<String, Object> template(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			String fileName = "单件信息导入模版.xls";
			List<SingleInfo> list = Lists.newArrayList();
			new ExportExcel("单件信息", SingleInfo.class, 2).setDataList(list)
					.write(response, request, fileName).dispose();
			return null;
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!!", e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * 根据当前分页导出单件信息内容
	 * 
	 * @author JackLiang
	 * @date 2013年8月7日 16:07:57
	 * @param page
	 * @param single
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="export")
	@ResponseBody
	public Map<String, Object> export(Page<SingleInfo> page, SingleInfo single,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			//文件名
			String fileName = "压力表单件信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<SingleInfo> list = singleInfoService.findSingleInfo(page,
					single, "","");

			new ExportExcel("压力表单件信息", SingleInfo.class)
					.setDataList(list.getResult())
					.write(response, request, fileName).dispose();
			return null;
		} catch (Exception e) {
			log.error("System error!", e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}

	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUi")
	public String addUi(Model model) {
		List<Location> listLocation = locationservice.getAllLocation();

		List<Map<String, Object>> mapLocation = new ArrayList<Map<String, Object>>();
		for (Location list : listLocation) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.getId());
			map.put("locationName", list.getLocationName());
			mapLocation.add(map);
		}
		model.addAttribute("mapLocation", mapLocation);

		List<SpectypeInfo> list = spectypeService.getAllSpectype();
		List<Map<String, Object>> spectypes = new ArrayList<Map<String, Object>>();
		for (SpectypeInfo s : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", s.getSpeName());
			map.put("value", s.getSpeId());
			spectypes.add(map);
		}
		// 将值放入model
		model.addAttribute("spectypes", spectypes);

		return "base/singleinfo/addSingleInfo";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @param mid
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/edit/{id}")
	public String editUi(HttpServletRequest request,
			@PathVariable("id") String mid, Model model) {
		List<Location> listLocation = locationservice.getAllLocation();
		List<Map<String, Object>> mapLocation = new ArrayList<Map<String, Object>>();
		for (Location list : listLocation) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.getId());
			map.put("locationName", list.getLocationName());
			mapLocation.add(map);
		}
		model.addAttribute("mapLocation", mapLocation);
		List<SpectypeInfo> list = spectypeService.getAllSpectype();
		List<Map<String, Object>> spectypes = new ArrayList<Map<String, Object>>();
		for (SpectypeInfo s : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", s.getSpeName());
			map.put("value", s.getSpeId());
			spectypes.add(map);
		}
		model.addAttribute("spectypes", spectypes);
		SingleInfo singleinfo = singleInfoService.getSingleById(mid);
		model.addAttribute("obj", singleinfo);
		return "base/singleinfo/addSingleInfo";
	}

	/**
	 * 跳转到高级查询页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/advanced")
	public String advanced(Model model) {

		return "base/singleinfo/advanced_query";

	}

	/**
	 * 添加单件明细
	 * 
	 * @param singleInfo
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, Object> addSpectype(SingleInfo singleInfo) {
		try {
			singleInfoService.saveSingleInfo(singleInfo);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "singleInfo");
		} catch (Exception e) {
			log.error("system error", e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}

	/**
	 * 修改单件明细
	 * 
	 * @param singleInfo
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> updateSpectype(SingleInfo singleInfo, Model model) {
		try {
			singleInfoService.updateSingleInfo(singleInfo);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "singleInfo");
		} catch (Exception e) {
			log.error("system error", e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Page<SingleInfo> page,
			SingleInfo single, Model model) {
		Page<SingleInfo> list = singleInfoService.findSingleInfo(page, single,
				"","");
		model.addAttribute("page", list);
		return "base/singleinfo/listSingleInfo";
	}

	/**
	 * 根据ID删除用户
	 * 
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/delete/{mid}")
	@ResponseBody
	public Map<String, Object> delSpectype(@PathVariable("mid") String mid) {
		try {
			singleInfoService.deleteSingleInfo(mid);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error", e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}

	/**
	 * 批量删除单件信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delByIds")
	@ResponseBody
	public Map<String, Object> delByIds(@RequestParam("ids") String ids) {
		try {
			singleInfoService.delByIds(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error", e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}

}
