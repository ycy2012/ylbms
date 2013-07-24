package com.ylbms.base.bill.web.controller;

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

import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.base.bill.model.BillTbodyModel;
import com.ylbms.base.bill.service.BillHeadService;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.DwzUtil;
import com.ylbms.common.web.BaseController;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@Controller
@RequestMapping(value = "/bill")
public class BillController extends BaseController {

	private static final Log log = LogFactory.getLog(BillController.class);

	private static final String NAV_TAB_ID = "bill";

	@Autowired
	BillHeadService billHService;
	@Autowired
	SingleInfoService singleInfoService;

	/**
	 * 修改单据信息
	 * 
	 * @param djID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editUi/{djId}")
	public String editUi(@PathVariable("djId") String djId, Model model) {
		BillHeadModel billH=billHService.getBillHeadByID(djId);
		model.addAttribute("bill", billH); 
		return "base/bill/editBill";
	}

	/**
	 * 查看单件详细信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewUi/{djId}")
	public String viewUi(@PathVariable("djId") String djId, Model model) {
		BillHeadModel billH=billHService.getBillHeadByID(djId);
		model.addAttribute("billH", billH);
		return "base/bill/viewBill";
	}

	/**
	 * 删除单据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") String id) {
		try {
			billHService.deleteBill(id);
			return DwzUtil.dialogAjaxDone(DwzUtil.OK);
		} catch (Exception e) {
			log.error("system error" + e.getMessage());
			return DwzUtil.dialogAjaxDone(DwzUtil.FAIL, NAV_TAB_ID,
					e.getMessage());
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Page<BillHeadModel> page,
			Model model) {
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		Page<BillHeadModel> list = billHService.searchBill(page, filters);
		model.addAttribute("page", list);
		return "base/bill/list";
	}

}
