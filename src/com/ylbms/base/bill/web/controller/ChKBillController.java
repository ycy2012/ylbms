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
 * 出库信息管理模块
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-2
 */
@Controller
@RequestMapping(value = "/ckgl")
public class ChKBillController extends BaseController {

	private static final Log log = LogFactory.getLog(ChKBillController.class);

	private static final String NAV_TAB_ID = "billgl";

	@Autowired
	SingleInfoService singleService;

	@Autowired
	BillService billservice;

	/**
	 * 跳转出库信息管理页面
	 * 
	 * @return
	 */
	@RequiresPermissions("base:ckgl:add")
	@RequestMapping(value = "/addUi")
	public String addUi() {
		return "base/bill/chkInput";
	}

	/**
	 * 添加明细
	 * 
	 * @param request
	 * @param mids
	 * @param page
	 * @param model
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addMx")
	public String addMx(HttpServletRequest request,
			@RequestParam("mids") String mids,@RequestParam("wz") String wzName, Page<SingleInfo> page,
			Model model) {
		wzName=URLDecoder.decode(wzName);mids=URLDecoder.decode(mids);   //解码处理
		
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SingleInfo> list = singleService.findSingleNotInMids(page,
				filters, mids, "020",wzName);
		model.addAttribute("page", list);

		return "base/bill/addMx";

	}

	/**
	 * 添加单据
	 * 
	 * @param single
	 * @param billHead
	 * @return
	 */
	@RequestMapping(value = "/addBill")
	@ResponseBody
	public Map<String, Object> addNewBill(SingleForm singles, BillHeadModel bill) {
		try {
			// save billheadInfo
			billservice.saveBillHeadAndBody(singles.getSingles(), bill, "030",bill.getAcceptLocation());

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
