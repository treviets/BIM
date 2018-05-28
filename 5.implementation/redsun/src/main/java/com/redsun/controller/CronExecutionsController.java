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
 * CronExecution Controller
 */
@Controller
@RequestMapping("cronexecution")
public class CronExecutionsController extends BaseController{
	
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addCronExecution (Model model){
		model.addAttribute("id", -1);
		return "CronExecutions_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addCronExecution(@RequestBody Map<String, Object> cronExecution, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CRON_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, cronExecution);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editCronExecution(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "CronExecutions_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateCronExecution(@PathVariable("id") Integer id, @RequestBody Map<String, Object> cronExecution, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CRON_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, cronExecution, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteCronExecution(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CRON_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listCronExecution(){
		return "CronExecutions_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getCronExecutionById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CRON_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listCronExecutionsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> cronExecution = new HashMap<String, Object>();
		String serviceUrl = getMainDomain(request) + RedSunURLs.CRON_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, cronExecution);
	}

}
