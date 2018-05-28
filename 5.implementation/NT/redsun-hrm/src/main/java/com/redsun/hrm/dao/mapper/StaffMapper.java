package com.redsun.hrm.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.hrm.entities.Staffs;


public class StaffMapper implements RowMapper<Staffs> {

	public Staffs mapRow(ResultSet rs, int rowNum) throws SQLException {

		Staffs staff = null;
		staff = new Staffs();
		staff.setId(rs.getInt("id"));
		staff.setCode(rs.getString("code"));
		staff.setName(rs.getString("name"));
		staff.setFullName(rs.getString("full_name"));
		staff.setDeparmentId(rs.getInt("department_id"));
		staff.setPositionId(rs.getInt("position_id"));
		staff.setEmail(rs.getString("email"));
		staff.setPhone(rs.getString("mobile"));
		staff.setDescription(rs.getString("description"));
		staff.setImg(rs.getString("img"));
		staff.setClientId(rs.getInt("client_id"));
		return staff;
	}

}
