package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.TaskResources;
import com.redsun.service.entities.Result;

public interface TaskResourcesService {

	// get by task
	Result getByTask(int taskId, int clientId);

	// get by project
	Result getTaskResourceOneProject(int projectId, int clientId);

	// insert
	Result insert(List<TaskResources> listTaskResources);

	// update
	Result update(TaskResources taskResources);

	// delete
	Result delete(int taskResourcesId);
}
