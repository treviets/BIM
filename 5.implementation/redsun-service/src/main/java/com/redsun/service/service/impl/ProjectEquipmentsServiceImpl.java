package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProjectEquipmentsDao;
import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ProjectEquipmentsService;

@Service
public class ProjectEquipmentsServiceImpl implements ProjectEquipmentsService {

	@Autowired
	ProjectEquipmentsDao projectEquipmentsDao;

	@Override
	public Result getByProjectId(int projectId, int clientId) {
		final List<ProjectEquipments> data = projectEquipmentsDao.getByProjectId(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(List<ProjectEquipments> listProjectEquipments) {
		final int data = projectEquipmentsDao.insert(listProjectEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result filterProjectEquipments(int projectId, int taskId, int clientId) {
		final List<ProjectEquipments> data = projectEquipmentsDao.filterProjectEquipments(projectId, taskId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(ProjectEquipments projectEquipments) {
		final int data = projectEquipmentsDao.update(projectEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectequipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int projectEquipmentsId) {
		final int data = projectEquipmentsDao.delete(projectEquipmentsId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectequipments", data);
		// Return.
		return new Result(result, true);
	}
}
