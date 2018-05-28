package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.ProjectMaterials;

public interface ProjectMaterialsDao {
	// select all
	List<ProjectMaterials> getByProjectId(int projectId, int clientId);

	// insert
	int insert(List<ProjectMaterials> listProjectMaterials);

	// update when key actual in task
	int update(ProjectMaterials projectMaterials);

	// filter by materials were exist in project_materials
	List<ProjectMaterials> filterProjectMaterials(int projectId, int taskId, int clientId);

	// Get by projectId and materialId.
	List<ProjectMaterials> getByProjectIdAndMaterialId(int projectId, int materialId, int clientId);

	// update
	int updatePMaterial(ProjectMaterials projectMaterials);

	// delete
	int delete(int projectMaterialId);

}