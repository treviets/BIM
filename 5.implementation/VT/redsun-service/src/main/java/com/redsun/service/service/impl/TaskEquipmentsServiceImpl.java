package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TaskEquipmentsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.service.TaskEquipmentsService;

@Service
public class TaskEquipmentsServiceImpl implements TaskEquipmentsService {

	@Autowired
	TaskEquipmentsDao taskEquipmentsDao;

	@Override
	public Result getByTask(int taskId, int projectId, int clientId) {
		final List<TaskEquipments> data = taskEquipmentsDao.getByTask(taskId, projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(List<TaskEquipments> listTaskEquipments) {
		final int data = taskEquipmentsDao.insert(listTaskEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result updateTaskEquipments(TaskEquipments taskEquipments) {
		final int data = taskEquipmentsDao.updateTaskEquipments(taskEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int taskEquipmentsId) {
		final int data = taskEquipmentsDao.delete(taskEquipmentsId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskequipments", data);
		// Return.
		return new Result(result, true);
	}
}
