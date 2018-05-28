package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.PlanningElement;

public interface PMReportDao {
	List<PlanningElement> getTaskParent(int projectId, String fromDate, String toDate);
	List<PlanningElement> getSubTask(String wbs);
	List<PlanningElement> calTaskCostByDuration(int projectId, String fromDate, String toDate);
	List<PlanningElement> calBCWP(int projectId, int y);
	List<PlanningElement> getMaterialInProject(int projectId);
	List<PlanningElement> getMaterialByDuration(int projectId, String fromDate, String toDate);
	List<PlanningElement> getMaterialParentByDuration(int projectId, String fromDate, String toDate);
	List<PlanningElement> getLaborInProject(int projectId);
	List<PlanningElement> getEquipmentInProject(int projectId);
	List<PlanningElement> getTimeSheet(int projectId, int month, int year);
}
