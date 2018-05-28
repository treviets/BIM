package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Types;

/**
 * Types Mapper
 */
@Component
public class TypesRowMapper implements RowMapper<Types> {

	public Types mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Types types = new Types();
		types.setId(rs.getInt("id"));
		types.setScope(rs.getString("scope"));
		types.setName(rs.getString("name"));
		types.setSortOrder(rs.getInt("sort_order"));
		types.setColor(rs.getString("color"));
		types.setIdWorkflow(rs.getInt("id_workflow"));
		types.setDescription(rs.getString("description"));
		types.setIdPlanningMode(rs.getInt("id_planning_mode"));
		types.setIdCategory(rs.getInt("id_category"));
		types.setClientId(rs.getInt("client_id"));
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			types.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return types;
	}
}

