package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.PlanningElement;

public class TaskCostMapper implements RowMapper<PlanningElement> {
	@Autowired
	private PlanningElement planningElement;

	public PlanningElement mapRow(ResultSet rs, int rowNum) throws SQLException {

		planningElement = new PlanningElement();
		//planningElement.setId(rs.getInt("id"));
		planningElement.setTaskId(rs.getInt("id"));
		planningElement.setTaskName(rs.getString("task_name"));
		planningElement.setWbs(rs.getString("wbs"));
		planningElement.setParent_id(rs.getString("parent_id"));
		planningElement.setTotalPlanningCost(rs.getInt("total_planning_cost"));
		planningElement.setTotalActuallyCost(rs.getInt("total_actually_cost"));
		return planningElement;
	}
}
