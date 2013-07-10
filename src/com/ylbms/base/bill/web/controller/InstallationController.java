package com.ylbms.base.bill.web.controller;

import java.net.URLDecoder;
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
 * 压力表安装记录填写
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-8
 */
@Controller
@RequestMapping(value = "install")
public class InstallationController extends BaseController {
	
	private static final Log log = LogFactory.getLog(InstallationController.class);
	private static final String NAV_TAB_ID = "billgl";
	
	@Autowired
	private SingleInfoService  singleService;
	
	@Autowired
	private BillService billservice;
	
	@RequestMapping(value="inputUi")
	public String installUi(Model model){
		return "base/bill/installNotes";
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
		mids=URLDecoder.decode(mids);wzName=URLDecoder.decode(wzName); //解码处理
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<SingleInfo> list = singleService.findSingleNotInMids(page,
				filters, mids, "030",wzName);
		model.addAttribute("page", list);

		return "base/bill/addMx";

	}
	
	/**
	 * 添加单据记录
	 * @param singles
	 * @param bill
	 * @return
	 */
	@RequestMapping(value = "/addBill")
	@ResponseBody
	public Map<String, Object> addNewBill(SingleForm singles, BillHeadModel bill) {
		try {
			// save billheadInfo
			billservice.saveBillHeadAndBody(singles.getSingles(), bill, "040",bill.getAcceptLocation());

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
