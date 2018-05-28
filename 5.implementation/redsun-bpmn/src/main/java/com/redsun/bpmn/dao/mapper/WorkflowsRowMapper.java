package com.redsun.bpmn.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.bpmn.entities.Workflows;

/**
 * Workflows Mapper
 */
@Component
public class WorkflowsRowMapper implements RowMapper<Workflows> {

	public Workflows mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Workflows workflows = new Workflows();
		workflows.setId(rs.getInt("id"));
		if(rs.wasNull()) { workflows.setId(null); };
		workflows.setName(rs.getString("name"));
		workflows.setDescription(rs.getString("description"));
		workflows.setIdle(rs.getInt("idle"));
		workflows.setWorkflowUpdate(rs.getString("workflow_update"));
		workflows.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { workflows.setSortOrder(null); };
		workflows.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { workflows.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			workflows.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return workflows;
	}
}

