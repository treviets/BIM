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
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

	@Autowired
	ClientService clientService;

	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;

	@Autowired
	UserUtil userUtil;

	@Autowired
	ServletContext application;

	String rootDirectory;
	String attach;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "project";
	}

	@RequestMapping(value = "/showformtoadd", method = { RequestMethod.GET })
	public String addProject(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "Project_form";
	}

	// Detail Task
	@RequestMapping(value = "/detailTask")
	public String detailTask() {
		return "Detail_Task";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> project, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String updateBy = userUtil.getLoginedUsername();
		project.put("clientId", clientId);
		project.put("updateBy", updateBy);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_ADD;
		Map<String, Object> result = (Map<String, Object>) RestAPIHelper.post(redsunServiceUrl, project);
		Map<String, Object> subResultMap = (Map<String, Object>) result.get("result");
		// create default directory

		Map<String, Object> resultDoc = new HashMap<String, Object>();
		String createDirectoryURL = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_CREATE_LIST
				+ subResultMap.get("projects") + "/" + project.get("code") + "/" + updateBy + "/" + clientId;
		RestAPIHelper.get(createDirectoryURL, resultDoc);
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/design/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object createDesignProject(@RequestBody Map<String, Object> project, HttpServletRequest request)
			throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String updateBy = userUtil.getLoginedUsername();
		project.put("clientId", clientId);
		project.put("updateBy", updateBy);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DESIGN_PROJECTS_URL_ADD;
		Map<String, Object> result = (Map<String, Object>) RestAPIHelper.post(redsunServiceUrl, project);
		Map<String, Object> subResultMap = (Map<String, Object>) result.get("result");
		// create default directory

		Map<String, Object> resultDoc = new HashMap<String, Object>();
		String createDirectoryURL = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_CREATE_LIST
				+ subResultMap.get("projects") + "/" + project.get("code") + "/" + updateBy + "/" + clientId;
		RestAPIHelper.get(createDirectoryURL, resultDoc);
		return result;
	}

	// Edit form
	@RequestMapping("/detail/{id}")
	public String showJspEdit(@PathVariable("id") Integer id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("id", id);
		return "project-detail";
	}

	// Update.
	@RequestMapping(value = "update/{id}", headers = "Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> project,
			HttpServletRequest request) throws Exception {
		String updateBy = userUtil.getLoginedUsername();
		project.put("updateBy", updateBy);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, project, null);
		return result;
	}

	@RequestMapping(value = "/getbyid/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getById(@PathVariable("projectId") Integer projectId, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GETBYID + projectId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	@RequestMapping(value = "/getbyname", method = { RequestMethod.GET })
	@ResponseBody
	public Object getName(@RequestParam("projectname") String projectName, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GETBYNAME + projectName;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	@RequestMapping(value = "/get/by-resource", method = { RequestMethod.GET })
	@ResponseBody
	public Object getByResourceId(HttpServletRequest request) throws Exception {
		if("admin".equals(userUtil.getLoginedUsername())) {
			return getAll(request);
		}
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_BY_RESOURCE + userUtil.getLoginedUsername()+"/";
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	@RequestMapping(value = "/categories", method = { RequestMethod.GET })
	@ResponseBody
	public Object getCategory(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_CATEGORIES + "/" + clientId;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/ganttchart/data/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getProjectGanttChartData(@PathVariable("projectId") int projectId, HttpServletRequest request)
			throws Exception {
		String userName = userUtil.getLoginedUsername();
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_GANTTCHART_DATA + "/" + clientId
				+ "/" + projectId + "/" + userName+"/";
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(HttpServletRequest request) throws Exception {
		int client_Id = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_ALL + "/" + client_Id;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/design/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllForDesignProject(HttpServletRequest request) throws Exception {
		int client_Id = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_ALL + "/" + client_Id;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/list/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getProjects(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo,
			HttpServletRequest request) throws Exception {
		int client_Id = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.PROJECTS_URL_GET_ALL + "/" + client_Id + "/"
				+ itemsPerPage + "/" + pageNo;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/design/list/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getDesignProjectData(@PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.DESIGN_PROJECTS_URL_GET_ALL + "/" + clientId
				+ "/" + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	// List screen.
	@RequestMapping("showpageproject")
	public String showJspProject() {
		return "project";
	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	// default create prior document
	@RequestMapping(value = "/socialupfile/{projectId}/888", method = { RequestMethod.POST })
	@ResponseBody
	public Object upfile(@PathVariable("projectId") Integer projectId, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		rootDirectory = application.getRealPath(File.separator + "static" + File.separator);
		String rootFolderName = "document" + File.separator + "project" + projectId + File.separator + "888"
				+ File.separator;
		rootFolderName = rootFolderName.toLowerCase();
		try {
			File thedir = new File(rootDirectory + File.separator + rootFolderName);
			thedir.mkdirs();
			attach = File.separator + "static" + File.separator + rootFolderName + file.getOriginalFilename();
			File f = new File(thedir + File.separator + file.getOriginalFilename());
			file.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		Result result = new Result();
		result.setStatus(1);
		result.setDescription("Success");
		return result;
	}
}
