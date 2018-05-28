package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Organization;

public class OrganizationMapper implements RowMapper<Organization> {
	@Autowired
	private Organization organization;
	@Override
	public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

		
		organization = new Organization();
		
		organization.setId(rs.getInt("id"));
		organization.setName(rs.getString("name"));
		organization.setDescription(rs.getString("description"));
		organization.setIdle(rs.getInt("idle"));
		organization.setUserId(rs.getInt("id_user"));
		organization.setResourceId(rs.getInt("id_resource"));
		organization.setSortOrder(rs.getString("sort_order"));
		organization.setOrganizationTypeId(rs.getInt("id_organization_type"));
		organization.setOrganizationId(rs.getInt("id_organization"));
		organization.setCreationDate(rs.getDate("creation_date"));
		if (organization.getCreationDate() != null)
			organization.setStringCreationDate(sdf.format(organization.getCreationDate()));
		organization.setLastUpdateDateTime(rs.getDate("last_update_date_time"));
		if (organization.getLastUpdateDateTime() != null)
			organization.setStringLastUpdateDateTime(sdf.format(organization.getLastUpdateDateTime()));

		return organization;
	}

}
