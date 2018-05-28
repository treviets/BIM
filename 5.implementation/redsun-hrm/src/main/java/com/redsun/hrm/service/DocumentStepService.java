package com.redsun.hrm.service;

import com.redsun.hrm.entities.DocumentStep;
import com.redsun.hrm.entities.Result;

/**
 * DocumentStep Service interface
 */
public interface DocumentStepService {
	
	// Get all document of project
	Result getAllDocumentStep(int projectId, String stepId);
	
	// Update document of step
	Result updateDocumentForStep(final DocumentStep documentStep) ;
	
	// Create document of step
	Result insertDocumentForStep(final DocumentStep documentStep) ;
	
	// Create document of step
	Result deleteDocumentForStep(final DocumentStep documentStep) ;
}
