package com.ylbms.base.check.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.base.check.service.JmbInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;
import com.ylbms.system.utils.UserUtils;

/**
 * 精密表控制层
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-7-26
 */

@Controller
@RequestMapping(value = "/jmbinfo")
public class JmbInfoController extends BaseController {
	private static final Log log = LogFactory.getLog(JmbInfoController.class);
	@Autowired
	JmbInfoService jmbInfiService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 跳转到添加精密表信息添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUI")
	public String jmbAddUI(Model model) {
		return "base/check/addJmbInfo";
	}

	/**
	 * 跳转到修改精密表信息页面
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editUi/{id}")
	public String editUi(HttpServletRequest request,
			@PathVariable("id") Long id, Model model) {
		JmylbModel jmblbModel = jmbInfiService.getId(id);
		model.addAttribute("obj", jmblbModel);
		return "base/check/editJmbInfo";
	}

	/**
	 * 查找带回
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "lookUp")
	public String lookUp(HttpServletRequest request, Page<JmylbModel> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<JmylbModel> list = jmbInfiService.list(page, filters);
		model.addAttribute("page", list);
		return "base/check/JmbLookup";

	}

	/**
	 * 添加精密表
	 * 
	 * @param jmbInfo
	 * @return
	 */
	@RequestMapping(value = "addJmy")
	@ResponseBody
	public Map<String, Object> addJmb(JmylbModel jmbInfo) {
		try {
			jmbInfiService.save(jmbInfo);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "jmbInfo");
		} catch (Exception e) {
			log.error("system error" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "jmbInfo",
					e.getMessage());
		}
	}

	/**
	 * 修改精密表
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jmbInfoEdit")
	@ResponseBody
	public Map<String, Object> updateSpectype(JmylbModel jmbInfo, Model model) {
		try {
			jmbInfiService.update(jmbInfo);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "jmbInfo");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!!" + e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "jmbInfo",
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
	@RequiresUser
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Page<JmylbModel> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<JmylbModel> list = jmbInfiService.list(page, filters);
		model.addAttribute("page", list);
		return "base/check/listJmb";
	}

	/**
	 * 根据ID删除
	 * 
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delSpectype(@PathVariable("id") Long id) {
		try {
			jmbInfiService.deleteJmbInfo(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error" + e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "jmbInfo",
					e.getMessage());
		}
	}

	/**
	 * 查看详细信息
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "showview/{id}")
	public String jmbDetailed(Model model, @PathVariable("id") String id) {
		Long parseLongMid = Long.parseLong(id);
		JmylbModel jmbInfo = jmbInfiService.getId(parseLongMid);
		model.addAttribute("jmbInfo", jmbInfo);
		return "base/check/jmbview";
	}
}
