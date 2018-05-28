package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.LikelihoodDao;
import com.redsun.service.dao.mapper.LikelihoodMapper;
import com.redsun.service.entities.Likelihood;

@Repository
public class LikelihoodDaoImpl implements LikelihoodDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(LikelihoodDaoImpl.class);

	private static String INSERT_LIKELIHOOD = "INSERT INTO likelihoods (name, value, color, sort_order, idle, value_pct) VALUES (?, ?, ?, ?, ?::bit, ?)";
	private static String DELETE_LIKELIHOOD = "DELETE FROM public.likelihoods WHERE id = ?";
	private static String SELECT_ALL_LIKELIHOOD = "SELECT id, name, value, color, sort_order, idle, value_pct, client_id FROM likelihoods WHERE client_id = ?";
			
	@Override
	public List<Likelihood> listLikelihood(int itemsPerPage, int pageNo) {

		List<Likelihood> listLikelihood = null;
		int offset = (pageNo - 1) * itemsPerPage;
		String SELECT_LIKELIHOOD = "SELECT id, name, value, color, sort_order, idle, value_pct FROM likelihoods ";
		List<Object> params = new ArrayList<Object>();
		params.add(itemsPerPage);
		params.add(offset);

		SELECT_LIKELIHOOD = SELECT_LIKELIHOOD + " order by id ASC limit ? offset ?";
		try {
			listLikelihood = jdbcTemplate.query(SELECT_LIKELIHOOD, params.toArray(), new LikelihoodMapper());
			return listLikelihood;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listLikelihood;
	}

	@Override
	public int insert(Likelihood likelihood) {

		int result = 0;
		try {
			result = jdbcTemplate.update(INSERT_LIKELIHOOD,
					new Object[] {
							likelihood.getName(),
							likelihood.getValue(), 
							likelihood.getColor(),
							likelihood.getSortOrder(),
							likelihood.getIdle(),
							likelihood.getValuePct()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int update(Likelihood likelihood) {
		int result = 0;
		try {
			String UPDATE_LIKELIHOOD = "UPDATE public.likelihoods"
					+ " SET name=?, value=?, color=?, sort_order=?, idle=?::bit, value_pct=? WHERE id = "+likelihood.getId();

			result = jdbcTemplate.update(UPDATE_LIKELIHOOD,
					new Object[] {
							likelihood.getName(),
							likelihood.getValue(), 
							likelihood.getColor(),
							likelihood.getSortOrder(),
							likelihood.getIdle(),
							likelihood.getValuePct()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
	@Override
	public int delete(Integer id) {
		int result = jdbcTemplate.update(DELETE_LIKELIHOOD, new Object[]{id});
		return result;
	}

	@Override
	public List<Likelihood> listAllLikelihood(int clientId) {

		List<Likelihood> listLikelihood = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listLikelihood = jdbcTemplate.query(SELECT_ALL_LIKELIHOOD,params.toArray(), new LikelihoodMapper());
			return listLikelihood;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listLikelihood;
	}


}
