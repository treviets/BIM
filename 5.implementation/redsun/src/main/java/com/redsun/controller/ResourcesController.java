package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.ClientService;
import com.redsun.utils.FileUtil;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Resource Controller
 */
@Controller
@RequestMapping("hr")
public class ResourcesController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;

	// Add screen.
	@RequestMapping(value = "showform", method = RequestMethod.GET)
	public String addResource(Model model) {
		return "Resources_form";
	}

	// Add screen.
	@RequestMapping(value = "showformexterior", method = RequestMethod.GET)
	public String addExterior(Model model) {
		return "Resources_Exterior_form";
	}
	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object addResource(@RequestBody Map<String, Object> resource, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		resource.put("clientId", clientId);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, resource);
		return result;
	}

	// Edit screen.
	@RequestMapping("/edit/{id}")
	public String editResource(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "Resources_form";
	}
	// Edit screen.
	@RequestMapping("/editexterior/{id}")
	public String editResourceExterior(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "Resources_Exterior_form";
	}
	// Update.
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateResource(@PathVariable("id") Integer id, @RequestBody Map<String, Object> resource, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, resource, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteResource(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	// List screen.
	@RequestMapping("")
	public String listResource() {
		return "Resources_list";
	}
	// List screen.
		@RequestMapping("listpage")
		public String listPageResource() {
			return "Resources_list";
		}
	// List exterior screen.
		@RequestMapping("exterior")
		public String listExterior() {
			return "Resources_Exterior_list";
		}

	// List by Id.
	@RequestMapping("/getbyid/{id}")
	@ResponseBody
	public Object getResourceById(@PathVariable("id") final Integer id, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_GET_BY_ID + clientId + "/" + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	// List for page and filter.
	@RequestMapping(value = "/list/page/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listResourcesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		Map<String, Object> resource = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_LIST_ONPAGE + clientId + "/" + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.get(getDataURL, resource);
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_LIST + clientId;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}
	
	@RequestMapping(value = "/list-all-type/", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllForSystem(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_ALL_TYPE_URL_SERVICE_LIST + clientId;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}
	
	@RequestMapping(value = "/deleted/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllDeletedResource(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getDeletedResourceURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_DELETE_LIST + clientId;
		return RestAPIHelper.get(getDeletedResourceURL, new HashMap<String, Object>());
	}
	
	@RequestMapping(value = "/restore/{resourceId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object restoreResource(@PathVariable("resourceId") final Integer resourceId, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getRestoreResourceURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_RESTORE +resourceId+"/"+clientId;
		return RestAPIHelper.get(getRestoreResourceURL, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/filtermember/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object filterMember(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_FILTER + projectId + "/" + clientId;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	// count.
	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public Object countResource(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_COUNT + clientId;
		return RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
	}

	// get clientId to be login
	@RequestMapping(value = "/getclientid", method = { RequestMethod.GET })
	@ResponseBody
	public Object getClientId() throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		return clientId;
	}
	// List for exterior
		@RequestMapping(value = "/exterior/page/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
		@ResponseBody
		public Object listResourcesExterior(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) throws Exception {
			int clientId = userUtil.getClientIdOfLoginedUser();
			Map<String, Object> resource = new HashMap<String, Object>();
			String getDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_LIST_EXTERIOR + clientId + "/" + itemsPerPage + "/" + pageNo;
			return RestAPIHelper.get(getDataURL, resource);
		}
		
		@RequestMapping(value="import-resource", method=RequestMethod.POST)
		@ResponseBody
		public Object importResource(@RequestParam("filePath") final String filePath, @RequestParam("projectId") final Integer projectId, @RequestParam("file") final MultipartFile file, HttpServletRequest request) throws Exception {
			Object result = null;
			
			String serviceUrl = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_IMPORT + "?filePath=" + filePath + "&projectId=" + projectId;
			Integer clientId = userUtil.getClientIdOfLoginedUser();
			if(clientId != null && clientId > 0) {
				serviceUrl += "&clientId=" + clientId;
			}
			
			// Post file to service.
		    LinkedMultiValueMap<String, Object> map = FileUtil.saveFileToLocalReturnLinkedMultiValueMap(file);
	        // Transfer files as resources to service.
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
	        result = RestAPIHelper.post(serviceUrl, requestEntity);
	        
		    return result;	
		}

		@RequestMapping(value = "/filtertitle", method = { RequestMethod.GET })
		@ResponseBody
		public Object filterTitle(HttpServletRequest request) throws Exception {
			int clientId = userUtil.getClientIdOfLoginedUser();
			String getProjectDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_FILTER_TITLE + clientId;
			return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
		}
		@RequestMapping(value = "/checkexist/{id}", method = { RequestMethod.GET })
		@ResponseBody
		public Object getToExist(@PathVariable("id") final String id, HttpServletRequest request) throws Exception {
			int clientId = userUtil.getClientIdOfLoginedUser();
			String getProjectDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_URL_SERVICE_CHECK_EXIST + clientId + "/" + id;
			return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
		}

}
