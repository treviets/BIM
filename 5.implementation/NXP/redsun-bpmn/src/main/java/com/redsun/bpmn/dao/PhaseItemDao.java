package com.redsun.bpmn.dao;

import java.util.List;
import java.util.Map;

import com.redsun.bpmn.entities.PhaseItem;

/**
 * Phase DAO interface
 */
public interface PhaseItemDao {
	
	// Get all phases(test data)
	List<PhaseItem> getAllPhaseItem();
	
	List<PhaseItem> getAllPhaseItemById(int projectId);

	// Update phase directly into database
	Map<String, Object> update(final PhaseItem phaseItem,  String currentStepId, String actionType);
	
	// Create phase
	int create(final PhaseItem phaseItem);
}
