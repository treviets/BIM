package com.redsun.bpmn.service;

import com.redsun.bpmn.entities.PhaseDocument;
import com.redsun.bpmn.entities.Result;

/**
 * Phase Service interface
 */
public interface PhaseDocumentService {
	
	// Get all document of project
	Result getAllPhaseDocument(int projectId);
	
	// Get all document of step
	Result getAllPhaseDocumentForStep(int projectId, int activeStep, int activeSubStep);
	
	// Update document of step
	Result updatePhaseForStep(final PhaseDocument phaseDocument) ;
	
	// Create document of step
	Result insertDocumentForStep(final PhaseDocument phaseDocument) ;
	
	// Create document of step
	Result deleteDocumentForStep(final PhaseDocument phaseDocument) ;
		
}
