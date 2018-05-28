package com.redsun.bpmn.dao;

import java.util.List;
import com.redsun.bpmn.entities.PhaseDocument;

/**
 * PhaseDocument DAO interface
 */
public interface PhaseDocumentDao {
	
	// Get all document of project
	List<PhaseDocument> getAllPhaseDocument(int projectId);
	
	// Get all document of step
	List<PhaseDocument> getAllPhaseDocumentForStep(int projectId, int activeStep, int activeSubStep);
	
	// Update document of step
	int update(final PhaseDocument phaseDocument);

	// Insert document into database
	int insert(final PhaseDocument phaseDocument);
	
	// Delete document into database
	int delete(final PhaseDocument phaseDocument);
}
