package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.PlanningElement;
import com.redsun.service.entities.TaskMaterials;

public interface PlanningElementDao {

	// get all projects
	List<PlanningElement> listResourceCost(int projectId, String fromDate, String toDate);
	List<PlanningElement> listACostPerMonth(int projectId, int m, int y);
	List<PlanningElement> listPCostPerMonth(int projectId, int m, int y);
	List<PlanningElement> listPCostPerMonth(int projectId, int y);
	List<PlanningElement> listMaterialUsage(int projectId);
}
