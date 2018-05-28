package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Project;

public class ProjectDetailMapper implements RowMapper<Project> {

	@Autowired
	private Project projectDetail;

	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

		projectDetail = new Project();
		projectDetail.setId(rs.getInt("id"));
		projectDetail.setName(rs.getString("name"));
		projectDetail.setLocation(rs.getString("location"));
		projectDetail.setDescription(rs.getString("description"));
		projectDetail.setCode(rs.getString("code"));
		projectDetail.setParentId(rs.getInt("parent_id"));
		projectDetail.setStatusId(rs.getInt("status_id"));
		projectDetail.setStatusName(rs.getString("status_name"));
		projectDetail.setStartDate(rs.getDate("start_date"));
		if (projectDetail.getStartDate() != null)
			projectDetail.setStringStartDate(sdf.format(projectDetail.getStartDate()));
		projectDetail.setDoneDate(rs.getDate("done_date"));
		if (projectDetail.getDoneDate() != null)
			projectDetail.setStringDoneDate(sdf.format(projectDetail.getDoneDate()));
		projectDetail.setEndDate(rs.getDate("end_date"));
		projectDetail.setDuration(rs.getInt("duration"));
		projectDetail.setWorkSunday(rs.getInt("work_sunday"));
		if (projectDetail.getEndDate() != null)
			projectDetail.setStringEndDate(sdf.format(projectDetail.getEndDate()));
		projectDetail.setClosedDate(rs.getDate("closed_date"));
		if (projectDetail.getClosedDate() != null)
			projectDetail.setStringClosedDate(sdf.format(projectDetail.getClosedDate()));
		projectDetail.setCreationDate(rs.getDate("creation_date"));
		if (projectDetail.getCreationDate() != null)
			projectDetail.setStringCreationDate(sdf.format(projectDetail.getCreationDate()));
		projectDetail.setLastUpdateDateTime(rs.getDate("last_update_date_time"));
		if (projectDetail.getLastUpdateDateTime() != null)
			projectDetail.setStringLastUpdateDateTime(sdf.format(projectDetail.getLastUpdateDateTime()));
		projectDetail.setCustomerId(rs.getInt("customer_id"));
		projectDetail.setCustomerName(rs.getString("customer_name"));
		projectDetail.setTypeId(rs.getInt("type_id"));
		projectDetail.setTypeName(rs.getString("type_name"));
		projectDetail.setHowManyDayPaymentDelay(rs.getString("how_many_day_payment_delay"));
		projectDetail.setAmount(rs.getBigDecimal("amount"));
		projectDetail.setUpdateBy(rs.getString("update_by"));
		projectDetail.setClientId(rs.getInt("client_id"));

		return projectDetail;
	}

}
