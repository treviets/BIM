package com.redsun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@EnableWebMvc
public class TypesController extends BaseController {

	@Autowired
	UserUtil userUtil;

	// List all types
	@RequestMapping(value = "/type/listall", method = { RequestMethod.GET })
	@ResponseBody
	public Object listAllTypes(@RequestParam("typevalue") String typeValue, HttpServletRequest request)
			throws Exception {
		
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getTypeDataURL = getMainDomain(request) + RedSunURLs.TYPES_URL_GET_ALL + "/" + clientId + "/"
				+ typeValue;
		return RestAPIHelper.get(getTypeDataURL, new HashMap<String, Object>());
	}

	// List all types use for task
	@RequestMapping(value = "/type/all", method = { RequestMethod.GET })
	@ResponseBody
	public Object allTypes(HttpServletRequest request) throws Exception {
		String getTypeDataURL = getMainDomain(request) + RedSunURLs.TYPES_URL_GET_ALL_USE_FOR_TASK;
		return RestAPIHelper.get(getTypeDataURL, new HashMap<String, Object>());
	}

	// all types
	@RequestMapping(value = "/type/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object listAll(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getTypeDataURL = getMainDomain(request) + RedSunURLs.TYPES_URL_ALL + clientId;
		return RestAPIHelper.get(getTypeDataURL, new HashMap<String, Object>());
	}
}
