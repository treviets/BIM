package com.redsun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Statuses Controller
 */
@Controller
@RequestMapping("statuses")
public class StatusesController extends BaseController {

	@Autowired
	UserUtil userUtil;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getDataURL = getMainDomain(request) + RedSunURLs.STATUSES_URL_GET_ALL + "/" + clientId;
		return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/getbyscope", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(@RequestParam("scopeValue") final String scopeValue, HttpServletRequest request)
			throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getDataURL = getMainDomain(request) + RedSunURLs.STATUSES_URL_GET_BY_SCOPE + "/" + clientId + "/"
				+ scopeValue;
		return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	}
}
