package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.UnitsDao;
import com.redsun.service.dao.mapper.UnitsMapper;
import com.redsun.service.entities.Units;

@Repository
public class UnitsDaoImpl implements UnitsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(UnitsDaoImpl.class);

	private static String SELECT_ALL_UNITS = "SELECT id, name, devices, client_id FROM units WHERE client_id = ?";
	private static String SELECT_BY_NAME = "SELECT id, name, devices, client_id FROM units WHERE client_id = ? AND UPPER(name) = UPPER(?)";

	// Get by name
	public List<Units> getByName(final int clientId, final String name) {
		Object[] params = new Object[] { clientId, name };
		List<Units> result = jdbcTemplate.query(SELECT_BY_NAME, params, new UnitsMapper());
		return result;
	}
	
	@Override
	public List<Units> listAllUnits(int clientId) {

		List<Units> listUnits = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listUnits = jdbcTemplate.query(SELECT_ALL_UNITS, params.toArray(), new UnitsMapper());
			return listUnits;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listUnits;
	}

}
