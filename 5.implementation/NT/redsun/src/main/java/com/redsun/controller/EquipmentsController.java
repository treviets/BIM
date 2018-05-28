package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.FileUtil;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Equipments Controller
 */
@Controller
public class EquipmentsController extends BaseController{
	
	@Autowired
	ClientService clientService;

	@Autowired
	UserUtil userUtil;

	// List screen.
	@RequestMapping("devices")
	public String listEquipments() {
		return "Equipments_list";
	}
	// Edit screen.
	@RequestMapping("equipment/edit/{id}")
	public String editResource(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "Equipments_form";
	}

	// show screen to insert
	@RequestMapping("equipment/showform")
	public String showForm() {
		return "Equipments_form";
	}

	@RequestMapping(value = "equipment/listall", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> equipment = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_LIST_ALL + clientId;
		return RestAPIHelper.get(getDataURL, equipment);
	}

	// List for page and filter.
	@RequestMapping(value = "equipment/list/page/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listForPage(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request)
			throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> resource = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_LIST_PAGE + clientId + "/" + itemsPerPage + "/"
				+ pageNo;
		return RestAPIHelper.get(getDataURL, resource);
	}

	@RequestMapping(value = "equipment/filterequipment/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object filterMember(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int client_Id = user.getClientId();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_FILTER + projectId + "/"
				+ client_Id;
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	// Insert.
	@RequestMapping(value = "equipment/add", method = RequestMethod.POST)
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> equipment, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		equipment.put("clientId", clientId);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, equipment);
		return result;
	}

	// Update.
	@RequestMapping(value = "equipment/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateResource(@PathVariable("id") Integer id, @RequestBody Map<String, Object> equipment, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, equipment, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "equipment/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteResource(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
	// count.
	@RequestMapping(value = "equipment/count", method = RequestMethod.GET)
	@ResponseBody
	public Object countResource(HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_COUNT + clientId;
		return RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
	}

	// get clientId to be login
	@RequestMapping(value = "equipment/getclientid", method = { RequestMethod.GET })
	@ResponseBody
	public Object getClientId() throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		return clientId;
	}

	// List by Id.
	@RequestMapping(value = "equipment/getbyid/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getResourceById(@PathVariable("id") final Integer id, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_GET_BY_ID + id + "/" + clientId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	@RequestMapping(value="equipment/import-equipment", method=RequestMethod.POST)
	@ResponseBody
	public Object importEquipment(@RequestParam("filePath") final String filePath, @RequestParam("projectId") final Integer projectId, @RequestParam("file") final MultipartFile file, HttpServletRequest request) throws Exception {
		Object result = null;
		
		String serviceUrl = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_IMPORT + "?filePath="+filePath + "&projectId=" + projectId;
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
	@RequestMapping(value = "equipment/checkexist/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object checkExist(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> equipment = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.EQUIPMENT_URL_SERVICE_CHECK_EXIST + clientId + "/" + id;
		return RestAPIHelper.get(getDataURL, equipment);
	}
}
