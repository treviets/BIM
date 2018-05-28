package com.redsun.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.entities.Result;
import com.redsun.service.ClientService;
import com.redsun.service.ProjectService;
import com.redsun.service.UserService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@EnableWebMvc
@RequestMapping(value = "/projectbaseline")
public class ProjectBaseLineController extends BaseController {

	@Autowired
	ClientService clientService;
	@Autowired
	UserUtil userUtil;

	@Autowired
	ServletContext application;

	@RequestMapping(value = "/list/labor/{projectId}/{baselineId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getProjectLaborBaseLine(@PathVariable("projectId") Integer projectId, @PathVariable("baselineId") Integer baselineId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_PRO_LABOR_BASELINE + "/" + clientId + "/" +  projectId+ "/" + baselineId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	@RequestMapping(value = "/insertbatch/labor/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertLaborBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_LABOR_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/insertbatch/material/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertMaterialBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_MATERIAL_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/insertbatch/equipment/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertEquipmentBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_EQUIPMENT_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/insertbatch/task/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertTaskBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_TASK_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/insertbatch/taskresource/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertTaskResourceBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_TASK_RESOURCE_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/insertbatch/taskmaterial/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertTaskMaterialBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_TASK_MATERIAL_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/insertbatch/taskequipment/{projectId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object insertTaskEquimentBaseLine(@RequestBody Map<String, Object> baseline, @PathVariable("projectId") Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_INSERTBATCH_PRO_TASK_EQUIPMENT_BASELINE + "/" + clientId + "/" + projectId;
		Object result = RestAPIHelper.post(redsunServiceUrl, baseline);
		return result;
	}
	@RequestMapping(value = "/list/baseline/{projectId}/{type}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getBaseLinebyType(@PathVariable("projectId") int projectId, @PathVariable("type") int type, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_BASELINE + "/" + projectId + "/" + type + "/" + clientId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
}
