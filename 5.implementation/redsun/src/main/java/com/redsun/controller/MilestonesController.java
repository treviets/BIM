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
 * Milestone Controller
 */
@Controller
@RequestMapping("milestone")
public class MilestonesController extends BaseController{
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addMilestone (Model model){
		model.addAttribute("id", -1);
		return "Milestones_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addMilestone(@RequestBody Map<String, Object> milestone, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.MILESTONE_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, milestone);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editMilestone(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Milestones_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateMilestone(@PathVariable("id") Integer id, @RequestBody Map<String, Object> milestone, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.MILESTONE_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, milestone, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteMilestone(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.MILESTONE_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listMilestone(){
		return "Milestones_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getMilestoneById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.MILESTONE_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listMilestonesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> milestone = new HashMap<String, Object>();
		String serviceUrl = getMainDomain(request) + RedSunURLs.MILESTONE_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, milestone);
	}
	
	// List by projectId.
	@RequestMapping(value="list/{projectId}", method=RequestMethod.GET)
	@ResponseBody
	public Object listMilestoneByProjectId(@PathVariable("projectId") Integer projectId, HttpServletRequest request){
		String serviceUrl = getMainDomain(request) + RedSunURLs.MILESTONE_URL_SERVICE_LIST + projectId;
		Object result = RestAPIHelper.get(serviceUrl, new HashMap<String, Object>());
		return result;
	}

}
