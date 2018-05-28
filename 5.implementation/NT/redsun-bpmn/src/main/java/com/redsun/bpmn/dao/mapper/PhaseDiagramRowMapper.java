package com.redsun.bpmn.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.bpmn.entities.PhaseDiagram;

/**
 * PhaseDiagram Mapper
 */
@Component
public class PhaseDiagramRowMapper implements RowMapper<PhaseDiagram> {

	public PhaseDiagram mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		PhaseDiagram phase = new PhaseDiagram();

		phase.setIdProject(rs.getInt("project_id"));
		phase.setActiveStep(rs.getString("active_step"));
		phase.setContain(rs.getString("contain"));
		return phase;
	}
}

