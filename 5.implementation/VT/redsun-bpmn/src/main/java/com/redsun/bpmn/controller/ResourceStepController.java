package com.redsun.bpmn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.bpmn.entities.ResourceStep;
import com.redsun.bpmn.service.ResourceStepService;


/**
 * Resource Step Controller
 */
@RestController
@RequestMapping("resourcestep")
public class ResourceStepController {
	// Service.
	@Autowired
	ResourceStepService resourceStepService;
		
	// Get all resource of project
	@RequestMapping(value = "/list/{projectId}/{stepId}", method = { RequestMethod.GET })
	public Object getAllResource(@PathVariable("projectId") Integer projectId, @PathVariable("stepId") String stepId) {
		return resourceStepService.getAllResourceStep(projectId, stepId);
	}
	
	// Insert resource to database
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object insertResource(@Validated @RequestBody ResourceStep resourceStep) {
		return resourceStepService.insertResourceForStep(resourceStep);
	}
	
	// Update resource to database
	@RequestMapping(value = "/update", method = { RequestMethod.PUT })
	public Object updateResource(@Validated @RequestBody ResourceStep resourceStep) {
		return resourceStepService.updateResourceForStep(resourceStep);
	}
	

	// Delete resource to database
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Object deleteResource(@Validated @RequestBody ResourceStep resourceStep) {		
		int projectId = resourceStep.getProjectId();
		String stepId = resourceStep.getStepId();
		String code = resourceStep.getResourceCode();
		
		return resourceStepService.deleteResourceForStep(projectId, stepId, code);
	}
}
