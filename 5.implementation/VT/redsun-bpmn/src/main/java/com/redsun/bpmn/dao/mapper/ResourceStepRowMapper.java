package com.redsun.bpmn.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.bpmn.entities.ResourceStep;

/**
 * Resource Step Mapper
 */
@Component
public class ResourceStepRowMapper implements RowMapper<ResourceStep> {

	public ResourceStep mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		ResourceStep resourceStep = new ResourceStep();
		
		resourceStep.setId(rs.getInt("id"));
		resourceStep.setProjectId(rs.getInt("project_id"));
		resourceStep.setStepId(rs.getString("step_id"));
		resourceStep.setResourceCode(rs.getString("code"));
		resourceStep.setEmailOfResponsible(rs.getString("email"));
		
		return resourceStep;
	}
}

