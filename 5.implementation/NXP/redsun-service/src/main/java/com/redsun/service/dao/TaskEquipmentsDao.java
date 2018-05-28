package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.TaskEquipments;

public interface TaskEquipmentsDao {
	// select all
	List<TaskEquipments> getByTask(int taskId, int projectId, int clientId);

	// insert
	int insert(List<TaskEquipments> listTaskEquipments);

	// update when planning
	int updateTaskEquipments(TaskEquipments taskEquipments);

	// delete
	int delete(int taskEquipmentsId);
}