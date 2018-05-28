package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Expenses;

/**
 * Expenses Mapper
 */
@Component
public class ExpensesRowMapper implements RowMapper<Expenses> {

	public Expenses mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Expenses expenses = new Expenses();
		expenses.setId(rs.getInt("id"));
		if(rs.wasNull()) { expenses.setId(null); };
		expenses.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { expenses.setIdProject(null); };
		expenses.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { expenses.setIdResource(null); };
		expenses.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { expenses.setIdUser(null); };
		expenses.setIdExpenseType(rs.getInt("id_expense_type"));
		if(rs.wasNull()) { expenses.setIdExpenseType(null); };
		expenses.setScope(rs.getString("scope"));
		expenses.setName(rs.getString("name"));
		expenses.setIdStatus(rs.getInt("id_status"));
		if(rs.wasNull()) { expenses.setIdStatus(null); };
		expenses.setDescription(rs.getString("description"));
		expenses.setExpensePlannedDate(rs.getDate("expense_planned_date"));
		expenses.setExpenseRealDate(rs.getDate("expense_real_date"));
		expenses.setPlannedAmount(rs.getBigDecimal("planned_amount"));
		if(rs.wasNull()) { expenses.setPlannedAmount(null); };
		expenses.setRealAmount(rs.getBigDecimal("real_amount"));
		if(rs.wasNull()) { expenses.setRealAmount(null); };
		expenses.setDay(rs.getString("day"));
		expenses.setWeek(rs.getString("week"));
		expenses.setMonth(rs.getString("month"));
		expenses.setYear(rs.getString("year"));
		expenses.setIdle(rs.getInt("idle"));
		expenses.setReference(rs.getString("reference"));
		expenses.setExternalReference(rs.getString("external_reference"));
		expenses.setCancelled(rs.getInt("cancelled"));
		expenses.setIdDocument(rs.getInt("id_document"));
		if(rs.wasNull()) { expenses.setIdDocument(null); };
		expenses.setIdProvider(rs.getInt("id_provider"));
		if(rs.wasNull()) { expenses.setIdProvider(null); };
		expenses.setSendDate(rs.getDate("send_date"));
		expenses.setIdDeliveryMode(rs.getInt("id_delivery_mode"));
		if(rs.wasNull()) { expenses.setIdDeliveryMode(null); };
		expenses.setDeliveryDelay(rs.getString("delivery_delay"));
		expenses.setDeliveryDate(rs.getDate("delivery_date"));
		expenses.setPaymentCondition(rs.getString("payment_condition"));
		expenses.setReceptionDate(rs.getDate("reception_date"));
		expenses.setResult(rs.getString("result"));
		expenses.setTaxPct(rs.getBigDecimal("tax_pct"));
		if(rs.wasNull()) { expenses.setTaxPct(null); };
		expenses.setPlannedFullAmount(rs.getBigDecimal("planned_full_amount"));
		if(rs.wasNull()) { expenses.setPlannedFullAmount(null); };
		expenses.setRealFullAmount(rs.getBigDecimal("real_full_amount"));
		if(rs.wasNull()) { expenses.setRealFullAmount(null); };
		expenses.setIdleDate(rs.getDate("idle_date"));
		expenses.setHandled(rs.getInt("handled"));
		expenses.setHandledDate(rs.getDate("handled_date"));
		expenses.setDone(rs.getInt("done"));
		expenses.setDoneDate(rs.getDate("done_date"));
		expenses.setIdResponsible(rs.getInt("id_responsible"));
		if(rs.wasNull()) { expenses.setIdResponsible(null); };
		expenses.setPaymentDone(rs.getInt("payment_done"));
		expenses.setIdContact(rs.getInt("id_contact"));
		if(rs.wasNull()) { expenses.setIdContact(null); };
		expenses.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { expenses.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			expenses.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return expenses;
	}
}

