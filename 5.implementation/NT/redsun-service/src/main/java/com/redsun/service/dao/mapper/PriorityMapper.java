package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Priority;

public class PriorityMapper implements RowMapper<Priority> {

	@Autowired
	private Priority priority;

	public Priority mapRow(ResultSet rs, int rowNum) throws SQLException {

		priority = new Priority();
		priority.setId(rs.getInt("id"));
		priority.setName(rs.getString("name"));
		priority.setValue(rs.getInt("value"));
		priority.setColor(rs.getString("color"));
		priority.setSortOrder(rs.getInt("sort_order"));
		priority.setIdle(rs.getInt("idle"));
		priority.setTotalCount(rs.getInt("totalCount"));
		priority.setClient_id(rs.getInt("client_id"));
		return priority;
	}

}
