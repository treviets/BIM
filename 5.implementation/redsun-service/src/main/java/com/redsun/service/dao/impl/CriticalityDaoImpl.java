package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.CriticalityDao;
import com.redsun.service.dao.mapper.CriticalityMapper;
import com.redsun.service.entities.Criticality;


@Repository
public class CriticalityDaoImpl implements CriticalityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(CriticalityDaoImpl.class);

	private static String INSERT_CRITICALITY = "INSERT INTO criticalities (name, value, color, sort_order, idle) VALUES (?, ?, ?, ?, ?::bit)";
	private static String DELETE_CRITICALITY = "DELETE FROM public.criticalities WHERE id = ?";
	private static String SELECT_ALL_CRITICALITY = "SELECT id, name, value, color, sort_order, idle, client_id FROM criticalities WHERE client_id = ?";
	@Override
	public List<Criticality> listCriticality(int itemsPerPage, int pageNo) {

		List<Criticality> listCriticality = null;
		int offset = (pageNo - 1) * itemsPerPage;
		String SELECT_CRITICALITY = "SELECT id, name, value, color, sort_order, idle, client_id FROM criticalities ";
		List<Object> params = new ArrayList<Object>();
		params.add(itemsPerPage);
		params.add(offset);

		SELECT_CRITICALITY = SELECT_CRITICALITY + " order by id ASC limit ? offset ?";
		try {
			listCriticality = jdbcTemplate.query(SELECT_CRITICALITY, params.toArray(), new CriticalityMapper());
			return listCriticality;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCriticality;
	}

	@Override
	public int insert(Criticality criticality) {

		int result = 0;
		try {
			result = jdbcTemplate.update(INSERT_CRITICALITY,
					new Object[] {
							criticality.getName(),
							criticality.getValue(), 
							criticality.getColor(),
							criticality.getSortOrder(),
							criticality.getIdle()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int update(Criticality criticality) {
		int result = 0;
		try {
			String UPDATE_CRITICALITY = "UPDATE public.criticalities"
					+ " SET name=?, value=?, color=?, sort_order=?, idle=?::bit WHERE id = "+criticality.getId();

			result = jdbcTemplate.update(UPDATE_CRITICALITY,
					new Object[] {
							criticality.getName(),
							criticality.getValue(), 
							criticality.getColor(),
							criticality.getSortOrder(),
							criticality.getIdle()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
	@Override
	public int delete(Integer id) {
		int result = jdbcTemplate.update(DELETE_CRITICALITY, new Object[]{id});
		return result;
	}

	@Override
	public List<Criticality> listAllCriticalities(int clientId) {

		List<Criticality> listCriticality = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);

		try {
			listCriticality = jdbcTemplate.query(SELECT_ALL_CRITICALITY, params.toArray(), new CriticalityMapper());
			return listCriticality;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCriticality;
	}

}
