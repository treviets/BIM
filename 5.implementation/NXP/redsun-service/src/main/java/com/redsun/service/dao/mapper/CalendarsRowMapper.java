package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Calendars;

@Component
public class CalendarsRowMapper implements RowMapper<Calendars> {

	@Autowired
	private Calendars calendar;

	@Override
	public Calendars mapRow(ResultSet rs, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

		calendar = new Calendars();

		calendar.setId(rs.getInt("id"));
		calendar.setTitle(rs.getString("calendar_name"));
		calendar.setProjectId(rs.getInt("project_id"));
		calendar.setProjectName(rs.getString("project_name"));
		
		calendar.setStart(rs.getDate("start_date"));
		if (calendar.getStart() != null)
			calendar.setStringStartDate(sdf.format(calendar.getStart()));
		
		calendar.setEnd(rs.getDate("end_date"));
		if (calendar.getEnd() != null)
			calendar.setStringEndtDate(sdf.format(calendar.getEnd()));
		
		calendar.setDescription(rs.getString("description"));
		calendar.setAttachFile(rs.getString("attach_file"));
		calendar.setClientId(rs.getInt("client_id"));

		return calendar;
	}

}
