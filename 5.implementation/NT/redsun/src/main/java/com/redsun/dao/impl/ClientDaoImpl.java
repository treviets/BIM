package com.redsun.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.ClientDao;
import com.redsun.dao.mapper.ClientMapper;
import com.redsun.entities.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private String GET_CLIENT_BY_ID = "select c.id, c.no, c.name, c.address, c.phone, c.logo from clients c where c.no = ?";

	@Override
	public Client getClientById(int id) throws Exception {
		return jdbcTemplate.queryForObject(GET_CLIENT_BY_ID, new Object[] { String.valueOf(id) }, new ClientMapper());
	}

}
