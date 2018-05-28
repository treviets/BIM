package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProjectResourcesDao;
import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ProjectResourcesService;

@Service
public class ProjectResourcesServiceImpl implements ProjectResourcesService {

	@Autowired
	ProjectResourcesDao projectResourcesDao;

	@Override
	public Result insert(List<ProjectResources> projectResource) {
		final int data = projectResourcesDao.insert(projectResource);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectresources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getByProject(int projectId, int clientId) {
		final List<ProjectResources> data = projectResourcesDao.getByProject(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectresources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result filterProjectResources(int projectId, int taskId, int clientId) {
		final List<ProjectResources> data = projectResourcesDao.filterProjectResources(projectId, taskId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectresources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getAllProjectResources(int clientId) {
		final List<ProjectResources> data = projectResourcesDao.getAllProjectResources(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectresources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(ProjectResources projectResources) {
		final int data = projectResourcesDao.update(projectResources);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectresources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int projectResourcesId) {
		final int data = projectResourcesDao.delete(projectResourcesId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectresources", data);
		// Return.
		return new Result(result, true);
	}
}
