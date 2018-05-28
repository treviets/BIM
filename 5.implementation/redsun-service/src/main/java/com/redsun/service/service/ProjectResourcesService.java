package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Result;

public interface ProjectResourcesService {

	// insert
	Result insert(List<ProjectResources> projectResource);

	// get by projectId
	Result getByProject(int projectId, int clientId);

	// filter by resources were exists in project_resources
	Result filterProjectResources(int projectId, int taskId, int clientId);

	// get all
	Result getAllProjectResources(int clientId);

	// update
	Result update(ProjectResources projectResources);

	// delete
	Result delete(int projectResourcesId);

}
