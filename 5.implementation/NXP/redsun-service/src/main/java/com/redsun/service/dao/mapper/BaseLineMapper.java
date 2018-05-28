package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.BaseLines;

/**
 * BaseLine Mapper
 */
@Component
public class BaseLineMapper implements RowMapper<BaseLines> {

	public BaseLines mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		BaseLines baseLines = new BaseLines();
		baseLines.setId(rs.getInt("id"));
		baseLines.setName(rs.getString("name"));
		baseLines.setDescription(rs.getString("description"));
		baseLines.setBaselineDate(rs.getDate("baseline_date"));
		return baseLines;
	}
}

