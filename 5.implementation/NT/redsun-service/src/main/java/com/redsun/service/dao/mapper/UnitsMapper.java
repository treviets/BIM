package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Units;

@Component
public class UnitsMapper implements RowMapper<Units> {

	@Autowired
	private Units unit;

	public Units mapRow(ResultSet rs, int rowNum) throws SQLException {

		unit = new Units();
		unit.setId(rs.getInt("id"));
		unit.setName(rs.getString("name"));
		unit.setDevices(rs.getInt("devices"));
		unit.setClientId(rs.getInt("client_id"));
		return unit;
	}

}
