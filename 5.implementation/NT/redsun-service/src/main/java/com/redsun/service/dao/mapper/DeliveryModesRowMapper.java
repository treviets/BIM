package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.DeliveryModes;

/**
 * DeliveryModes Mapper
 */
@Component
public class DeliveryModesRowMapper implements RowMapper<DeliveryModes> {

	public DeliveryModes mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		DeliveryModes deliveryModes = new DeliveryModes();
		deliveryModes.setId(rs.getInt("id"));
		if(rs.wasNull()) { deliveryModes.setId(null); };
		deliveryModes.setName(rs.getString("name"));
		deliveryModes.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { deliveryModes.setSortOrder(null); };
		deliveryModes.setIdle(rs.getInt("idle"));
		deliveryModes.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { deliveryModes.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			deliveryModes.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return deliveryModes;
	}
}

