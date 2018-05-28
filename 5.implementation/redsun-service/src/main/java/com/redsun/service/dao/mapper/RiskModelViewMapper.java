package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Risk;

public class RiskModelViewMapper implements RowMapper<Risk> {
	@Autowired
	private Risk  riskmodelview;

	@Override
	public Risk mapRow(ResultSet rs, int rowNum) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		riskmodelview = new Risk();
		riskmodelview.setId(rs.getInt("id"));
		riskmodelview.setProjectId(rs.getInt("project_id"));
		riskmodelview.setProjectName(rs.getString("project_name"));
		riskmodelview.setName(rs.getString("name"));
		riskmodelview.setDescription(rs.getString("description"));//
		riskmodelview.setRiskTypeId(rs.getInt("type_id"));
		riskmodelview.setTypeName(rs.getString("type_name"));
		riskmodelview.setCause(rs.getString("cause"));//
		riskmodelview.setImpact(rs.getString("impact"));//
		riskmodelview.setSeverityId(rs.getInt("severity_id"));
		riskmodelview.setSeverityName(rs.getString("severity_name"));
		riskmodelview.setLikelihoodId(rs.getInt("likelihood_id"));
		riskmodelview.setLikelihoodName(rs.getString("likelihood_name"));
		riskmodelview.setCriticalityId(rs.getInt("criticality_id"));
		riskmodelview.setCriticalityName(rs.getString("criticality_name"));
		riskmodelview.setStatusId(rs.getInt("status_id"));
		riskmodelview.setStatusName(rs.getString("status_name"));
		riskmodelview.setPriorityId(rs.getInt("priority_id"));
		riskmodelview.setPriorityName(rs.getString("priority_name"));
		riskmodelview.setResult(rs.getString("result"));//
		riskmodelview.setImpactCost(rs.getBigDecimal("impact_cost"));//
		
		riskmodelview.setCreationDate(rs.getDate("creation_date"));//
		if (riskmodelview.getCreationDate() != null)
			riskmodelview.setStringCreationDate(sdf.format(riskmodelview.getCreationDate()));
		
		riskmodelview.setPlanningEndDate(rs.getDate("planning_end_date"));//
		if (riskmodelview.getPlanningEndDate() != null)
			riskmodelview.setStringPlanningEndDate(sdf.format(riskmodelview.getPlanningEndDate()));
		
		riskmodelview.setActualEndDate((rs.getDate("actual_end_date")));
		if (riskmodelview.getActualEndDate() != null)
			riskmodelview.setStringActualEndDate(sdf.format(riskmodelview.getActualEndDate()));
		
		riskmodelview.setDoneDate(rs.getDate("done_date"));//
		if (riskmodelview.getDoneDate() != null)
			riskmodelview.setStringDoneDate(sdf.format(riskmodelview.getDoneDate()));
		
		riskmodelview.setHandledDate(rs.getDate("handled_date"));//
		if (riskmodelview.getHandledDate()!= null)
			riskmodelview.setStringHandledDate(sdf.format(riskmodelview.getHandledDate()));
		
		riskmodelview.setDoneDate(rs.getDate("done_date"));//
		if (riskmodelview.getDoneDate()!= null)
			riskmodelview.setStringDoneDate(sdf.format(riskmodelview.getDoneDate()));
		
		riskmodelview.setTotalCount(rs.getInt("totalCount"));
		riskmodelview.setUpdateDate(rs.getDate("update_date"));//
		if (riskmodelview.getUpdateDate() != null)
			riskmodelview.setStringUpdateDate(sdf.format(riskmodelview.getUpdateDate()));
		riskmodelview.setUpdateBy(rs.getString("update_by"));//
		riskmodelview.setClientId(rs.getInt("client_id"));

		return riskmodelview;
	}
}
