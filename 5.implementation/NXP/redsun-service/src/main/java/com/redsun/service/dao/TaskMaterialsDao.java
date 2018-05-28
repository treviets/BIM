package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.TaskMaterials;

public interface TaskMaterialsDao {
	// select all
	List<TaskMaterials> getByTask(int taskId, int projectId, int clientId);

	// insert
	int insert(List<TaskMaterials> listTaskMaterials);

	// update actual
	int update(TaskMaterials taskMaterial);

	// update when planning
	int updateTaskMaterials(TaskMaterials taskMaterials);

	// delete
	int delete(int taskMaterialsId);
}