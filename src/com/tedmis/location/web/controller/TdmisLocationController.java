package com.tedmis.location.web.controller;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedmis.location.service.TdmisLocationService;
import com.ylbms.common.web.BaseController;

@Controller
@RequestMapping(value = "tdmis")
public class TdmisLocationController extends BaseController {

	@Autowired
	private TdmisLocationService tdmisService;

	@RequestMapping(value = "test")
	@ResponseBody
	@RequiresUser
	public Object test() {
		return tdmisService.getTdmisLocation("00000000000311");
	}
}
