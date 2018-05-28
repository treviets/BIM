package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskEquipments;

public interface TaskEquipmentsService {

	// get by task
	Result getByTask(int task, int projectId, int clientId);

	// insert
	Result insert(List<TaskEquipments> listTaskEquipments);

	// update when planning
	Result updateTaskEquipments(TaskEquipments taskEquipments);

	// delete
	Result delete(int taskEquipmentsId);
}
