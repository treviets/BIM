package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.BaseLines;
import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Task;
import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.entities.TaskMaterials;
import com.redsun.service.entities.TaskResources;

public interface ProjectBaseLineDao {
	List<BaseLines> getBaseLineByType(int projectId, int type, int clientId);
	List<ProjectResources> getLaborBaseline(int projectId, int clientId, int baselineId);
	List<ProjectResources> getProjectResourceByProjectId(int projectId, int clientId);
	List<ProjectMaterials> getProjectMaterialByProjectId(int projectId, int clientId);
	List<ProjectEquipments> getProjectEquipmentByProjectId(int projectId, int clientId);
	List<Task> getProjectTaskByProjectId(int projectId, int clientId);
	List<TaskResources> getProjectTaskRSByProjectId(int projectId, int clientId);
	List<TaskMaterials> getProjectTaskMaterialByProjectId(int projectId, int clientId);
	List<TaskEquipments> getProjectTaskEquipmentByProjectId(int projectId, int clientId);
	void insertLaborBaseline(List<ProjectResources> projectResources);
	void insertEquipmentBaseline(List<ProjectEquipments> projectEquipments);
	void insertMaterialBaseline(List<ProjectMaterials> projectMaterials);
	void insertTaskBaseline(List<Task> tasks);
	void insertTaskResourceBaseline(List<TaskResources> taskRS);
	void insertTaskMaterialBaseline(List<TaskMaterials> taskMaterials);
	void insertTaskEquipmentBaseline(List<TaskEquipments> taskEquipments);
	int inserBaseLine(BaseLines baseline);
}
