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
 * 新品合格入库单
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-18
 */
@Controller
@RequestMapping(value = "new")
public class NewInBillController extends BaseController {

	private static final Log log = LogFactory.getLog(NewInBillController.class);

	private static final String NAV_TAB_ID = "billgl";

	@Autowired
	private SingleInfoService singleService;

	@Autowired
	private BillService billservice;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("base:newbill:add")
	@RequestMapping(value = "newUi")
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
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "addMx")
	public String addMx(HttpServletRequest request,
			@RequestParam("mids") String ids, @RequestParam("wz") String wz,
			Page<SingleInfo> page, Model model) {
		String wzName = URLDecoder.decode(wz), mids = URLDecoder.decode(ids);
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SingleInfo> list = singleService.findSingleNotInMids(page,
				filters, mids, "010", wzName);
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
	@RequestMapping(value = "addBill")
	@ResponseBody
	public Map<String, Object> addBill(SingleForm singles, BillHeadModel bill) {
		try {
			log.debug("save bill infos");
			// save billheadInfo
			billservice.saveBillHeadAndBody(singles.getSingles(), bill, "020",
					bill.getAcceptLocation());
			return DwzUtil.dialogAjaxDoneForward(DwzUtil.OK, "bill/list");
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isErrorEnabled()) {
				log.error("system error! " + e.getMessage());
			}
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}
}
