package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProjectMaterialsDao;
import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ProjectMaterialsService;

@Service
public class ProjectMaterialsServiceImpl implements ProjectMaterialsService {

	@Autowired
	ProjectMaterialsDao projectMaterialsDao;

	@Override
	public Result getByProjectId(int projectId, int clientId) {
		final List<ProjectMaterials> data = projectMaterialsDao.getByProjectId(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(List<ProjectMaterials> listProjectMaterials) {
		final int data = projectMaterialsDao.insert(listProjectMaterials);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result filterProjectMaterials(int projectId, int taskId, int clientId) {
		final List<ProjectMaterials> data = projectMaterialsDao.filterProjectMaterials(projectId, taskId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result updatePMaterial(ProjectMaterials projectMaterials) {
		final int data = projectMaterialsDao.updatePMaterial(projectMaterials);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectmaterials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int projectMaterialId) {
		final int data = projectMaterialsDao.delete(projectMaterialId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectmaterials", data);
		// Return.
		return new Result(result, true);
	}

}
