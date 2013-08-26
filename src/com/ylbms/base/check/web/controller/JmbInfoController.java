package com.ylbms.base.check.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.base.check.service.JmbInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.utils.excel.ExportExcel;
import com.ylbms.common.utils.excel.ImportExcel;
import com.ylbms.common.web.BaseController;

/**
 * 精密表控制层
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-7-26
 * @editor JackLiang 2013年8月26日 11:23:27 添加Excel导入导出功能
 */

@Controller
@RequestMapping(value = "/jmbinfo")
public class JmbInfoController extends BaseController {
	private static final Log log = LogFactory.getLog(JmbInfoController.class);
	private static final String NAV_TAB_ID = "jmbinfo";

	@Autowired
	private JmbInfoService jmbInfiService;

	/**
	 * to import page
	 * 
	 * @return
	 */
	@RequiresPermissions("base:jmb:add")
	@RequestMapping(value = "importUi")
	public String importUi() {
		return "base/jmb/import";
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
			List<JmylbModel> list = Lists.newArrayList();
			new ExportExcel("单件信息", JmylbModel.class, 2).setDataList(list)
					.write(response, request, fileName).dispose();
			return null;
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!!", e.getCause());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * add jmylb infos by importing excel
	 * 
	 * @param fileUpload
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "import")
	@RequiresPermissions("base:jmb:add")
	@ResponseBody
	public Map<String, Object> importFile(MultipartFile fileUpload, Model model) {
		try {
			ImportExcel ei = new ImportExcel(fileUpload, 1, 0);
			List<JmylbModel> list = ei.getDataList(JmylbModel.class);
			for (JmylbModel j : list) {
				jmbInfiService.save(j);
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("System error!!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * 跳转到添加精密表信息添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("base:jmb:add")
	@RequestMapping(value = "/addUI")
	public String jmbAddUI(Model model) {
		return "base/jmb/addJmbInfo";
	}

	/**
	 * 跳转到修改精密表信息页面
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("base:jmb:edit")
	@RequestMapping(value = "/editUi/{id}")
	public String editUi(HttpServletRequest request,
			@PathVariable("id") Long id, Model model) {
		JmylbModel jmblbModel = jmbInfiService.getId(id);
		model.addAttribute("obj", jmblbModel);
		return "base/jmb/editJmbInfo";
	}

	/**
	 * 查找带回
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "lookUp")
	public String lookUp(HttpServletRequest request, Page<JmylbModel> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<JmylbModel> list = jmbInfiService.list(page, filters);
		model.addAttribute("page", list);
		return "base/jmb/JmbLookup";

	}

	/**
	 * 添加精密表
	 * 
	 * @param jmbInfo
	 * @return
	 */
	@RequiresPermissions("base:jmb:add")
	@RequestMapping(value = "addJmy")
	@ResponseBody
	public Map<String, Object> addJmb(JmylbModel jmbInfo) {
		try {
			jmbInfiService.save(jmbInfo);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
		} catch (Exception e) {
			log.error("system error" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
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
	@RequiresPermissions("base:jmb:edit")
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
		return "base/jmb/listJmb";
	}

	/**
	 * 根据ID删除
	 * 
	 * @param mid
	 * @return
	 */
	@RequiresPermissions("check:jmb:delete")
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delSpectype(@PathVariable("id") Long id) {
		try {
			jmbInfiService.deleteJmbInfo(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error" + e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
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
	@RequiresUser
	@RequestMapping(value = "showview/{id}")
	public String jmbDetailed(Model model, @PathVariable("id") String id) {
		Long parseLongMid = Long.parseLong(id);
		JmylbModel jmbInfo = jmbInfiService.getId(parseLongMid);
		model.addAttribute("jmbInfo", jmbInfo);
		return "base/jmb/jmbview";
	}
}
