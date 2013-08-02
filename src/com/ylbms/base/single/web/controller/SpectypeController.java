package com.ylbms.base.single.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.service.SpectypeService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-6
 */
@Controller
@RequestMapping("/spec")
public class SpectypeController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(SpectypeController.class);

	@Autowired
	SpectypeService spectypeService;

	/**
	 * 跳转到添加规格型号添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUi")
	public String addUi(Model model) {
		return "base/spectype/addspectype";
	}

	/**
	 * 跳转到规格型号展示页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editUi")
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
	@RequestMapping(value = "/edit/{id}")
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
	@RequestMapping(value = "importUi")
	public Map<String, Object> importExcel(HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			if (!fileUpload.isEmpty()) {
				String fileName=fileUpload.getOriginalFilename();

			}
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "spectype");
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
	@RequestMapping(value = "/add")
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
}
