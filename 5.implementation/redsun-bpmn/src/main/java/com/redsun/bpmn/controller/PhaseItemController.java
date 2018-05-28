package com.redsun.bpmn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.bpmn.entities.PhaseItem;
import com.redsun.bpmn.service.PhaseItemService;


/**
 * Phase Controller
 */
@RestController
@RequestMapping("phaseitem")

public class PhaseItemController {
	
	// Service.
	@Autowired
	PhaseItemService phaseItemService;
	
	// Get all	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public Object getAllPhases() {
		return phaseItemService.getAllPhaseItem();
	}
	
	// Get all	
	@RequestMapping(value = "/list/{id}", method = { RequestMethod.GET })
	public Object getAllPhasesById(@PathVariable("id") Integer id) {
		return phaseItemService.getAllPhaseItemById(id);
	}

	// Update.
	@RequestMapping(value = "/update/{id}/{currentStepId}/{actionType}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @PathVariable("currentStepId") String currentStepId, @PathVariable("actionType") String actionType, @RequestBody Map<String, Object> phaseItem){
		HashMap<String, Object> hsPhase = new HashMap<String, Object>(phaseItem);
		
		int idPhase = Integer.parseInt(String.valueOf(hsPhase.get("id")));
		int idProjectPhase = Integer.parseInt(String.valueOf(hsPhase.get("idProject")));
		String activeStepPhase = String.valueOf(hsPhase.get("activeStep"));
		String contain = String.valueOf(hsPhase.get("contain"));
		
		PhaseItem objUpdate = new PhaseItem();
		objUpdate.setId(idPhase);
		objUpdate.setIdProject(idProjectPhase);
		objUpdate.setActiveStep(activeStepPhase);
		objUpdate.setContain(contain);
		
		return phaseItemService.update(objUpdate, currentStepId, actionType);
	}
	
}
