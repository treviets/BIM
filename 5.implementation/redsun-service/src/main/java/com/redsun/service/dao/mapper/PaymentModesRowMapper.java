package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.PaymentModes;

/**
 * PaymentModes Mapper
 */
@Component
public class PaymentModesRowMapper implements RowMapper<PaymentModes> {

	public PaymentModes mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		PaymentModes paymentModes = new PaymentModes();
		paymentModes.setId(rs.getInt("id"));
		if(rs.wasNull()) { paymentModes.setId(null); };
		paymentModes.setName(rs.getString("name"));
		paymentModes.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { paymentModes.setSortOrder(null); };
		paymentModes.setIdle(rs.getInt("idle"));
		paymentModes.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { paymentModes.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			paymentModes.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return paymentModes;
	}
}

