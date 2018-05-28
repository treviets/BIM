package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Payments;

/**
 * Payments Mapper
 */
@Component
public class PaymentsRowMapper implements RowMapper<Payments> {

	public Payments mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Payments payments = new Payments();
		payments.setId(rs.getInt("id"));
		if(rs.wasNull()) { payments.setId(null); };
		payments.setName(rs.getString("name"));
		payments.setIdBill(rs.getInt("id_bill"));
		if(rs.wasNull()) { payments.setIdBill(null); };
		payments.setPaymentDate(rs.getDate("payment_date"));
		payments.setIdPaymentMode(rs.getInt("id_payment_mode"));
		if(rs.wasNull()) { payments.setIdPaymentMode(null); };
		payments.setIdle(rs.getInt("idle"));
		payments.setIdPaymentType(rs.getInt("id_payment_type"));
		if(rs.wasNull()) { payments.setIdPaymentType(null); };
		payments.setPaymentAmount(rs.getBigDecimal("payment_amount"));
		if(rs.wasNull()) { payments.setPaymentAmount(null); };
		payments.setPaymentFeeAmount(rs.getBigDecimal("payment_fee_amount"));
		if(rs.wasNull()) { payments.setPaymentFeeAmount(null); };
		payments.setPaymentCreditAmount(rs.getBigDecimal("payment_credit_amount"));
		if(rs.wasNull()) { payments.setPaymentCreditAmount(null); };
		payments.setDescription(rs.getString("description"));
		payments.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { payments.setIdUser(null); };
		payments.setCreationDate(rs.getDate("creation_date"));
		payments.setReferenceBill(rs.getString("reference_bill"));
		payments.setIdClient(rs.getInt("id_client"));
		if(rs.wasNull()) { payments.setIdClient(null); };
		payments.setIdRecipient(rs.getInt("id_recipient"));
		if(rs.wasNull()) { payments.setIdRecipient(null); };
		payments.setBillAmount(rs.getBigDecimal("bill_amount"));
		if(rs.wasNull()) { payments.setBillAmount(null); };
		payments.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { payments.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			payments.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return payments;
	}
}

