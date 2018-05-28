package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.PlanningElement;

public class CostExpensesPerMonthMapper implements RowMapper<PlanningElement> {
	@Autowired
	private PlanningElement planningElement;

	public PlanningElement mapRow(ResultSet rs, int rowNum) throws SQLException {

		planningElement = new PlanningElement();
		planningElement.setProjectId(rs.getInt("project_id"));
		planningElement.setTaskId(rs.getInt("task_id"));
		//planningElement.setStartDate(rs.getDate("start_date"));
		//planningElement.setEndDate(rs.getDate("end_date"));
		planningElement.setMonthly(rs.getInt("monthly"));
		planningElement.setYearly(rs.getInt("yearly"));
		planningElement.setTotalPlanningCost(rs.getInt("total_planning_cost"));
		planningElement.setTotalActuallyCost(rs.getInt("total_actually_cost"));
		return planningElement;
	}
}
