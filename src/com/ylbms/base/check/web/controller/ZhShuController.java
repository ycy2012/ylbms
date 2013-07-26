package com.ylbms.base.check.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ylbms.base.check.model.CheckZhShuModel;
import com.ylbms.base.check.service.ZhShModelService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-26
 */
@Controller
@RequestMapping(value = "jdzhs")
public class ZhShuController extends BaseController {
	private static final Log log = LogFactory.getLog(ZhShuController.class);
	private static final String NAV_TAB_ID = "jdzhs";

	@Autowired
	private ZhShModelService zhShuService;

	/**
	 * to add page
	 * 
	 * @return
	 */
	@RequestMapping(value = "addUi")
	public String addUi() {
		return "base/check/zhshInput";
	}

	/**
	 * to view page
	 * 
	 * @param zId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewUi/{zId}")
	public String viewUi(@PathVariable("zId") String zId, Model model) {

		return "base/check/ZhShView";
	}

	/**
	 * list by page info
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Page<CheckZhShuModel> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<CheckZhShuModel> list = zhShuService.findZhShByPage(page, filters);
		model.addAttribute("page", list);
		return "base/check/zhshList";
	}

	/**
	 * delete zhSh info by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public Map<String, Object> delete(@PathVariable("id") String id) {
		try {
			this.zhShuService.deleteById(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
		} catch (Exception e) {
			log.error("system error!!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delByIds")
	public Map<String, Object> delByIds(@RequestParam("ids") String ids) {
		try {
			this.zhShuService.delByIds(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
		} catch (Exception e) {
			log.error("system error!!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}
}
