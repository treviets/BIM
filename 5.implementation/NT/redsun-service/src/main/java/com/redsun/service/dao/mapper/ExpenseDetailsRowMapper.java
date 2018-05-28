package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.ExpenseDetails;

/**
 * ExpenseDetails Mapper
 */
@Component
public class ExpenseDetailsRowMapper implements RowMapper<ExpenseDetails> {

	public ExpenseDetails mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		ExpenseDetails expenseDetails = new ExpenseDetails();
		expenseDetails.setId(rs.getInt("id"));
		if(rs.wasNull()) { expenseDetails.setId(null); };
		expenseDetails.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { expenseDetails.setIdProject(null); };
		expenseDetails.setIdExpense(rs.getInt("id_expense"));
		if(rs.wasNull()) { expenseDetails.setIdExpense(null); };
		expenseDetails.setExpenseDate(rs.getDate("expense_date"));
		expenseDetails.setName(rs.getString("name"));
		expenseDetails.setIdExpenseDetailType(rs.getInt("id_expense_detail_type"));
		if(rs.wasNull()) { expenseDetails.setIdExpenseDetailType(null); };
		expenseDetails.setValue01(rs.getBigDecimal("value01"));
		if(rs.wasNull()) { expenseDetails.setValue01(null); };
		expenseDetails.setValue02(rs.getBigDecimal("value02"));
		if(rs.wasNull()) { expenseDetails.setValue02(null); };
		expenseDetails.setValue03(rs.getBigDecimal("value03"));
		if(rs.wasNull()) { expenseDetails.setValue03(null); };
		expenseDetails.setUnit01(rs.getString("unit01"));
		expenseDetails.setUnit02(rs.getString("unit02"));
		expenseDetails.setUnit03(rs.getString("unit03"));
		expenseDetails.setDescription(rs.getString("description"));
		expenseDetails.setAmount(rs.getBigDecimal("amount"));
		if(rs.wasNull()) { expenseDetails.setAmount(null); };
		expenseDetails.setIdle(rs.getInt("idle"));
		expenseDetails.setExternalReference(rs.getString("external_reference"));
		expenseDetails.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { expenseDetails.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			expenseDetails.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return expenseDetails;
	}
}

