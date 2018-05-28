package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Client Controller
 */
@Controller
@RequestMapping("client")
public class ClientsController extends BaseController{

	@Autowired
	ClientService clientService;
	@Autowired
	UserUtil userUtil;
	
	// Add screen.
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addClient(Model model) {
		model.addAttribute("id", -1);
		return "Clients_form";
	}

	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addClient(@RequestBody Map<String, Object> client, HttpServletRequest request)
			throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		client.put("clientId", clientId);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CLIENT_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, client);
		return result;
	}

	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editClient(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "Clients_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateClient(@PathVariable("id") Integer id, @RequestBody Map<String, Object> client, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CLIENT_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, client, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteClient(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CLIENT_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	// List screen.
	@RequestMapping("listpage")
	public String listClient() {
		return "Clients_list";
	}

	// List by Id.
	@RequestMapping("/getbyid/{id}")
	@ResponseBody
	public Object getClientById(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CLIENT_URL_SERVICE_GET_BY_ID + clientId + "/" + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(HttpServletRequest request) throws Exception {

		String getCustomerDataURL = getMainDomain(request) + RedSunURLs.CLIENT_URL_SERVICE_LIST_ALL;
		return RestAPIHelper.get(getCustomerDataURL, new HashMap<String, Object>());
	}

	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listCustomerForPageAndFilter(@PathVariable("itemsPerPage") Integer itemsPerPage,
			@PathVariable("pageNo") Integer pageNo, HttpServletRequest request) {
		Map<String, Object> customer = new HashMap<String, Object>();

		String serviceUrl = getMainDomain(request) + RedSunURLs.CLIENT_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, customer);
	}
}
