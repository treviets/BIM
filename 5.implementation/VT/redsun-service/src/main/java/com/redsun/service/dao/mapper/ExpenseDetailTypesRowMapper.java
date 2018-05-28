package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.ExpenseDetailTypes;

/**
 * ExpenseDetailTypes Mapper
 */
@Component
public class ExpenseDetailTypesRowMapper implements RowMapper<ExpenseDetailTypes> {

	public ExpenseDetailTypes mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		ExpenseDetailTypes expenseDetailTypes = new ExpenseDetailTypes();
		expenseDetailTypes.setId(rs.getInt("id"));
		if(rs.wasNull()) { expenseDetailTypes.setId(null); };
		expenseDetailTypes.setName(rs.getString("name"));
		expenseDetailTypes.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { expenseDetailTypes.setSortOrder(null); };
		expenseDetailTypes.setValue01(rs.getBigDecimal("value01"));
		if(rs.wasNull()) { expenseDetailTypes.setValue01(null); };
		expenseDetailTypes.setValue02(rs.getBigDecimal("value02"));
		if(rs.wasNull()) { expenseDetailTypes.setValue02(null); };
		expenseDetailTypes.setValue03(rs.getBigDecimal("value03"));
		if(rs.wasNull()) { expenseDetailTypes.setValue03(null); };
		expenseDetailTypes.setUnit01(rs.getString("unit01"));
		expenseDetailTypes.setUnit02(rs.getString("unit02"));
		expenseDetailTypes.setUnit03(rs.getString("unit03"));
		expenseDetailTypes.setIdle(rs.getInt("idle"));
		expenseDetailTypes.setDescription(rs.getString("description"));
		expenseDetailTypes.setIndividual(rs.getInt("individual"));
		expenseDetailTypes.setProject(rs.getInt("project"));
		expenseDetailTypes.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { expenseDetailTypes.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			expenseDetailTypes.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return expenseDetailTypes;
	}
}

