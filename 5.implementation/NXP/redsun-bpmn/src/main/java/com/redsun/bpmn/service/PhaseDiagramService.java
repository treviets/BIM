package com.redsun.bpmn.service;

import com.redsun.bpmn.entities.PhaseDiagram;
import com.redsun.bpmn.entities.Result;

/**
 * Phase Service interface
 */
public interface PhaseDiagramService {

	// Get all document of project
	Result getAllPhaseDiagram();
	
	// Get all document of step
	Result getPhaseDiagramById(int projectId);

	// Update document of step
	Result updatePhaseDiagram(final PhaseDiagram phaseDiagram);
	
	// Create diagram
	Result createPhaseDiagram(final PhaseDiagram phaseDiagram);
	
}
