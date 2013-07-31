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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.base.check.model.CheckNotes;
import com.ylbms.base.check.model.ZhShInfosModel;
import com.ylbms.base.check.service.CheckNotesService;
import com.ylbms.base.check.service.ZhShInfoService;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.StateInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;

/**
 * 检定记录Action
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-16
 */
@Controller
@RequestMapping(value = "jd")
public class CheckNotesController extends BaseController {

	private static final Log log = LogFactory
			.getLog(CheckNotesController.class);
	private static final String NAV_TAB_ID = "jdnotes";

	@Autowired
	private CheckNotesService checkService;

	@Autowired
	private SingleInfoService singleService;

	@Autowired
	private ZhShInfoService zsInfoService;

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
	 * 添加明细
	 * 
	 * @param page
	 * @param single
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addMx")
	public String addMx(@RequestParam("mids") String mids,
			Page<SingleInfo> page, SingleInfo single, Model model) {
		single.setState(new StateInfo("040")); // 设置要查询的单件信息的状态
		Page<SingleInfo> list = singleService
				.findSingleInfo(page, single, mids);
		model.addAttribute("page", list);
		return "base/check/addMx";
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
			checkService.deleteNotesById(djId);
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
	 * save 检定记录信息
	 * 
	 * @param cheack
	 *            表头信息
	 * @param notes
	 *            保存明细内容
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(CheckNotes cheack, NotesDTO notes) {
		try {
			checkService.saveCheckNotes(cheack, notes);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
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
		Page<CheckNotes> list = checkService.findPage(page, filters);
		model.addAttribute("page", list);
		return "base/check/list";
	}

	/**
	 * 分页查询检定记录信息
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "iList")
	@RequiresUser
	public String infoList(HttpServletRequest request,
			Page<ZhShInfosModel> page, Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<ZhShInfosModel> list = zsInfoService.findPage(page, filters);
		model.addAttribute("page", list);
		return "base/check/infoList";
	}

	/**
	 * 展示单件信息明细
	 * 
	 * @param jdId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewUi/{jdId}")
	@RequiresUser
	public String viewUi(@PathVariable("jdId") String jdId, Model model) {
		CheckNotes master = checkService.getCheckById(jdId);
		model.addAttribute("master", master);
		return "base/check/view";
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delByIds")
	@ResponseBody
	public Map<String, Object> delByIds(@RequestParam("ids") String ids) {
		try {
			checkService.delByIds(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!", e.getCause());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}
}
