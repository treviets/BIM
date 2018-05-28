package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Severity;

public class SeverityMapper implements RowMapper<Severity> {

	@Autowired
	Severity severity;

	public Severity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		severity = new Severity();
		severity.setId(rs.getInt("id"));
		severity.setName(rs.getString("name"));
		severity.setValue(rs.getInt("value"));
		severity.setColor(rs.getString("color"));
		severity.setSortOrder(rs.getInt("sort_order"));
		severity.setIdle(rs.getInt("idle"));
		severity.setClient_id(rs.getInt("client_id"));
		
		return severity;
	}

}
