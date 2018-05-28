package com.redsun.bpmn.dao;


import java.util.List;

import com.redsun.bpmn.entities.ResourceStep;

/**
 * PhaseDocument DAO interface
 */
public interface ResourceStepDao {
	
	// Get all resource of project
	List<ResourceStep> getAllResourceForStep(int projectId, String stepId);
	
	// Update resource of step
	int update(final ResourceStep resourceStep);

	// Insert resource into database
	int insert(final ResourceStep resourceStep);

	// Delete resource into database
	public int delete(int projectId, String stepId, String code);
	
}
