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

import com.ylbms.base.check.model.CheckNotes;
import com.ylbms.base.check.service.CheckNotesService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;

/**
 * 检定记录Action
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-16
 */
@Controller
@RequestMapping(value = "jd")
public class CheckNotesController extends BaseController {

	private static final Log log = LogFactory
			.getLog(CheckNotesController.class);
	private static final String NAV_TAB_ID = "notes";

	@Autowired
	private CheckNotesService checkNotesService;

	/**
	 * to add page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addUi")
	public String addUi(HttpServletRequest request, Model model) {

		return "base/check/input";
	}

	/**
	 * delete checkNotes by id
	 * 
	 * @param djId
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") String djId) {
		try {
			checkNotesService.deleteNotesById(djId);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!", e.getCause());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * save checkNotes 
	 * 
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save() {
		try {
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!", e.getCause());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
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
	@RequestMapping(value = "list")
	@RequiresUser
	public String list(HttpServletRequest request, Page<CheckNotes> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<CheckNotes> list = checkNotesService.findPage(page, filters);
		model.addAttribute("page", list);
		return "base/check/list";
	}
}
