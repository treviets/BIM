package com.redsun.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

@Controller
@EnableWebMvc
@RequestMapping(value = "priority")
public class PriorityController extends BaseController{

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/{name}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getPriorityData(@PathVariable("name") String name, @PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, Model model, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> priority = new HashMap<String, Object>();
		String getPriorityDataURL = getMainDomain(request) + RedSunURLs.PRIORITY_URL_SELECT + clientId + "/" + name + "/" + itemsPerPage + "/"
				+ pageNo;
		return RestAPIHelper.get(getPriorityDataURL, priority);
	}
	

	 @RequestMapping(value="/list", method={RequestMethod.GET})
	    @ResponseBody
	    public Object getAll(HttpServletRequest request) throws Exception  {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			User user = clientService.getClient(userName);
			int clientId = user.getClientId();
	    	String getDataURL = getMainDomain(request) + RedSunURLs.PRIORITIES_URL_GET_ALL + clientId;
	    	return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	    }

	// Add form
	@RequestMapping("/addform")
	public String showJspAdd(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "Priority_form";
	}

	// Add form
	@RequestMapping("/edit/{id}")
	public String showJspEdit(@PathVariable("id") Integer id, @RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("id", id);
		return "Priority_form";
	}

	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object addPriority(@RequestBody Map<String, Object> priority, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PRIORITY_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, priority);
		return result;
	}

	// Update.
	@RequestMapping(value = "update/{id}", headers="Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> priority, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PRIORITY_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, priority, null);
		return result;
	}

	// Get by Id.
	@RequestMapping("/getbyid/{id}")
	@ResponseBody
	public Object getPriorityById(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PRIORITY_URL_GETBYID + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;

	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deletePriority(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PRIORITY_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

}
