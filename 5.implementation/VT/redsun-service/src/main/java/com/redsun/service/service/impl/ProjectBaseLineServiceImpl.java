package com.redsun.service.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProjectBaseLineDao;
import com.redsun.service.entities.BaseLines;
import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Task;
import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.entities.TaskMaterials;
import com.redsun.service.entities.TaskResources;
import com.redsun.service.service.ProjectBaseLineService;

@Service
public class ProjectBaseLineServiceImpl implements ProjectBaseLineService {

	@Autowired
	ProjectBaseLineDao projectBaseLineDao;
	
	@Override
	public Result insertLaborBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<ProjectResources> projectResources = projectBaseLineDao.getProjectResourceByProjectId(projectId, clientId);
		for(int i=0; i<projectResources.size(); i++)
			projectResources.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertLaborBaseline(projectResources);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result insertMaterialBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<ProjectMaterials> projectMaterials = projectBaseLineDao.getProjectMaterialByProjectId(projectId, clientId);
		for(int i=0; i<projectMaterials.size(); i++)
			projectMaterials.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertMaterialBaseline(projectMaterials);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result insertEquipmentBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<ProjectEquipments> projectEquipments = projectBaseLineDao.getProjectEquipmentByProjectId(projectId, clientId);
		for(int i=0; i<projectEquipments.size(); i++)
			projectEquipments.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertEquipmentBaseline(projectEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result insertTaskBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<Task> tasks = projectBaseLineDao.getProjectTaskByProjectId(projectId, clientId);
		for(int i=0; i<tasks.size(); i++)
			tasks.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertTaskBaseline(tasks);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result insertTaskResourceBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<TaskResources> taskRS = projectBaseLineDao.getProjectTaskRSByProjectId(projectId, clientId);
		for(int i=0; i<taskRS.size(); i++)
			taskRS.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertTaskResourceBaseline(taskRS);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result insertTaskMaterialBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<TaskMaterials> taskMaterials = projectBaseLineDao.getProjectTaskMaterialByProjectId(projectId, clientId);
		for(int i=0; i<taskMaterials.size(); i++)
			taskMaterials.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertTaskMaterialBaseline(taskMaterials);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result insertTaskEquipmentBaseline(int projectId, int clientId, BaseLines baseline) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		baseline.setBaselineDate(currentDate);
		baseline.setClientId(clientId);
		int baselineId = projectBaseLineDao.inserBaseLine(baseline);
		final List<TaskEquipments> taskEquipments = projectBaseLineDao.getProjectTaskEquipmentByProjectId(projectId, clientId);
		for(int i=0; i<taskEquipments.size(); i++)
			taskEquipments.get(i).setBaselineId(baselineId);
		projectBaseLineDao.insertTaskEquipmentBaseline(taskEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", 1);
		return new Result(result, true);
	}
	@Override
	public Result getLaborBaseline(int projectId, int clientId, int baselineId) {
		// TODO Auto-generated method stub
		final List<ProjectResources> projectRessources = projectBaseLineDao.getLaborBaseline(projectId, clientId, baselineId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectRessources", projectRessources);
		return new Result(result, true);
	}

	
}
