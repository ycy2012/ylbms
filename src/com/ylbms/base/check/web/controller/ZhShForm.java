package com.ylbms.base.check.web.controller;

import java.util.List;

import com.google.common.collect.Lists;
import com.ylbms.base.check.model.ZhShInfosModel;

/**
 * 证书form
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-29
 */
public class ZhShForm {

	private List<ZhShInfosModel> detail = Lists.newArrayList();

	public List<ZhShInfosModel> getDetail() {
		return detail;
	}

	public void setDetail(List<ZhShInfosModel> detail) {
		this.detail = detail;
	}
}
