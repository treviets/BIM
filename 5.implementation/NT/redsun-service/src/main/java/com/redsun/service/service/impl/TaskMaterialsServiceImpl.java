package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TaskMaterialsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskMaterials;
import com.redsun.service.service.TaskMaterialsService;

@Service
public class TaskMaterialsServiceImpl implements TaskMaterialsService {

	@Autowired
	TaskMaterialsDao taskMaterialsDao;

	@Override
	public Result getByTask(int taskId, int projectId, int clientId) {
		final List<TaskMaterials> data = taskMaterialsDao.getByTask(taskId, projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(List<TaskMaterials> listTaskMaterials) {
		final int data = taskMaterialsDao.insert(listTaskMaterials);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(TaskMaterials taskMaterial) {
		final int data = taskMaterialsDao.update(taskMaterial);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result updateTaskMaterials(TaskMaterials taskMaterials) {
		final int data = taskMaterialsDao.updateTaskMaterials(taskMaterials);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int taskMaterialsId) {
		final int data = taskMaterialsDao.delete(taskMaterialsId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskmaterials", data);
		// Return.
		return new Result(result, true);
	}
}
