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
 * Version Controller
 */
@Controller
@RequestMapping("version")
public class VersionsController extends BaseController{
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addVersion (Model model){
		model.addAttribute("id", -1);
		return "Versions_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addVersion(@RequestBody Map<String, Object> version, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.VERSION_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, version);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editVersion(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Versions_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateVersion(@PathVariable("id") Integer id, @RequestBody Map<String, Object> version, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.VERSION_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, version, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteVersion(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.VERSION_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listVersion(){
		return "Versions_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getVersionById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.VERSION_URL_GETBYID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listVersionsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> version = new HashMap<String, Object>();
		
		String serviceUrl = getMainDomain(request) + RedSunURLs.VERSION_URL_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, version);
	}

}
