package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Configurations;

@Component
public class ConfigurationsMapper implements RowMapper<Configurations> {
	@Autowired
	private Configurations configuration;

	public Configurations mapRow(ResultSet rs, int rowNum) throws SQLException {
		configuration = new Configurations();
		configuration.setId(rs.getInt("id"));
		configuration.setProjectId(rs.getInt("id_project"));
		configuration.setLevel(rs.getInt("level"));
		configuration.setRoleName(rs.getString("role_name"));
		configuration.setFactor(rs.getInt("factor"));
		configuration.setClientId(rs.getInt("client_id"));
		return configuration;
	}

}
