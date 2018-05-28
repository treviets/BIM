package com.redsun.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.User;

public class UserRowMapper implements RowMapper<User>{

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setClientId(rs.getInt("client_id"));
		user.setCreatedBy(rs.getString("create_by"));
		user.setCreatedDate(rs.getString("create_date"));
		user.setStatus(rs.getInt("status")==0?"Inactive":"Active");
		user.setRole(rs.getString("role_name"));
		return user;
	}

}
