package com.redsun.whm.dao;


import java.util.List;

import com.redsun.whm.entities.DocumentStep;

/**
 * PhaseDocument DAO interface
 */
public interface DocumentStepDao {
	
	// Get all document of project
	List<DocumentStep> getAllDocumentForStep(int projectId, String stepId);
	
	// Update document of step
	int update(final DocumentStep phaseDocument);

	// Insert document into database
	int insert(final DocumentStep documentStep);
	
	// Delete document into database
	int delete(final DocumentStep documentStep);
		
}
