package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.PlanningElement;

public class ResourceCostMapper implements RowMapper<PlanningElement> {
	@Autowired
	private PlanningElement planningElement;

	public PlanningElement mapRow(ResultSet rs, int rowNum) throws SQLException {

		planningElement = new PlanningElement();
		//planningElement.setId(rs.getInt("id"));
		//planningElement.setActuallyTime(rs.getInt("actually_time"));
		//planningElement.setActuallyCost(rs.getInt("actually_cost"));
		planningElement.setCode(rs.getString("code"));
		planningElement.setResourceName(rs.getString("resource_name"));
		planningElement.setStartDate(rs.getDate("start_date"));
		planningElement.setSalary(rs.getInt("salary"));
		planningElement.setTotalActuallyTime(rs.getInt("total_actually_time"));
		planningElement.setTotalPlanningCost(rs.getInt("total_planning_cost"));
		planningElement.setTotalActuallyCost(rs.getInt("total_actually_cost"));
		//planningElement.setEquipmentName(rs.getString("equipment_name"));
		return planningElement;
	}
}
