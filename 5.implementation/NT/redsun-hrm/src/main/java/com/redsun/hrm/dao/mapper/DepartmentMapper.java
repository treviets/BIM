package com.redsun.hrm.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.hrm.entities.Departments;


public class DepartmentMapper implements RowMapper<Departments> {

	public Departments mapRow(ResultSet rs, int rowNum) throws SQLException {

		Departments department = null;
		department = new Departments();
		department.setId(rs.getInt("id"));
		department.setCode(rs.getString("code"));
		department.setName(rs.getString("name"));
		department.setParentId(rs.getInt("parent_id"));
		department.setWbs(rs.getString("wbs"));
		department.setLevel(rs.getInt("level"));
		department.setChiefName(rs.getString("chief_name"));
		department.setImg(rs.getString("img"));
		return department;
	}

}
