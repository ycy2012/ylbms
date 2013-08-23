package com.ylbms.base.bill.web.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.base.bill.service.BillService;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;

/**
 * 送检信息记录管理(回收在检)
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-8
 */
@Controller
@RequestMapping(value = "check")
public class RecheckController extends BaseController {
	private static final Log log = LogFactory.getLog(RecheckController.class);
	private static final String NAV_TAB_ID = "billgl";
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private SingleInfoService singleService;

	/**
	 * to cheack notes page
	 * 
	 * @return
	 */
	@RequiresPermissions("base:checkbill:add")
	@RequestMapping(value = "addUi")
	public String toCheackUi() {
		return "base/bill/recheckNotes";
	}
	
	/**
	 * 添加单件明细内容
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addMx")
	public String addMx(HttpServletRequest request,
			@RequestParam("mids") String ids,@RequestParam("wz")String wz, Page<SingleInfo> page,
			Model model) {
		String wzName=URLDecoder.decode(wz),mids=URLDecoder.decode(ids);
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SingleInfo> list = singleService.findSingleNotInMids(page, filters, mids, "030",wzName);
		model.addAttribute("page", list);

		return "base/bill/addMx";
	}
	
	/**
	 * 添加单据
	 * 
	 * @param single 
	 * @param billHead 表头信息
	 * @return
	 */
	@RequestMapping(value = "/addBill")
	@ResponseBody
	public Map<String, Object> addBill(SingleForm singles, BillHeadModel bill) {
		try {
			// save billheadInfo
			billService.saveBillHeadAndBody(singles.getSingles(), bill, "040",bill.getAcceptLocation());
			
			return DwzUtil.dialogAjaxDone(DwzUtil.OK, NAV_TAB_ID);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("system error", e.getCause());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}


}
