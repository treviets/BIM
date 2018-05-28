package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Likelihood;

@Component
public class LikelihoodMapper implements RowMapper<Likelihood> {

	@Autowired
	private Likelihood likelihood;

	public Likelihood mapRow(ResultSet rs, int rowNum) throws SQLException {

		likelihood = new Likelihood();
		likelihood.setId(rs.getInt("id"));
		likelihood.setName(rs.getString("name"));
		likelihood.setValue(rs.getInt("value"));
		likelihood.setColor(rs.getString("color"));
		likelihood.setSortOrder(rs.getInt("sort_order"));
		likelihood.setIdle(rs.getInt("value_pct"));
		likelihood.setClient_id(rs.getInt("client_id"));
		return likelihood;
	}

}
