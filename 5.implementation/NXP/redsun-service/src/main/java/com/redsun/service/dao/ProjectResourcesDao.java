package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.ProjectResources;

public interface ProjectResourcesDao {
	// insert
	int insert(List<ProjectResources> projectResource);

	// get by projectId
	List<ProjectResources> getByProject(int projectId, int clientId);

	// filter by resources were exist in project_resources
	List<ProjectResources> filterProjectResources(int projectId, int taskId, int clientId);

	// Get by projectId and resourceId.
	List<ProjectResources> getByProjectIdAndResourceId(int projectId, int resourceId, int clientId);

	// get all
	List<ProjectResources> getAllProjectResources(int clientId);

	// update
	int update(ProjectResources projectResource);

	// delete
	int delete(int projectResourceId);

}
