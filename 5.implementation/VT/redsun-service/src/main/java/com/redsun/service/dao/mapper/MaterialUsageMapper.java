package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.PlanningElement;

@Component
public class MaterialUsageMapper implements RowMapper<PlanningElement> {
	@Autowired
	private PlanningElement planningElement;

	public PlanningElement mapRow(ResultSet rs, int rowNum) throws SQLException {
		planningElement = new PlanningElement();
		planningElement.setCode(rs.getString("code"));
		planningElement.setResourceName(rs.getString("material_name"));
		planningElement.setUnitName(rs.getString("unit_name"));
		planningElement.setTaskName(rs.getString("task_name"));
		planningElement.setStartDate(rs.getDate("start_date"));
		planningElement.setEndDate(rs.getDate("end_date"));
		planningElement.setParent_id(rs.getString("parent_id"));
		planningElement.setEstimateQuantity(rs.getInt("estimate_quantity"));
		planningElement.setActuallyQuantity(rs.getInt("actual_quantity"));
		planningElement.setSV(rs.getDouble("net_price"));
		planningElement.setCV(rs.getDouble("price"));
		return planningElement;
	}

}
