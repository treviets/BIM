package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.TaskResources;

public interface TaskResourcesDao {

	// get by task
	List<TaskResources> getByTask(int taskId, int clientId);

	// get by project
	List<TaskResources> getTaskResourceOneProject(int projectId, int clientId);

	// insert
	int insert(List<TaskResources> listTaskResources);

	// update
	int update(TaskResources taskResources);

	// delete
	int delete(int taskResourcesId);
}