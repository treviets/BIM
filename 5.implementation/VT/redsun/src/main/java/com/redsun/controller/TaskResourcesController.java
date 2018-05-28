package com.redsun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Assignment Controller
 */
@RestController
@RequestMapping("taskresources")
public class TaskResourcesController extends BaseController{

	@Autowired
	ClientService clientService;
	
	@RequestMapping(value = "/getbytask/{taskId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("taskId") final Integer taskId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		String getDataURL = getMainDomain(request) + RedSunURLs.TASKRESOURCES_URL_GET_BY_TASK + taskId + "/" + clientId;
		return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/gettaskresourcesbyproject/{projectId}", method = { RequestMethod.GET })
	public Object getTaskResourcesByProject(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		String getDataURL = getMainDomain(request) + RedSunURLs.TASKRESOURCES_URL_GET_BY_PROJECT + projectId + "/" + clientId;
		return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	}

	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object addAssignments(@RequestBody List<Object> assignments, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASKRESOURCES_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, assignments);
		return result;
	}

	// update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Object updateAssignment(@PathVariable("id") final Integer id, @RequestBody Map<String, Object> assignments, HttpServletRequest request)
			throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASKRESOURCES_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, assignments, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl =  getMainDomain(request) + RedSunURLs.TASKRESOURCES_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

}
