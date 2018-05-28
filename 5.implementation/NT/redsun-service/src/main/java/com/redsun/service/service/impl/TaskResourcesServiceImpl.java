package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TaskResourcesDao;
import com.redsun.service.entities.TaskResources;
import com.redsun.service.entities.Result;
import com.redsun.service.service.TaskResourcesService;

@Service
public class TaskResourcesServiceImpl implements TaskResourcesService {

	@Autowired
	TaskResourcesDao taskResourcesDao;

	@Override
	public Result getByTask(int taskId, int clientId) {
		final List<TaskResources> data = taskResourcesDao.getByTask(taskId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("assignments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(List<TaskResources> listTaskResources) {
		final int data = taskResourcesDao.insert(listTaskResources);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("assignments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(TaskResources taskResources) {
		final int data = taskResourcesDao.update(taskResources);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("assignments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getTaskResourceOneProject(int projectId, int clientId) {
		final List<TaskResources> data = taskResourcesDao.getTaskResourceOneProject(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskresources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int taskResourcesId) {
		final int data = taskResourcesDao.delete(taskResourcesId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("assignments", data);
		// Return.
		return new Result(result, true);
	}

}
