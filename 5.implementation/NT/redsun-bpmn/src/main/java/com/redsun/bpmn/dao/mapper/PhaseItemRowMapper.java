package com.redsun.bpmn.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.bpmn.entities.PhaseItem;

/**
 * PhaseItem Mapper
 */
@Component
public class PhaseItemRowMapper implements RowMapper<PhaseItem> {

	public PhaseItem mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		PhaseItem phase = new PhaseItem();
		
		phase.setId(rs.getInt("id"));
		if(rs.wasNull()) { 
			phase.setId(null); 
		};
		
		phase.setIdProject(rs.getInt("project_id"));
		phase.setActiveStep(rs.getString("active_step"));
		phase.setContain(rs.getString("contain"));
		return phase;
	}
}

