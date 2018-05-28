package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * Map Mapper
 */
@Component
public class SelectionsRowMapper implements RowMapper<Map<Object, Object>> {
	
	public Map<Object, Object> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		// id.
		map.put("id", rs.getObject(1));
		// name.
		map.put("name", rs.getObject(2));
        
		return map;
	}
}

