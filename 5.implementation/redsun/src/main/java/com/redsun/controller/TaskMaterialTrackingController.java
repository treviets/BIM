package com.redsun.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@RequestMapping(value = "/taskmaterialtracking")
public class TaskMaterialTrackingController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> taskmaterialtracking, HttpServletRequest request) throws Exception {
		taskmaterialtracking.put("owner", userUtil.getLoginedUsername());
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_MATERIAL_TRACKING_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, taskmaterialtracking);
		return result;
	}

}
