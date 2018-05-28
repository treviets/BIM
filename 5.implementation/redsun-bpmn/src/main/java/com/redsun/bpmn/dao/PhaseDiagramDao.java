package com.redsun.bpmn.dao;

import java.util.List;
import java.util.Map;

import com.redsun.bpmn.entities.PhaseDiagram;

/**
 * Phase DAO interface
 */
public interface PhaseDiagramDao {
	
	// Get all phases(test data)
	List<PhaseDiagram> getPhaseDiagram();
	
	List<PhaseDiagram> getStandardDiagram();
	
	List<PhaseDiagram> getPhaseDiagramById(int projectId);

	// Update phase directly into database
	int updatePhaseDiagram(final PhaseDiagram phaseItem);
	
	Map<String, Object> createPhaseDiagram(final PhaseDiagram phaseDiagram);
	
	int clonePhaseDiagram(PhaseDiagram phaseDiagram);
}
