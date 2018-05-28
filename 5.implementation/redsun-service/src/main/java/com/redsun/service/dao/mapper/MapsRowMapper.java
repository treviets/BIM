package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.utils.StringUtil;

/**
 * Map Mapper
 */
@Component
public class MapsRowMapper implements RowMapper<Map<Object, Object>> {
	
	public Map<Object, Object> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final ResultSetMetaData metaData = rs.getMetaData();
		final HashMap<Object, Object> map = new HashMap<Object, Object>();
		for(int i = 1; i < metaData.getColumnCount() + 1; i++) {
	        map.put(StringUtil.capitalizeUnderScore(metaData.getColumnName(i)), rs.getObject(i));
		}
		return map;
	}
}

