package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.service.PlanningElementService;

@RestController
@RequestMapping(value = "planningelement")
public class PlanningElementController { 
	
	
	@Autowired
    PlanningElementService planningElementService;
	 
    @RequestMapping(value="/list/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
    public Object getResourceCost(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model) {
    	
        return planningElementService.listResourceCost(projectId, startDate, endDate);
    }
    @RequestMapping(value="/list/taskcost/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
    public Object getTaskCost(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model) {
    	
        return planningElementService.listTaskCostByDuration(projectId, startDate, endDate);
    }
    @RequestMapping(value="/list/costexpenses/{projectId}/{m}/{y}", method = {RequestMethod.GET})
    public Object getCostExpenses(@PathVariable("projectId") int projectId
    						, @PathVariable("m") int m
    						, @PathVariable("y") int y, Model model) {
    	
        return planningElementService.listCostPerMonth(projectId, m, y);
    }
    @RequestMapping(value="/list/pcostexpenses/{projectId}/{m}/{y}", method = {RequestMethod.GET})
    public Object getPCostExpenses(@PathVariable("projectId") int projectId
    						, @PathVariable("m") int m
    						, @PathVariable("y") int y, Model model) {
    	
        return planningElementService.listPCostPerMonth(projectId, m, y);
    }
    @RequestMapping(value="/list/materialusage/{projectId}", method = {RequestMethod.GET})
    public Object getMaterialUsage(@PathVariable("projectId") int projectId, Model model) {
    	
        return planningElementService.listMaterialUsage(projectId);
    }
    @RequestMapping(value="/list/materialprovied/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
    public Object getMaterialProvidedByDuration(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model) {
    	
        return planningElementService.getMaterialByDuration(projectId, startDate, endDate);
    }
    @RequestMapping(value="/list/labor/{projectId}", method = {RequestMethod.GET})
    public Object getLaborInProject(@PathVariable("projectId") int projectId, Model model) {
    	
        return planningElementService.getLaborInProject(projectId);
    }
    @RequestMapping(value="/list/equipment/{projectId}", method = {RequestMethod.GET})
    public Object getEquipmentInProject(@PathVariable("projectId") int projectId, Model model) {
    	
        return planningElementService.getEquipmentInProject(projectId);
    }
    @RequestMapping(value="/list/timesheet/{projectId}/{m}/{y}", method = {RequestMethod.GET})
    public Object getTimeSheetInProject(@PathVariable("projectId") int projectId
    									, @PathVariable("m") int m
    									, @PathVariable("y") int y, Model model) {
    	
        return planningElementService.getTimeSheet(projectId, m, y);
    }
   }
