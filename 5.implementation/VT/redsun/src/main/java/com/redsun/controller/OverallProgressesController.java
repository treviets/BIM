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
 * OverallProgresse Controller
 */
@Controller
@RequestMapping("overallprogresse")
public class OverallProgressesController extends BaseController{
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addOverallProgresse (Model model){
		model.addAttribute("id", -1);
		return "OverallProgresses_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addOverallProgresse(@RequestBody Map<String, Object> overallProgresse, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.OVERALL_PROGRESSES_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, overallProgresse);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editOverallProgresse(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "OverallProgresses_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateOverallProgresse(@PathVariable("id") Integer id, @RequestBody Map<String, Object> overallProgresse, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.OVERALL_PROGRESSES_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, overallProgresse, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteOverallProgresse(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.OVERALL_PROGRESSES_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listOverallProgresse(){
		return "OverallProgresses_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getOverallProgresseById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.OVERALL_PROGRESSES_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listOverallProgressesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> overallProgresse = new HashMap<String, Object>();
		String serviceUrl = getMainDomain(request) + RedSunURLs.OVERALL_PROGRESSES_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, overallProgresse);
	}

}
