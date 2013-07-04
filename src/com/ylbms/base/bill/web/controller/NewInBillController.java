package com.ylbms.base.bill.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * 新品合格入库单
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-18
 */
@Controller
@RequestMapping(value = "/new")
public class NewInBillController extends BaseController {

	private static final Log log = LogFactory.getLog(NewInBillController.class);

	private static final String NAV_TAB_ID = "billgl";

	@Autowired
	SingleInfoService singleService;

	@Autowired
	BillService billservice;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newUi")
	public String newUi(Model model) {
		return "base/bill/newInput";
	}

	/**
	 * 添加单件明细内容
	 * 
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addMx")
	public String addMx(HttpServletRequest request,
			@RequestParam("mids") String mids,@RequestParam("wz")String wzName, Page<SingleInfo> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SingleInfo> list = singleService.findSingleNotInMids(page, filters, mids, "010",wzName);
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
			billservice.saveBillHeadAndBody(singles.getSingles(), bill, "020","基地");
			
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
