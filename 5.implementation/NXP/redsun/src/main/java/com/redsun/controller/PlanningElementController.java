package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

@Controller
@EnableWebMvc
@RequestMapping(value = "planningelement")
public class PlanningElementController  extends BaseController{

	@Autowired
	ClientService clientService;
	
	@RequestMapping(value="/list/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
	@ResponseBody
    public Object getResourceCost(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model, HttpServletRequest request) {
		Map<String, Object> resourceCost = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_RESOURCE_COST + projectId + "/" + startDate + "/" + endDate;
        return RestAPIHelper.get(getDataURL, resourceCost);
    }
	@RequestMapping(value="/list/taskcost/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
	@ResponseBody
    public Object getTaskCost(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model, HttpServletRequest request) {
		
		Map<String, Object> taskCost = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_TASK_COST + projectId + "/" + startDate + "/" + endDate;
        return RestAPIHelper.get(getDataURL, taskCost);
    }
	@RequestMapping(value="/list/costexpenses/{projectId}/{m}/{y}", method = {RequestMethod.GET})
	@ResponseBody
    public Object getPCostExpenses(@PathVariable("projectId") int projectId
    						, @PathVariable("m") int m
    						, @PathVariable("y") int y, Model model, HttpServletRequest request) {
		
		Map<String, Object> CostExpenses = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_COST_EXPENSES + projectId + "/" + m + "/" + y;
        return RestAPIHelper.get(getDataURL, CostExpenses);
    }
	@RequestMapping(value="/list/materialusage/{projectId}", method = {RequestMethod.GET})
	@ResponseBody
    public Object getMaterialUsage(@PathVariable("projectId") int projectId, Model model, HttpServletRequest request) {
		
		Map<String, Object> listMaterials = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_MATERIAL_USAGE + projectId;
        return RestAPIHelper.get(getDataURL, listMaterials);
    }
	@RequestMapping(value="/list/materialprovided/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
	@ResponseBody
    public Object getMaterialProvidedByDuration(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model, HttpServletRequest request) {
		
		Map<String, Object> listMaterials = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_MATERIAL_PROVIDED + projectId + "/" + startDate + "/" + endDate;
        return RestAPIHelper.get(getDataURL, listMaterials);
    }
	@RequestMapping(value="/list/labor/{projectId}", method = {RequestMethod.GET})
	@ResponseBody
	public Object getLaborInProject(@PathVariable("projectId") int projectId, Model model, HttpServletRequest request) {
		
		Map<String, Object> listLabors = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_LABOR + projectId;
        return RestAPIHelper.get(getDataURL, listLabors);
    }
	@RequestMapping(value="/list/equipment/{projectId}", method = {RequestMethod.GET})
	@ResponseBody
	public Object getEquipmentInProject(@PathVariable("projectId") int projectId, Model model, HttpServletRequest request) {
		
		Map<String, Object> listEquipements = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_EQUIPMENT + projectId;
        return RestAPIHelper.get(getDataURL, listEquipements);
    }
	@RequestMapping(value="/list/timesheet/{projectId}/{m}/{y}", method = {RequestMethod.GET})
	@ResponseBody
	public Object getTimeSheetInProject(@PathVariable("projectId") int projectId
										, @PathVariable("m") int m
										, @PathVariable("y") int y, Model model, HttpServletRequest request) {
		
		Map<String, Object> listTimeSheet = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.PM_TIMESHEET + projectId + "/" + m + "/" + y;
        return RestAPIHelper.get(getDataURL, listTimeSheet);
    }
}
