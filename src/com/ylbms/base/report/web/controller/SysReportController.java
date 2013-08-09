package com.ylbms.base.report.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.ylbms.base.report.model.SingleBarDTO;
import com.ylbms.base.report.service.SysReportService;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.web.BaseController;

/**
 * 系统报表模块控制器
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-31
 */
@Controller
@RequestMapping(value = "report")
public class SysReportController extends BaseController {

	private static Logger log = LoggerFactory
			.getLogger(SysReportController.class);

	@Autowired
	private SysReportService sysReportService;

	@Autowired
	private SingleInfoService singleInfoService;

	/**
	 * to single info bar by state
	 * 
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "barUi")
	public String toSingleBarUi() {
		return "report/singleBar";
	}

	/**
	 * 单件信息状态数量图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sinlgeBar")
	@ResponseBody
	@RequiresUser
	public List<Integer> singleCountsBystate(Model model) {
		List<SingleBarDTO> barDTO = sysReportService.singleCountsBystate();
		List<Integer> values = Lists.newArrayList();
		for (SingleBarDTO bar : barDTO) {
			values.add(bar.getCounts());
		}
		return values;
	}

	/**
	 * 获得应该复检的数量 目前的阀值是2 当少于两天就开始提醒
	 * 
	 * @return
	 */
	@RequestMapping(value = "singleCheck")
	@RequiresUser
	@ResponseBody
	public Long getCheckCount(Model model) {
		try {
			Long result = sysReportService.getCheckCount();
			model.addAttribute("counts", result);
			return result;
		} catch (Exception e) {
			log.error("system error!", e.getMessage());
			return null;
		}
	}

	/**
	 * 查询到期的单件信息
	 * 
	 * @param page
	 * @param single
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	@RequiresUser
	public String list(Page<SingleInfo> page, SingleInfo single, Model model) {
		Page<SingleInfo> list = singleInfoService.findSingleInfo(page, single,
				"", "YES");
		model.addAttribute("page", list);
		return "base/singleinfo/listSingleInfo";
	}
}
