package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.PositionDao;
import com.redsun.service.dao.mapper.PositionMapper;
import com.redsun.service.entities.Position;

@Repository
public class PositionDaoImpl implements PositionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_POSITION_BY_ID = "select id, code, name, description, client_id from positions where id = ?";
	private static Logger log = Logger.getLogger(PositionDaoImpl.class);

	public List<Position> listPositions(int clientId, String code, String name) {
		// TODO Auto-generated method stub
		List<Position> listPosition = null;
		String sql = "select id, code, name, description, client_id"
				+ " from customers where client_id = ?";//
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		if(code.equals("undefined")==false){
			sql += " and UPPER(code) like UPPER(?)";
			params.add("%" + code + "%");
		}
		if(name.equals("undefined")==false){
			sql += " and UPPER(name) like UPPER(?)";
			params.add("%" + name + "%");
		}
		sql = sql + " order by id ASC";
		try {
			listPosition = jdbcTemplate.query(sql, params.toArray(), new PositionMapper());
			return listPosition;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listPosition;
	}
	public List<Position> getPositionDetails(int id) {
		List<Position> positions = null;
		try {
			positions = jdbcTemplate.query(SELECT_POSITION_BY_ID, new Object[] { id }, new PositionMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return positions;
	}


}
