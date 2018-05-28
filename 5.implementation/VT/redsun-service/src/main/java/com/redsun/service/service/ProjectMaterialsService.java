package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.Result;

public interface ProjectMaterialsService {

	// get by project id
	Result getByProjectId(int projectId, int clientId);

	// insert
	Result insert(List<ProjectMaterials> listProjectMaterials);

	// filter by materials were exists in project_materials
	Result filterProjectMaterials(int projectId, int taskId, int clientId);

	// update
	Result updatePMaterial(ProjectMaterials projectMaterials);

	// delete
	Result delete(int projectMaterialId);

}
