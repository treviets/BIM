package com.redsun.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.Client;

public class ClientMapper implements RowMapper<Client> {

	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

		Client client = null;
		client = new Client();
		client.setId(rs.getInt("id"));
		client.setNo(rs.getString("no"));
		client.setName(rs.getString("name"));
		client.setAddress(rs.getString("address"));
		client.setPhone(rs.getString("phone"));
		client.setLogo(rs.getString("logo"));
		return client;
	}

}
