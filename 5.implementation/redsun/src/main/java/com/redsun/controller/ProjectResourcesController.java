package com.redsun.controller;

import java.util.HashMap;
import java.util.List;
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

import com.redsun.dao.UserDao;
import com.redsun.entities.Result;
import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * ProjectResource Controller
 */
@Controller
@RequestMapping("projectresource")
public class ProjectResourcesController extends BaseController{
	@Autowired
	ClientService clientService;

	@Autowired
	UserDao userDao;
	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object addProjectResources(@RequestBody List<Object> projectResources, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECT_RESOURCES_URL_SERVICE_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, projectResources);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listforoneproject/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getListResourcesOneProject(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> projectResourceMap = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_RESOURCES_URL_SERVICE_BY_PROJECT + projectId + "/" + clientId;
		Result result = RestAPIHelper.get(getDataURL, projectResourceMap);
		Map<Object, Object>resultMap = result.getResult();

		List<Map> resourceObjects = (List<Map>)resultMap.get("projectresources");
		for(Map projectResource : resourceObjects){
			projectResource.put("projectRoleId", userDao.getModuleRoleIdByUsername(String.valueOf(projectResource.get("resourceCode")), Integer.valueOf(String.valueOf(projectResource.get("projectId")))));
		}
		return result;
	}

	@RequestMapping(value = "/filterprojectresource/{projectId}/{taskId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object filterResources(@PathVariable("projectId") final Integer projectId,
			@PathVariable("taskId") final Integer taskId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> projectResource = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_RESOURCES_URL_SERVICE_FILTER + projectId + "/"
				+ taskId + "/" + clientId;
		return RestAPIHelper.get(getDataURL, projectResource);
	}

	@RequestMapping(value = "/getallprojectresources", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllProjectResources(HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> projectResource = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_RESOURCES_URL_SERVICE_GET_ALL + clientId;
		return RestAPIHelper.get(getDataURL, projectResource);
	}

	// Update.
	@RequestMapping(value = "updatehr/{id}", headers = "Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> projectResource, HttpServletRequest request) {
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_RESOURCES_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(getDataURL, projectResource, null);
		return result;
	}
	
	// Delete.
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
		@ResponseBody
		public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
			String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECT_RESOURCES_URL_SERVICE_DELETE + id;
			Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
			return result;
		}
}
