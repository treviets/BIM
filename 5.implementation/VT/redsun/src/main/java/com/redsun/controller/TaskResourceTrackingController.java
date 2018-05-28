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
@RequestMapping(value = "/taskresourcetracking")
public class TaskResourceTrackingController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> taskresourcetracking, HttpServletRequest request) throws Exception {

		taskresourcetracking.put("owner", userUtil.getLoginedUsername());
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASKRESOURCES_URL_TRACKING_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, taskresourcetracking);
		return result;
	}

}
