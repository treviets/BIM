package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.PlanningModes;

/**
 * PlanningModes Mapper
 */
@Component
public class PlanningModesRowMapper implements RowMapper<PlanningModes> {

	public PlanningModes mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		PlanningModes planningModes = new PlanningModes();
		planningModes.setId(rs.getInt("id"));
		if(rs.wasNull()) { planningModes.setId(null); };
		planningModes.setName(rs.getString("name"));
		planningModes.setCode(rs.getString("code"));
		planningModes.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { planningModes.setSortOrder(null); };
		planningModes.setMandatoryStartDate(rs.getInt("mandatory_start_date"));
		planningModes.setMandatoryEndDate(rs.getInt("mandatory_end_date"));
		planningModes.setApplyTo(rs.getString("apply_to"));
		planningModes.setIdle(rs.getInt("idle"));
		planningModes.setMandatoryDuration(rs.getInt("mandatory_duration"));
		planningModes.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { planningModes.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			planningModes.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return planningModes;
	}
}

