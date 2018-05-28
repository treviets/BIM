package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.PlanningElement;

public class TaskCostPerMonthMapper implements RowMapper<PlanningElement> {
	@Autowired
	private PlanningElement planningElement;

	public PlanningElement mapRow(ResultSet rs, int rowNum) throws SQLException {

		planningElement = new PlanningElement();
		planningElement.setStartDate(rs.getDate("start_date"));
		planningElement.setEndDate(rs.getDate("end_date"));
		planningElement.setEstimateTime(rs.getInt("estimate_time"));
		planningElement.setCompleted(rs.getInt("completed"));
		//planningElement.setYearly(rs.getInt("yearly"));
		planningElement.setPlanningCost(rs.getInt("planning_cost"));
		//planningElement.setTotalActuallyCost(rs.getInt("total_actually_cost"));
		return planningElement;
	}
}
