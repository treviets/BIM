package com.redsun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.entities.Result;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@RestController
@RequestMapping(value = "/taskequipment")
public class TaskEquipmentsController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/getbytask/{taskId}/{projectId}", method = { RequestMethod.GET })
	public Object getByTask(@PathVariable("taskId")final Integer taskId, @PathVariable("projectId")final Integer projectId, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		Map<String, Object> projectEquipment = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.TASK_EQUIPMENT_GET_BY_TASK + taskId + "/" + projectId + "/" + clientId;
		Result result = RestAPIHelper.get(getDataURL, projectEquipment);
		return result;
	}

	@RequestMapping(value = "/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	public Object create(@RequestBody List<Object> taskEquipment, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_EQUIPMENT_ADD;
		Object result =  RestAPIHelper.post(redsunServiceUrl, taskEquipment);
		return result;
	}

	// update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Object updateEquipment(@PathVariable("id") final Integer id, @RequestBody Map<String, Object> taskEquipment, HttpServletRequest request)
			throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_EQUIPMENT_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, taskEquipment, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl =  getMainDomain(request) + RedSunURLs.TASK_EQUIPMENT_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
}
