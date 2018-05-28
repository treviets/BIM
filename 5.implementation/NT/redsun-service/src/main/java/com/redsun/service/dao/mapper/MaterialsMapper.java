package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Materials;

@Component
public class MaterialsMapper implements RowMapper<Materials> {
	@Autowired
	private Materials material;

	public Materials mapRow(ResultSet rs, int rowNum) throws SQLException {
		material = new Materials();
		material.setId(rs.getInt("id"));
		material.setCode(rs.getString("code"));
		material.setName(rs.getString("name"));
		material.setUnit(rs.getInt("unit"));
		material.setUnitName(rs.getString("unit_name"));
		material.setQuantity(rs.getDouble("quantity"));
		material.setNetPrice(rs.getDouble("net_price"));
		material.setPrice(rs.getDouble("price"));
		material.setDescription(rs.getString("description"));
		material.setClientId(rs.getInt("client_id"));
		material.setTotalCount(rs.getInt("totalCount"));
		return material;
	}

}
