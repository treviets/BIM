package com.redsun.social.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.social.entities.Chatting;


@Component
public class ChattingMapper implements RowMapper<Chatting> {
	
	@Autowired
	private Chatting chatting;

	public Chatting mapRow(ResultSet rs, int rowNum) throws SQLException {

		chatting = new Chatting();
		chatting.setId(rs.getInt("id"));
		chatting.setClientId(rs.getInt("client_id"));
		chatting.setProjectId(rs.getInt("project_id"));
		chatting.setCreatedBy(rs.getString("created_by"));
		chatting.setCreatedDate(rs.getString("created_date"));
		chatting.setMessage(rs.getString("message"));
		chatting.setAvatar(rs.getString("avatar"));
		chatting.setScopes(rs.getInt("scopes"));
	
		return chatting;
	}
}
