package com.redsun.bpmn.dao;


import com.redsun.bpmn.entities.PhaseStep;

/**
 * Phase DAO interface
 */
public interface PhaseStepDao {
	
	// Update phase directly into database
	int create(final PhaseStep phaseStep);
	
	
}
