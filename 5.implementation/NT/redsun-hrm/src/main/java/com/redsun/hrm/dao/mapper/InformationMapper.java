package com.redsun.hrm.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.hrm.entities.Departments;
import com.redsun.hrm.entities.Information;


public class InformationMapper implements RowMapper<Information> {

	public Information mapRow(ResultSet rs, int rowNum) throws SQLException {

		Information inf = new Information();
		inf.setUserId(rs.getInt("user_id"));
		inf.setCompanyId(rs.getInt("company_id"));
		return inf;
	}

}
