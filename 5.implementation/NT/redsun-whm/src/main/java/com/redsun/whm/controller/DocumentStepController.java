package com.redsun.whm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.whm.entities.DocumentStep;
import com.redsun.whm.service.DocumentStepService;


/**
 * Document Step Controller
 */
@RestController
@RequestMapping("documentstep")
public class DocumentStepController {
	// Service.
	@Autowired
	DocumentStepService documentStepService;
		
	// Get all document of project
	@RequestMapping(value = "/list/{projectId}/{stepId}", method = { RequestMethod.GET })
	public Object getAllDocumentForStep(@PathVariable("projectId") Integer projectId, @PathVariable("stepId") String stepId) {
		return documentStepService.getAllDocumentStep(projectId, stepId);
	}
	
	// Insert document to database
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody DocumentStep documentStep) {
		return documentStepService.insertDocumentForStep(documentStep);
	}

	// Delete document to database
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Object deleteResource(@Validated @RequestBody DocumentStep documentStep) {		
		return documentStepService.deleteDocumentForStep(documentStep);
	}

	
}
