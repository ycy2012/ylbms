package com.ylbms.base.single.web.controller;

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

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 */

@Controller
@RequestMapping("/single")
public class SingleInfoController {
	private static final Log log = LogFactory.getLog(SingleInfoController.class);
	
	@Autowired
	SingleInfoService singleInfoService;
	
	/**
	 * 跳转到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUi")
	public String addUi(Model model) {
		return "base/singleinfo/addSingleInfo";
	}
	
	@RequestMapping(value = "/listUi")
	public String editModify(HttpServletRequest request) {
		return "base/singleinfo/listSingleInfo";
	}
	
	/**
	 * 添加单件明细
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
			log.error("system error" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}
	
	/**
	 * 修改单件明细
	 * @param singleInfo
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> updateSpectype(SingleInfo singleInfo) {
		try {
			singleInfoService.updateSingleInfo(singleInfo);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "singleInfo");
		} catch (Exception e) {
			log.error("system error" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}
	/**
	 * 单件明细展示页面
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			Page<SingleInfo> page, Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SingleInfo> list =singleInfoService.findSingleInfo(page, filters);
		model.addAttribute("page", list);
		return "base/singleinfo/listSingleInfo";
	}
	
	
}


