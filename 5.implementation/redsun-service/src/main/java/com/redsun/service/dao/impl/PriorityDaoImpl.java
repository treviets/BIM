package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.PriorityDao;
import com.redsun.service.dao.mapper.PriorityMapper;
import com.redsun.service.entities.Priority;

@Repository
public class PriorityDaoImpl implements PriorityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(PriorityDaoImpl.class);

	private static String INSERT_PRIORITY = "INSERT INTO priorities (name, value, color, sort_order, idle, client_id) VALUES (?, ?, ?, ?, ?::bit, ?)";
	private static String DELETE_PRIORITY = "DELETE FROM public.priorities WHERE id = ? and client_id=1";
	private static String SELECT_PRIORITY_TOFIND = "SELECT id, name, value, color, sort_order, idle, client_id, count(*) over() as totalCount FROM priorities WHERE id =?";
	private static String SELECT_ALL_PRIORITY = "SELECT id, name, value, color, sort_order, idle, client_id, count(*) over() as totalCount FROM priorities WHERE client_id = ?";

			
	@Override
	public List<Priority> listPriority(int client_id, String name, int itemsPerPage, int pageNo) {
		
		List<Priority> listPriority = null;
		int offset = (pageNo - 1) * itemsPerPage;
		String SELECT_PRIORITY = "SELECT id, name, value, color, sort_order, idle, client_id,  count(*) over() as totalCount FROM priorities WHERE client_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(client_id);
		params.add(itemsPerPage);
		params.add(offset);
		
		if(name.equals("undefined")==false){
			SELECT_PRIORITY += " and UPPER(name) like UPPER(?)";
			params.add("%" + name + "%");
		}
		
		SELECT_PRIORITY = SELECT_PRIORITY + " order by id ASC limit ? offset ?";
		try {
			listPriority = jdbcTemplate.query(SELECT_PRIORITY, params.toArray(), new PriorityMapper());
			return listPriority;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listPriority;
	}

	@Override
	public int insert(Priority priority) {
		int result = 0;
		try {
			result = jdbcTemplate.update(INSERT_PRIORITY,
					new Object[] {
							priority.getName(),
							priority.getValue(), 
							priority.getColor(),
							priority.getSortOrder(),
							priority.getIdle(),
							priority.getClient_id()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int update(Priority priority) {
		int result = 0;
		try {
			String UPDATE_PRIORITY = "UPDATE public.priorities"
					+ " SET name=?, value=?, color=?, sort_order=?, idle=?::bit WHERE id = "+ priority.getId();

			result = jdbcTemplate.update(UPDATE_PRIORITY,
					new Object[] {
							priority.getName(),
							priority.getValue(), 
							priority.getColor(),
							priority.getSortOrder(),
							priority.getIdle(),
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
	@Override
	public int delete(Integer id) {
		int result = jdbcTemplate.update(DELETE_PRIORITY, new Object[]{id});
		return result;
	}

	@Override
	public Priority getById(Integer id) {
		Object[] primaryKey = new Object[] { id };
		try {
			return jdbcTemplate.queryForObject(SELECT_PRIORITY_TOFIND, primaryKey, new PriorityMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Priority> listAllPriories(int clientId) {

		List<Priority> listPriority = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);

		try {
			listPriority = jdbcTemplate.query(SELECT_ALL_PRIORITY, params.toArray(), new PriorityMapper());
			return listPriority;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listPriority;
	}



}
