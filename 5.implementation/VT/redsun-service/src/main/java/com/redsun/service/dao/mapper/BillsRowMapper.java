package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Bills;

/**
 * Bills Mapper
 */
@Component
public class BillsRowMapper implements RowMapper<Bills> {

	public Bills mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Bills bills = new Bills();
		bills.setId(rs.getInt("id"));
		if(rs.wasNull()) { bills.setId(null); };
		bills.setIdBillType(rs.getInt("id_bill_type"));
		if(rs.wasNull()) { bills.setIdBillType(null); };
		bills.setName(rs.getString("name"));
		bills.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { bills.setIdProject(null); };
		bills.setIdClient(rs.getInt("id_client"));
		if(rs.wasNull()) { bills.setIdClient(null); };
		bills.setIdContact(rs.getInt("id_contact"));
		if(rs.wasNull()) { bills.setIdContact(null); };
		bills.setIdRecipient(rs.getInt("id_recipient"));
		if(rs.wasNull()) { bills.setIdRecipient(null); };
		bills.setBillingType(rs.getString("billing_type"));
		bills.setDescription(rs.getString("description"));
		bills.setDate(rs.getDate("date"));
		bills.setIdStatus(rs.getInt("id_status"));
		if(rs.wasNull()) { bills.setIdStatus(null); };
		bills.setDone(rs.getInt("done"));
		bills.setIdle(rs.getInt("idle"));
		bills.setBillId(rs.getInt("bill_id"));
		if(rs.wasNull()) { bills.setBillId(null); };
		bills.setTax(rs.getBigDecimal("tax"));
		if(rs.wasNull()) { bills.setTax(null); };
		bills.setUntaxedAmount(rs.getBigDecimal("untaxed_amount"));
		if(rs.wasNull()) { bills.setUntaxedAmount(null); };
		bills.setFullAmount(rs.getBigDecimal("full_amount"));
		if(rs.wasNull()) { bills.setFullAmount(null); };
		bills.setCancelled(rs.getInt("cancelled"));
		bills.setIdActivityType(rs.getInt("id_activity_type"));
		if(rs.wasNull()) { bills.setIdActivityType(null); };
		bills.setReference(rs.getString("reference"));
		bills.setPaymentDone(rs.getInt("payment_done"));
		bills.setPaymentDate(rs.getDate("payment_date"));
		bills.setPaymentAmount(rs.getBigDecimal("payment_amount"));
		if(rs.wasNull()) { bills.setPaymentAmount(null); };
		bills.setIdPaymentDelay(rs.getInt("id_payment_delay"));
		if(rs.wasNull()) { bills.setIdPaymentDelay(null); };
		bills.setPaymentDueDate(rs.getDate("payment_due_date"));
		bills.setIdDeliveryMode(rs.getInt("id_delivery_mode"));
		if(rs.wasNull()) { bills.setIdDeliveryMode(null); };
		bills.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { bills.setIdResource(null); };
		bills.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { bills.setIdUser(null); };
		bills.setCreationDate(rs.getDate("creation_date"));
		bills.setPaymentsCount(rs.getInt("payments_count"));
		if(rs.wasNull()) { bills.setPaymentsCount(null); };
		bills.setCommandAmountPct(rs.getInt("command_amount_pct"));
		if(rs.wasNull()) { bills.setCommandAmountPct(null); };
		bills.setSendDate(rs.getDate("send_date"));
		bills.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { bills.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			bills.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return bills;
	}
}

