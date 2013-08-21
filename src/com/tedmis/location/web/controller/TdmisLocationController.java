package com.tedmis.location.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.tedmis.location.model.TdmisLocationDTO;
import com.tedmis.location.model.TreeBean;
import com.tedmis.location.service.TdmisLocationService;
import com.ylbms.common.utils.CacheUtils;
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
		@SuppressWarnings("unchecked")
		List<TdmisLocationDTO> list = (List<TdmisLocationDTO>) CacheUtils
				.get("treeList");
		if (null == list || list.isEmpty()) {
			list = tdmisService.getTdmisLocation();
		}
		return treeList(list);
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public Object treeList(List<TdmisLocationDTO> list) {
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
	public TreeBean setTreeBeanValues(TdmisLocationDTO t) {
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
