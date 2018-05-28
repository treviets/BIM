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

import com.redsun.entities.Result;
import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * ProjectEquipments Controller
 */
@Controller
@RequestMapping("projectequipment")
public class ProjectEquipmentsController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;
	
	@RequestMapping(value = "/list/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllDataOneProject(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		Map<String, Object> projectEquipment = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_EQUIPMENTS_URL_GET_ALL + projectId + "/" + clientId;
		Result result = RestAPIHelper.get(getDataURL, projectEquipment);
		return result;
	}

	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object addProjectEquipment(@RequestBody List<Object> projectEquipments, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECT_EQUIPMENTS_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, projectEquipments);
		return result;
	}

	@RequestMapping(value = "/filterprojectequipment/{projectId}/{taskId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object filterProjectEquipment(@PathVariable("projectId") final Integer projectId,
			@PathVariable("taskId") final Integer taskId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> projectEquipments = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_EQUIPMENTS_URL_SERVICE_FILTER + projectId + "/" + taskId + "/" + clientId;
		return RestAPIHelper.get(getDataURL, projectEquipments);
	}

	// Update.
	@RequestMapping(value = "update/{id}", headers = "Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> projectEquipments, HttpServletRequest request) {
		String getDataURL = getMainDomain(request) + RedSunURLs.PROJECT_EQUIPMENTS_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(getDataURL, projectEquipments, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECT_EQUIPMENTS_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
}
