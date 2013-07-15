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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
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
	private static final Log log = LogFactory
			.getLog(SingleInfoController.class);

	@Autowired
	SingleInfoService singleInfoService;

	/**
	 * 跳转到添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUi")
	public String addUi(Model model) {
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
			log.error("system error" + e.getMessage());
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
		Page<SingleInfo> list = singleInfoService.findSingleInfo(page, single);
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
			log.error("system error" + e);
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
	@RequestMapping(value = "/delByIds/{ids}")
	@ResponseBody
	public Map<String, Object> delByIds(@RequestParam("ids") String ids) {
		try {
			singleInfoService.delByIds(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error" + e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "singleInfo",
					e.getMessage());
		}
	}
	
}
