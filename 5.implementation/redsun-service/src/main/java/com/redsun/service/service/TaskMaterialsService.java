package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskMaterials;

public interface TaskMaterialsService {

	// get by task
	Result getByTask(int task, int projectId, int clientId);

	// insert
	Result insert(List<TaskMaterials> listTaskMaterials);

	// update actual
	Result update(TaskMaterials taskMaterial);

	// update when planning
	Result updateTaskMaterials(TaskMaterials taskMaterials);

	// delete
	Result delete(int taskMaterialsId);
}