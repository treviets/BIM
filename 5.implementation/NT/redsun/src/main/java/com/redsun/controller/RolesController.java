package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Role Controller
 */
@Controller
@RequestMapping("role")
public class RolesController extends BaseController{
	
	@Autowired
	private UserUtil userUtil;
	@Autowired
	ClientService clientService;
	// Form screen.
	@RequestMapping("form")
	public String formRole(@RequestParam("id") Integer id, Model model){
		if(id == null) {
			model.addAttribute("id", -1);
		} else {
			model.addAttribute("id", id);
		}
		
		return "Financial/Incomes/Roles_form";
	}
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addRole (Model model){
		model.addAttribute("id", -1);
		return "Financial/Incomes/Roles_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addRole(@RequestBody Map<String, Object> role, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		role.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, role);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editRole(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Financial/Incomes/Roles_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateRole(@PathVariable("id") Integer id, @RequestBody Map<String, Object> role, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		role.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, role, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteRole(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listRole(){
		return "Financial/Incomes/Roles_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getRoleById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listRolesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo
	, HttpServletRequest request) {
		Map<String, Object> role = new HashMap<String, Object>();

		String serviceUrl = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_LIST + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, role);
	}
	
	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listRolesExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestParam Map<String, Object> filter, HttpServletRequest request) {
		String serviceUrl = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, filter);
	}

	// get all roles
	@RequestMapping(value = "/listall", method = { RequestMethod.GET })
	@ResponseBody
	public Object listAllTypes(HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String getTypeDataURL = getMainDomain(request) + RedSunURLs.ROLE_URL_SERVICE_LIST_ALL + clientId;
		return RestAPIHelper.get(getTypeDataURL, new HashMap<String, Object>());
	}

}
