package com.redsun.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.ModulePropertyDao;
import com.redsun.dao.mapper.ModulePropertyRowMapper;
import com.redsun.entities.ModuleProperty;

@Repository
public class ModulePropertyDaoImpl implements ModulePropertyDao {
	private static Logger log = Logger.getLogger(ModulePropertyDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	private String GET_MODULE = "Select name, item, item_title from module_properties where name = ? order by name";
	@Override
	public List<ModuleProperty> gets(String moduleName) throws Exception {
		try {
			return jdbcTemplate.query(GET_MODULE, new Object[] { moduleName }, new ModulePropertyRowMapper());

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}


}
