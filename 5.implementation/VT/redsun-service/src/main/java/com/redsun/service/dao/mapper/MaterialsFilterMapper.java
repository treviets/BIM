package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Materials;

/**
 * MaterialsFilter Mapper
 */
@Component
public class MaterialsFilterMapper implements RowMapper<Materials> {

	public Materials mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Materials materials = new Materials();
		materials.setId(rs.getInt("id"));
		materials.setName(rs.getString("name"));
		materials.setUnitName(rs.getString("unit_name"));
		materials.setNetPrice(rs.getDouble("net_price"));
		materials.setPrice(rs.getDouble("price"));
		materials.setClientId(rs.getInt("client_id"));

		return materials;
	}
}
