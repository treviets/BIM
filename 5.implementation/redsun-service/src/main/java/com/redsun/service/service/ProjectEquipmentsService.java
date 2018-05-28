package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.Result;

public interface ProjectEquipmentsService {

	// get by project id
	Result getByProjectId(int projectId, int clientId);

	// insert
	Result insert(List<ProjectEquipments> listProjectEquipments);

	// filter by equipments were exists in project_equipments
	Result filterProjectEquipments(int projectId, int taskId, int clientId);

	// update
	Result update(ProjectEquipments projectEquipments);

	// delete
	Result delete(int projectEquipmentsId);
}
