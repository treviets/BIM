package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Equipments;

@Component
public class EquipmentsMapper implements RowMapper<Equipments> {
	@Autowired
	private Equipments equipment;

	public Equipments mapRow(ResultSet rs, int rowNum) throws SQLException {
		equipment = new Equipments();
		equipment.setId(rs.getInt("id"));
		equipment.setCode(rs.getString("code"));
		equipment.setName(rs.getString("name"));
		equipment.setUnit(rs.getInt("unit"));
		equipment.setUnitName(rs.getString("unit_name"));
		equipment.setQuantity(rs.getInt("quantity"));
		equipment.setNetPrice(rs.getDouble("net_price"));
		equipment.setPrice(rs.getDouble("price"));
		equipment.setDescription(rs.getString("description"));
		equipment.setClientId(rs.getInt("client_id"));
		equipment.setTotalCount(rs.getInt("totalCount"));
		return equipment;
	}

}
