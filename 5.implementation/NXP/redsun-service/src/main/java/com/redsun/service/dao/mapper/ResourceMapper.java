package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Resource;


public class ResourceMapper implements RowMapper<Resource> {

	public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {

		Resource resource = null;
		resource = new Resource();
		resource.setId(rs.getInt("id"));
		resource.setNo(rs.getString("code"));
		resource.setName(rs.getString("name"));
		resource.setFullName(rs.getString("full_name"));
		resource.setEmail(rs.getString("email"));
		resource.setDescription(rs.getString("description"));
		resource.setAddress(rs.getString("address"));
		resource.setPhone(rs.getString("phone"));
		resource.setClientId(rs.getInt("client_id"));
		resource.setPositionId(rs.getInt("position_id"));
		resource.setTotalCount(rs.getInt("total_count"));
		return resource;
	}

}
