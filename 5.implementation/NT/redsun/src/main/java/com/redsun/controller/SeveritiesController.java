package com.redsun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Likelihood Controller
 */
@Controller
@RequestMapping("severities")
public class SeveritiesController extends BaseController{
	
	@Autowired
	ClientService clientService;
	
	 @RequestMapping(value="/list", method={RequestMethod.GET})
	    @ResponseBody
	    public Object getAll(HttpServletRequest request) throws Exception  {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	String userName = auth.getName();
	    	User user = clientService.getClient(userName);
	    	int client_Id = user.getClientId();
	    	String getDataURL = getMainDomain(request) + RedSunURLs.SEVERITIES_URL_GET_ALL + "/" + client_Id;
	    	return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	    }
}
