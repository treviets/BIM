package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Clients;

/**
 * Clients Mapper
 */
@Component
public class ClientsRowMapper implements RowMapper<Clients> {

	public Clients mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Clients clients = new Clients();
		clients.setId(rs.getInt("id"));
		if(rs.wasNull()) { clients.setId(null); };
		clients.setName(rs.getString("name"));
		clients.setDescription(rs.getString("description"));
		clients.setClientCode(rs.getString("client_code"));
		clients.setIdle(rs.getInt("idle"));
		clients.setPaymentDelay(rs.getInt("payment_delay"));
		clients.setTax(rs.getBigDecimal("tax"));
		clients.setStreet(rs.getString("street"));
		clients.setComplement(rs.getString("complement"));
		clients.setZip(rs.getString("zip"));
		clients.setCity(rs.getString("city"));
		clients.setState(rs.getString("state"));
		clients.setCountry(rs.getString("country"));
		clients.setIdClientType(rs.getInt("id_client_type"));
		clients.setPaymentDelayEndOfMonth(rs.getInt("payment_delay_end_of_month"));
		clients.setNumTax(rs.getString("num_tax"));
		clients.setIdPaymentDelay(rs.getInt("id_payment_delay"));
		clients.setClientId(rs.getInt("client_id"));
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			clients.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return clients;
	}
}

