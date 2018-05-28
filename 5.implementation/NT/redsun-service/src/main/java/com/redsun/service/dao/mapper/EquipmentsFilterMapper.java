package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Equipments;

/**
 * EquipmentsFilter Mapper
 */
@Component
public class EquipmentsFilterMapper implements RowMapper<Equipments> {

	public Equipments mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Equipments equipments = new Equipments();
		equipments.setId(rs.getInt("id"));
		equipments.setName(rs.getString("name"));
		equipments.setUnitName(rs.getString("unit_name"));
		equipments.setNetPrice(rs.getDouble("net_price"));
		equipments.setPrice(rs.getDouble("price"));
		equipments.setClientId(rs.getInt("client_id"));

		return equipments;
	}
}
