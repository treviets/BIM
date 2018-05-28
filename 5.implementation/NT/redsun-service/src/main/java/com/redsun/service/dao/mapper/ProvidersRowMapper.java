package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Providers;

/**
 * Providers Mapper
 */
@Component
public class ProvidersRowMapper implements RowMapper<Providers> {

	public Providers mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Providers providers = new Providers();
		providers.setId(rs.getInt("id"));
		if(rs.wasNull()) { providers.setId(null); };
		providers.setName(rs.getString("name"));
		providers.setIdProviderType(rs.getInt("id_provider_type"));
		if(rs.wasNull()) { providers.setIdProviderType(null); };
		providers.setDescription(rs.getString("description"));
		providers.setProviderCode(rs.getString("provider_code"));
		providers.setIdPaymentDelay(rs.getInt("id_payment_delay"));
		if(rs.wasNull()) { providers.setIdPaymentDelay(null); };
		providers.setNumTax(rs.getString("num_tax"));
		providers.setTax(rs.getBigDecimal("tax"));
		if(rs.wasNull()) { providers.setTax(null); };
		providers.setDesignation(rs.getString("designation"));
		providers.setStreet(rs.getString("street"));
		providers.setComplement(rs.getString("complement"));
		providers.setZip(rs.getString("zip"));
		providers.setCity(rs.getString("city"));
		providers.setState(rs.getString("state"));
		providers.setCountry(rs.getString("country"));
		providers.setIdle(rs.getInt("idle"));
		providers.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { providers.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			providers.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return providers;
	}
}

