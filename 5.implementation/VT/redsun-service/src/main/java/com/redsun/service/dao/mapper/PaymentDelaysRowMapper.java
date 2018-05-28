package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.PaymentDelays;

/**
 * PaymentDelays Mapper
 */
@Component
public class PaymentDelaysRowMapper implements RowMapper<PaymentDelays> {

	public PaymentDelays mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		PaymentDelays paymentDelays = new PaymentDelays();
		paymentDelays.setId(rs.getInt("id"));
		if(rs.wasNull()) { paymentDelays.setId(null); };
		paymentDelays.setName(rs.getString("name"));
		paymentDelays.setDays(rs.getInt("days"));
		if(rs.wasNull()) { paymentDelays.setDays(null); };
		paymentDelays.setEndOfMonth(rs.getInt("end_of_month"));
		paymentDelays.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { paymentDelays.setSortOrder(null); };
		paymentDelays.setIdle(rs.getInt("idle"));
		paymentDelays.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { paymentDelays.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			paymentDelays.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return paymentDelays;
	}
}

