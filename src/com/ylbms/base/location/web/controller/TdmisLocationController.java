package com.ylbms.base.location.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.ylbms.base.location.model.TdmisLocation;
import com.ylbms.base.location.model.TreeBean;
import com.ylbms.base.location.service.TdmisLocationService;
import com.ylbms.common.web.BaseController;

@Controller
@RequestMapping(value = "tdmis")
public class TdmisLocationController extends BaseController {

	@Autowired
	private TdmisLocationService tdmisService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "commUi")
	public String commUi() {
		return "common/tdmis/commonTree";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "view")
	@RequiresUser
	public String view() {
		return "common/tdmis/locationView";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "treeList")
	@ResponseBody
	@RequiresUser
	public Object treeList() {
		return treeList(tdmisService.getTdmisLocation());
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public Object treeList(List<TdmisLocation> list) {
		List<TreeBean> treeList = Lists.newArrayList();
		for (int i = 0, len = list.size(); i < len; i++) {
			if (list.get(i) != null) {
				TreeBean bean = setTreeBeanValues(list.get(i));
				treeList.add(bean);
			}
		}
		return treeList;
	}

	/**
	 * 添加属性为treeBean
	 * 
	 * @param t
	 * @return
	 */
	public TreeBean setTreeBeanValues(TdmisLocation t) {
		int flag = t.getWzCc().length() / 4;
		TreeBean bean = new TreeBean();
		String pid = t.getWzCc().substring(0, (flag - 1) * 4);
		bean.setId(t.getWzCc().toString());
		bean.setName(t.getWzName());
		bean.setPid(pid.equals("") ? "0" : pid);
		bean.setWzCode(t.getWzId() == null ? "" : t.getWzId());
		return bean;
	}
}
