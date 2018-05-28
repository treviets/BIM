package com.redsun.hrm.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.hrm.entities.Departments;
import com.redsun.hrm.entities.Positions;


public class PositionMapper implements RowMapper<Positions> {

	public Positions mapRow(ResultSet rs, int rowNum) throws SQLException {

		Positions position = null;
		position = new Positions();
		position.setId(rs.getInt("id"));
		position.setName(rs.getString("name"));
		position.setIsUsed(rs.getInt("is_used"));
		return position;
	}

}
