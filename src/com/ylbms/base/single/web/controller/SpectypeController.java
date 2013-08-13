package com.ylbms.base.single.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.service.SpectypeService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DateUtils;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.utils.excel.ExportExcel;
import com.ylbms.common.utils.excel.ImportExcel;
import com.ylbms.common.web.BaseController;

/**
 * 规格型号信息维护Action
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-6
 */
@Controller
@RequestMapping("/spec")
public class SpectypeController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(SpectypeController.class);
	private static final String NAV_TAB_ID = "spectype";

	@Autowired
	private SpectypeService spectypeService;

	/**
	 * to excel import page
	 * @author JackLiang
	 * @date 2013年8月5日 14:25:57
	 * @return
	 */
	@RequestMapping(value = "importUi")
	public String importUi() {
		return "base/spectype/import";
	}

	/**
	 * download excel's table for importing
	 * 
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "import/template")
	@ResponseBody
	public Map<String, Object> importFileTemplate(HttpServletResponse response,HttpServletRequest request) {
		try {
			String fileName = "规格型号导入模版.xls";
			List<SpectypeInfo> list = Lists.newArrayList();
			new ExportExcel("规格型号信息", SpectypeInfo.class, 2).setDataList(list)
					.write(response,request, fileName).dispose();
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
	 * 导出规格信息到excel
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "export")
	public Map<String, Object> exportFile(Page<SpectypeInfo> page,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);

			String fileName = "规划型号数据" + DateUtils.getDate("yyyyMMddHHmmss")+ ".xlsx";
			Page<SpectypeInfo> list = spectypeService.getSpectypeInfo(page,filters);

			new ExportExcel("规划型号数据", SpectypeInfo.class)
					.setDataList(list.getResult()).write(response,request, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!", e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * 跳转到添加规格型号添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping("addUi")
	public String addUi(Model model) {
		return "base/spectype/addspectype";
	}

	/**
	 * 跳转到规格型号展示页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "editUi")
	public String editModify(HttpServletRequest request) {
		return "base/spectype/editspectype";
	}

	/**
	 * 跳转到规格型号修改页面
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "edit/{id}")
	public String editUi(HttpServletRequest request,
			@PathVariable("id") int id, Model model) {
		SpectypeInfo spectype = spectypeService.getSpetypeById(id);
		model.addAttribute("obj", spectype);
		return "base/spectype/edit";
	}

	/**
	 * 通过excel导入规格型号信息 1 、先上传服务器作为零时文件2、后解析导入
	 * 
	 * @author JackLiang
	 * @date 2013年8月2日 18:21:45
	 * @return
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importExcel(HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		int suNum = 0;
		try {
			if (!fileUpload.isEmpty()) {
				ImportExcel ei = new ImportExcel(fileUpload, 1, 0);
				List<SpectypeInfo> list = ei.getDataList(SpectypeInfo.class);
				for (SpectypeInfo s : list) {
					if (checkSpectype(s.getSpeName())) {
						spectypeService.updateSpectypeInfo(s);
						suNum++;
					}
				}
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.OK,NAV_TAB_ID,"导入成功了！数量是<b>"+suNum+"</b>");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!", e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "spectype",
					e.getMessage());
		}
	}

	/**
	 * 增加规格型号
	 * 
	 * @param spectype
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Map<String, Object> addSpectype(SpectypeInfo spectype) {
		try {
			spectypeService.addSpectype(spectype);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "spectype");
		} catch (Exception e) {
			log.error("system error" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "spectype",
					e.getMessage());
		}
	}

	/**
	 * 删除规格型号
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delSpectype(@PathVariable("id") Long id) {
		try {
			spectypeService.deleteSpectype(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error" + e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "spectype",
					e.getMessage());
		}
	}

	/**
	 * 修改规格型号
	 * 
	 * @param spectype
	 * @return
	 */
	@RequestMapping(value = "/spectype")
	@ResponseBody
	public Map<String, Object> updateSpectype(SpectypeInfo spectype, Model model) {
		try {
			spectypeService.addSpectype(spectype);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "spectype");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!!" + e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "spectype",
					e.getMessage());
		}
	}

	/**
	 * 规格型号分页查询展示页面
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Page<SpectypeInfo> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SpectypeInfo> list = spectypeService
				.getSpectypeInfo(page, filters);
		model.addAttribute("page", list);
		return "base/spectype/list";
	}

	/**
	 * 根据id批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value = "/deletes/{ids}")
	@ResponseBody
	public Map<String, Object> delSpectype(@RequestParam("ids") String ids) {
		try {
			spectypeService.deleteSpectype(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error" + e);
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "spectype",
					e.getMessage());
		}
	}

	/**
	 * 检测该规划名称是否存在
	 * 
	 * @param speName
	 * @return
	 */
	public Boolean checkSpectype(String speName) {
		return spectypeService.getSpectypeByName(speName) == null ? true
				: false;
	}
}
