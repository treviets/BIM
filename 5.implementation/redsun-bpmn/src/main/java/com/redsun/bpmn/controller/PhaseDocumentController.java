package com.redsun.bpmn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.bpmn.entities.PhaseDocument;
import com.redsun.bpmn.service.PhaseDocumentService;


/**
 * PhaseDocument Controller
 */
@RestController
@RequestMapping("phasedocument")
public class PhaseDocumentController {
	
	// Service.
	@Autowired
	PhaseDocumentService phaseDocumentService;
	
	// Get all document of project
	@RequestMapping(value = "/list/project/{projectId}", method = { RequestMethod.GET })
	public Object getAllDocumentForProject(@PathVariable("projectId") Integer projectId) {
		return phaseDocumentService.getAllPhaseDocument(projectId);
	}
	
	// Get all document of project
	@RequestMapping(value = "/list/step/{projectId}/{activeStep}/{activeSubStep}", method = { RequestMethod.GET })
	public Object getAllDocumentForStep(@PathVariable("projectId") Integer projectId, @PathVariable("activeStep") Integer activeStep, @PathVariable("activeSubStep") Integer activeSubStep) {
		return phaseDocumentService.getAllPhaseDocumentForStep(projectId, activeStep, activeSubStep);
	}
	
	// Insert document to database
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody PhaseDocument documents) {
		return phaseDocumentService.insertDocumentForStep(documents);
	}

	// Insert document to database
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Object delete(@Validated @RequestBody PhaseDocument documents) {
		return phaseDocumentService.deleteDocumentForStep(documents);
	}
	
}
