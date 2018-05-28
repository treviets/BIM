package com.redsun.service.service;

import com.redsun.service.entities.Result;

public interface PlanningElementService {
	
	//on page
	 Result listResourceCost(int projectId, String fromDate, String toDate);
	 Result listTaskCostByDuration(int projectId, String fromDate, String toDate);
	 Result listCostPerMonth(int projectId, int m, int y);
	 Result listPCostPerMonth(int projectId, int m, int y);
	 Result listMaterialUsage(int projectId);
	 Result getMaterialByDuration(int projectId, String fromDate, String toDate);
	 Result getLaborInProject(int projectId);
	 Result getEquipmentInProject(int projectId);
	 Result getTimeSheet(int projectId, int m, int y);
}
