package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Criticality;

@Component
public class CriticalityMapper implements RowMapper<Criticality> {
	@Autowired
	private Criticality criticality;

	public Criticality mapRow(ResultSet rs, int rowNum) throws SQLException {
		criticality = new Criticality();
		criticality.setId(rs.getInt("id"));
		criticality.setName(rs.getString("name"));
		criticality.setValue(rs.getInt("value"));
		criticality.setColor(rs.getString("color"));
		criticality.setSortOrder(rs.getInt("sort_order"));
		criticality.setIdle(rs.getInt("idle"));
		criticality.setClient_id(rs.getInt("client_id"));
		return criticality;
	}

}
