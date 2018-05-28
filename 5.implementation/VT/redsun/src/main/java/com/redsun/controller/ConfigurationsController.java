package com.redsun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * configurations Controller
 */
@Controller
@RequestMapping("configurations")
public class ConfigurationsController extends BaseController{
	@Autowired
	ClientService clientService;

	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object addAffectation(@RequestBody ArrayList<Object> configurations, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CONFIG_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, configurations);
		return result;
	}

	@RequestMapping(value = "/getbyproject/{projecId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getByProject(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> configuration = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_GET_BY_PROJECT_ID + projectId + "/" + clientId;
		return RestAPIHelper.get(getDataURL, configuration);
	}
}