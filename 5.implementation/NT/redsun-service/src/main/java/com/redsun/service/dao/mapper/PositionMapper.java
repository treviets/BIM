package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Position;

public class PositionMapper implements RowMapper<Position> {

	public Position mapRow(ResultSet rs, int rowNum) throws SQLException {

		Position position = null;
		position = new Position();
		position.setId(rs.getInt("id"));
		position.setCode(rs.getString("code"));
		position.setName(rs.getString("name"));
		position.setDescription(rs.getString("description"));
		position.setClientId(rs.getInt("client_id"));
		return position;
	}

}
