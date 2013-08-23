package com.ylbms.system.web.controller;

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

import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.system.model.Dict;
import com.ylbms.system.service.DictService;

/**
 * 字典信息控制器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-4
 */
@Controller()
@RequestMapping(value = "/dict")
public class DictController {

	private static final Log log = LogFactory.getLog(DictController.class);

	@Autowired
	DictService dictService;

	/**
	 * to add dictInfo page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUi")
	public String addUi(HttpServletRequest request, Model model) {
		return "dict/input";
	}

	/**
	 * to edit dictInfo page
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/editUi/{id}")
	public String editUi(@PathVariable("id") Long id, Model model) {
		Dict dict = dictService.getDictById(id);
		model.addAttribute("obj", dict);
		return "dict/input";
	}

	/**
	 * add or update dictInfo
	 * 
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, Object> add(Dict dict, Model model) {
		try {
			dictService.saveDict(dict);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error!!" + e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "dict", e.getMessage());
		}
	}

	/**
	 * delete dictInfo by ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") Long id) {
		try {
			dictService.deleteDictByID(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("system error!!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "dict", e.getMessage());
		}
	}

	/**
	 * delete Dicts by Ids
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delByIds/{ids}")
	@ResponseBody
	public Map<String, Object> delByIds(@PathVariable("ids") String ids) {
		try {
			dictService.delByIds(ids);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, "dict");
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("system error!!", e.getCause());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, "dict", e.getMessage());
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
	@RequestMapping("/list")
	@RequiresUser
	public String list(HttpServletRequest request, Page<Dict> page, Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<Dict> list = dictService.searchUser(page, filters);
		model.addAttribute("page", list);
		return "dict/list";
	}
}
