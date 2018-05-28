package com.redsun.hrm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.hrm.dao.PositionDao;
import com.redsun.hrm.dao.mapper.PositionMapper;
import com.redsun.hrm.entities.Positions;


@Repository
public class PositionDaoImpl implements PositionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_POSITION_BY_STAFF = "SELECT id, name, is_used"
											+ " FROM positions WHERE staff_id = ? AND client_id = ? AND is_used = '0' ORDER BY name";
	static private String SELECT_ALL_POSITIONS = "SELECT id, name, is_used"
											+ " FROM positions WHERE client_id = ? AND is_used = '0' ORDER BY name";
	private static Logger log = Logger.getLogger(PositionDaoImpl.class);

	public List<Positions> getPositionByStaff(int staffId, int clientId) {
		// TODO Auto-generated method stub
		List<Positions> position = null;
		List<Object> params = new ArrayList<Object>();
		params.add(staffId);
		params.add(clientId);
		try {
			position = jdbcTemplate.query(SELECT_POSITION_BY_STAFF, params.toArray(), new PositionMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return position;
	}

	@Override
	public List<Positions> getAllPositions(int clientId) {
		// TODO Auto-generated method stub
		List<Positions> listPositions = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listPositions = jdbcTemplate.query(SELECT_ALL_POSITIONS, params.toArray(), new PositionMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listPositions;
	}
}
