package com.redsun.bpmn.service;

import com.redsun.bpmn.entities.ResourceStep;
import com.redsun.bpmn.entities.Result;

/**
 * ResourceStep Service interface
 */
public interface ResourceStepService {
	
	// Get all resource of project
	Result getAllResourceStep(int projectId, String stepId);
	
	// Update resource of step
	Result updateResourceForStep(final ResourceStep resourceStep) ;
	
	// Create resource of step
	Result insertResourceForStep(final ResourceStep resourceStep) ;
		
	Result deleteResourceForStep(int projectId, String stepId, String code);
}
