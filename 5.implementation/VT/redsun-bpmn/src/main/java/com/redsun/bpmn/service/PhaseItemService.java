package com.redsun.bpmn.service;

import com.redsun.bpmn.entities.PhaseItem;
import com.redsun.bpmn.entities.Result;

/**
 * Phase Service interface
 */
public interface PhaseItemService {

	// Get all document of project
	Result getAllPhaseItem();
	
	// Get all document of step
	Result getAllPhaseItemById(int projectId);

	// Update document of step
	Result update(final PhaseItem phaseItem, String currentStepId, String actionType) ;
}
