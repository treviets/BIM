package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.SeverityDao;
import com.redsun.service.dao.mapper.SeverityMapper;
import com.redsun.service.entities.Severity;


@Repository
public class SeverityDaoImpl implements SeverityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(LikelihoodDaoImpl.class);

	private static String INSERT_SEVERITY = "INSERT INTO severities (name, value, color, sort_order, idle, client_id) VALUES (?, ?, ?, ?, ?::bit, ?)";
	private static String DELETE_SEVERITY = "DELETE FROM public.severities WHERE id = ?";
	private static String SELECT_ALL_SEVERITY = "SELECT id, name, value, color, sort_order, idle, client_id FROM severities WHERE client_id = ? ";
	@Override
	public List<Severity> listSeverity(int itemsPerPage, int pageNo) {

		List<Severity> listSeverity = null;
		int offset = (pageNo - 1) * itemsPerPage;
		String SELECT_SEVERITY = "SELECT id, name, value, color, sort_order, idle, client_id FROM severities ";
		List<Object> params = new ArrayList<Object>();
		params.add(itemsPerPage);
		params.add(offset);

		SELECT_SEVERITY = SELECT_SEVERITY + " order by id ASC limit ? offset ?";
		try {
			listSeverity = jdbcTemplate.query(SELECT_SEVERITY, params.toArray(), new SeverityMapper());
			return listSeverity;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listSeverity;
	}

	@Override
	public int insert(Severity severity) {

		int result = 0;
		try {
			result = jdbcTemplate.update(INSERT_SEVERITY,
					new Object[] {
							severity.getName(),
							severity.getValue(), 
							severity.getColor(),
							severity.getSortOrder(),
							severity.getIdle()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int update(Severity severity) {
		int result = 0;
		try {
			String UPDATE_SEVERITY = "UPDATE public.severities"
					+ " SET name=?, value=?, color=?, sortorder=?, idle=?::bit WHERE id = "+severity.getId();

			result = jdbcTemplate.update(UPDATE_SEVERITY,
					new Object[] {
							severity.getName(),
							severity.getValue(), 
							severity.getColor(),
							severity.getSortOrder(),
							severity.getIdle()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
	@Override
	public int delete(Integer id) {
		int result = jdbcTemplate.update(DELETE_SEVERITY, new Object[]{id});
		return result;
	}

	@Override
	public List<Severity> listAllSeverities(int clientId) {

		List<Severity> listSeverity = null;

		try {
			listSeverity = jdbcTemplate.query(SELECT_ALL_SEVERITY,  new Object[] { clientId }, new SeverityMapper());
			return listSeverity;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listSeverity;
	}

}
