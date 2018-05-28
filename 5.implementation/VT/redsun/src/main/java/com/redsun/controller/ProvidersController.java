package com.redsun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Provider Controller
 */
@Controller
@RequestMapping("provider")
public class ProvidersController extends BaseController{
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addProvider (Model model){
		model.addAttribute("id", -1);
		return "Providers_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addProvider(@RequestBody Map<String, Object> provider, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROVIDER_URL_SERVICE_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, provider);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editProvider(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Providers_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateProvider(@PathVariable("id") Integer id, @RequestBody Map<String, Object> provider, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROVIDER_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, provider, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteProvider(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROVIDER_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listProvider(){
		return "Providers_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getProviderById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROVIDER_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listProvidersForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo
	, HttpServletRequest request) {
		Map<String, Object> provider = new HashMap<String, Object>();
		
		String serviceUrl = getMainDomain(request) + RedSunURLs.PROVIDER_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, provider);
	}

}
