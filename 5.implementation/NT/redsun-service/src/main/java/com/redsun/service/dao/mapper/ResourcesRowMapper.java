package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Resources;

/**
 * Resources Mapper
 */
@Component
public class ResourcesRowMapper implements RowMapper<Resources> {

	public Resources mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Resources resources = new Resources();
		resources.setCode(rs.getString("code"));
		resources.setTitle(rs.getString("title"));
		resources.setIdCard(rs.getString("id_card"));
		resources.setId(rs.getInt("id"));
		resources.setName(rs.getString("name"));
		resources.setFullName(rs.getString("full_name"));
		resources.setEmail(rs.getString("email"));
		resources.setDescription(rs.getString("description"));
		resources.setPhone(rs.getString("phone"));
		resources.setMobile(rs.getString("mobile"));
		resources.setFax(rs.getString("fax"));
		resources.setAddress(rs.getString("address"));
		resources.setStreet(rs.getString("street"));
		resources.setZip(rs.getString("zip"));
		resources.setCity(rs.getString("city"));
		resources.setState(rs.getString("state"));
		resources.setCountry(rs.getString("country"));
		resources.setSalary(rs.getDouble("salary"));
		resources.setIsUser(rs.getInt("is_user"));
		resources.setIsResource(rs.getInt("is_resource"));
		resources.setIsContact(rs.getInt("is_contact"));
		resources.setIsTrash(rs.getInt("is_trash"));
		resources.setTotalCount(rs.getInt("total_count"));
		resources.setClientId(rs.getInt("client_id"));

		return resources;
	}
}
