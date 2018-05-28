package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ResourceDao;
import com.redsun.service.dao.mapper.ResourceMapper;
import com.redsun.service.entities.Resource;

@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_ALL_RESOURCES = "SELECT id, code, name, full_name, email, description, address, phone, client_id, position_id, count(*) over() AS total_count FROM resources WHERE client_id = ? ORDER BY name";
	static private String SELECT_RESOURCE_BY_ID = "SELECT id, code, name, full_name, email, description, address, phone, client_id, position_id, count(*) over() AS total_count FROM resources WHERE id = ?";
	private static Logger log = Logger.getLogger(ResourceDaoImpl.class);

	public List<Resource> listResources(int clientId, String code, String name, int itemsPerPage, int pageNo) {
		// TODO Auto-generated method stub
		List<Resource> listResource = null;
		int offset = (pageNo-1)*itemsPerPage;
		String sql = "SELECT id, code, name, address, phone, client_id, count(*) over() AS total_count"
				+ " FROM resources WHERE client_id = ?";//
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
		params.add(itemsPerPage);
		params.add(offset);
		
		sql = sql + " order by id ASC limit ? offset ?";
		try {
			listResource = jdbcTemplate.query(sql, params.toArray(), new ResourceMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listResource;
	}
	public List<Resource> listResources(int clientId) {
		// TODO Auto-generated method stub
		List<Resource> listResource = null;
		try {
			listResource = jdbcTemplate.query(SELECT_ALL_RESOURCES, new Object[] { clientId }, new ResourceMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listResource;
	}
	public List<Resource> getResourceDetails(int id) {
		List<Resource> resources = null;
		try {
			resources = jdbcTemplate.query(SELECT_RESOURCE_BY_ID, new Object[] { id }, new ResourceMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resources;
	}


}
