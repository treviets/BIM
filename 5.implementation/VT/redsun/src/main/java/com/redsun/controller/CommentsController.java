package com.redsun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@RequestMapping("/comment")
public class CommentsController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;
	
	// Get by Id.
	@RequestMapping("/getbytaskid/{taskId}")
	@ResponseBody
	public Object getById(@PathVariable("taskId") final Integer taskId, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.COMMENT_URL_GET_BY_TASKID + taskId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;

	}

	
}
