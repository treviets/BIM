package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.ProjectEquipments;

public interface ProjectEquipmentsDao {
	// select all
	List<ProjectEquipments> getByProjectId(int projectId, int clientId);

	// insert
	int insert(List<ProjectEquipments> listProjectEquipments);

	// filter by equipments were exist in project_equipments
	List<ProjectEquipments> filterProjectEquipments(int projectId, int taskId, int clientId);

	// Get by projectId and materialId.
	List<ProjectEquipments> getByProjectIdAndEquipmentId(int projectId, int equipmentId, int clientId);

	// insert
	int update(ProjectEquipments projectEquipments);

	// delete
	int delete(int projectEquipmentsId);
}