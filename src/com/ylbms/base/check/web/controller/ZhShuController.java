package com.ylbms.base.check.web.controller;

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

import com.ylbms.base.check.dao.ZhShInfosDao;
import com.ylbms.base.check.model.CheckNotesInfo;
import com.ylbms.base.check.model.ZhShInfosModel;
import com.ylbms.base.check.model.ZhShuMasterModel;
import com.ylbms.base.check.service.CheckNotesService;
import com.ylbms.base.check.service.ZhShModelService;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DateUtils;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.utils.excel.ExportExcel;
import com.ylbms.common.web.BaseController;

/**
 * 检定证书管理模块
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-26
 */
@Controller
@RequestMapping(value = "jdzhs")
public class ZhShuController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(ZhShuController.class);
	
	private static final String NAV_TAB_ID = "jdzhs";

	@Autowired
	private ZhShModelService zhShuService;

	@Autowired
	private CheckNotesService checkService;

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
	public String viewUi(@PathVariable("zId") Long zId, Model model) {
		ZhShuMasterModel master = zhShuService.getZhShMasterByZid(zId);
		model.addAttribute("master", master);
		return "base/zhshu/view";
	}

	/**
	 * add mx for doing zhsh
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addMx")
	public String addMx(@RequestParam("mids") String mids,
			Page<CheckNotesInfo> page, CheckNotesInfo info, Model model) {
		checkService.findPage(page, info, mids);
		return "base/check/zhshMx";
	}

	/**
	 * save zhengshu master and detail's infos
	 * 
	 * @param master
	 * @param detail
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(ZhShuMasterModel master, ZhShDTO detail) {
		try {
			zhShuService.saveZhShu(master, detail.getDetail());
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!!", e.getCause());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
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
	public String list(HttpServletRequest request, Page<ZhShuMasterModel> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		Page<ZhShuMasterModel> list = zhShuService
				.findZhShByPage(page, filters);
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
	public Map<String, Object> delete(@PathVariable("id") Long id) {
		try {
			this.zhShuService.deleteById(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
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
	@ResponseBody
	public Map<String, Object> delByIds(@RequestParam("Ids") String ids) {
		try {
			this.zhShuService.delByIds(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error!!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}
}
