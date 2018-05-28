package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.RiskDao;
import com.redsun.service.dao.mapper.RiskModelViewMapper;
import com.redsun.service.entities.Risk;

@Repository
public class RiskDaoImpl implements RiskDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(RiskDaoImpl.class);
	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] { 
																						
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.VARCHAR, // name
			java.sql.Types.VARCHAR, // description
			java.sql.Types.INTEGER, // type_id
			java.sql.Types.VARCHAR, // cause
			java.sql.Types.VARCHAR, // impact
			java.sql.Types.INTEGER, // severity_id
			java.sql.Types.INTEGER, // likelihood_id
			java.sql.Types.INTEGER, // criticality_id
			java.sql.Types.INTEGER, // status_id
			java.sql.Types.DATE, // planning_end_date
			java.sql.Types.DATE, // actual_end_date
			java.sql.Types.VARCHAR, // result
			java.sql.Types.DATE, // done_date
			java.sql.Types.DATE, // handled_date
			java.sql.Types.INTEGER, // priority_id
			java.sql.Types.NUMERIC, // impact_cost
			java.sql.Types.VARCHAR,	//update_by
			java.sql.Types.INTEGER // client_id
	};

	private static String ADD_RISK = "insert into risks("
			+ "project_id, name, description, type_id, cause, impact, severity_id,"
			+ "likelihood_id, criticality_id, creation_date, status_id, "
			+ "planning_end_date, actual_end_date, result, done_date, "
			+ "handled_date, priority_id, impact_cost, "
			+ "update_by, client_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'now()'::date, ?, "
			+ "?::date, ?::date, ?, ?::date, "
			+ "?::date, ?, ?, "
			+ "?, ?)";
	private static String DELETE_RISK = "DELETE FROM public.risks WHERE id = ?";

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Risk risk) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_RISK, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(risk));
		return psc;
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Risk risk) {
		return new Object[] { 
				risk.getProjectId(), 
				risk.getName(),
				risk.getDescription(),
				risk.getRiskTypeId(),
				risk.getCause(),
				risk.getImpact(), 
				risk.getSeverityId(), 
				risk.getLikelihoodId(),
				risk.getCriticalityId(), 
				risk.getStatusId(),
				risk.getPlanningEndDate(),
				risk.getActualEndDate(), 
				risk.getResult(),
				risk.getDoneDate(),
				risk.getHandledDate(), 
				risk.getPriorityId(), 
				risk.getImpactCost(), 
				risk.getUpdateBy(),
				risk.getClientId()
				};
	}
	//getValuesForPrimaryKey
		protected Object[] getValuesForPrimaryKey(final Risk risk)  {
			return new Object[] {
				risk.getId() // id: java.lang.Integer
			};
		}
	public int insert(final Risk risk) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(risk), keyHolder);

		if (result != 1) {
			throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
		}

		Number key = keyHolder.getKey();
		if (key != null) {
			return key.intValue();
		} else {
			throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
		}
	}

	@Override
	public List<Risk> listRisk(int clientId, int itemsPerPage, int pageNo) {
		List<Risk> listRisk = null;
		int offset = (pageNo - 1) * itemsPerPage;
		String SELECT_RISK = "SELECT r.id, r.name, r.client_id, "
				+ "r.project_id, r.type_id, r.severity_id, r.likelihood_id, r.criticality_id, r.status_id, "
				+ "r.priority_id, r.update_date, r.update_by, r.description, r.cause, r.result, "
				+ "r.impact, r.impact_cost, r.creation_date, r.planning_end_date, r.actual_end_date, "
				+ "p.name AS project_name, types.name AS type_name, s.name AS severity_name, l.name AS likelihood_name, r.done_date, r.handled_date,  "
				+ "c.name AS criticality_name, statuses.name AS status_name, priorities.name AS priority_name, "
				+ "count(*) over() AS totalCount "
				+ "FROM risks AS r "
				+ "INNER JOIN projects AS p ON r.project_id = p.id "
				+ "INNER JOIN types ON r.type_id = types.id "
				+ "INNER JOIN severities AS s ON r.severity_id = s.id "
				+ "INNER JOIN likelihoods AS l ON r.likelihood_id = l.id "
				+ "INNER JOIN criticalities AS c ON r.criticality_id = c.id "
				+ "INNER JOIN statuses ON r.status_id = statuses.id "
				+ "INNER JOIN priorities ON r.priority_id = priorities.id "
				+ "WHERE p.client_id=? ";

		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(itemsPerPage);
		params.add(offset);

		SELECT_RISK = SELECT_RISK + " order by r.id ASC limit ? offset ?";

		try {
			listRisk = jdbcTemplate.query(SELECT_RISK, params.toArray(), new RiskModelViewMapper());
			return listRisk;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listRisk;
	}

	@Override
	public int update(Risk risk) {

		int result = 0;
		try {
			String UPDATE_RISK = "UPDATE risks "
					+ "SET project_id=?, name=?, description=?, type_id=?, cause=?, " 
					+ "impact=?, severity_id=?, likelihood_id=?, criticality_id=?, " 
					+ "status_id=?, planning_end_date=?, actual_end_date=?, " 
					+ "result=?, done_date=?::date," 
					+ "handled_date=?::date, " 
					+ "priority_id=?, impact_cost=?, " 
					+ "update_date=?::date, update_by=? WHERE id = " + risk.getId();

			result = jdbcTemplate.update(UPDATE_RISK,
					new Object[] {
							risk.getProjectId(), 
							risk.getName(),
							risk.getDescription(),
							risk.getRiskTypeId(),
							risk.getCause(),
							risk.getImpact(), 
							risk.getSeverityId(), 
							risk.getLikelihoodId(),
							risk.getCriticalityId(), 
							risk.getStatusId(),
							risk.getPlanningEndDate(),
							risk.getActualEndDate(), 
							risk.getResult(),
							risk.getDoneDate(),
							risk.getHandledDate(), 
							risk.getPriorityId(), 
							risk.getImpactCost(), 
							"now()",
							risk.getUpdateBy()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(Integer id) {
		int result = jdbcTemplate.update(DELETE_RISK, new Object[] { id });
		return result;
	}

	@Override
	public Risk getById(Integer id) {
		Object[] primaryKey = new Object[] { id };
		String SELECT_GETBYID =  "SELECT r.id, r.name, r.client_id, "
				+ "r.project_id, r.type_id, r.severity_id, r.likelihood_id, r.criticality_id, r.status_id, "
				+ "r.priority_id, r.update_date, r.update_by, r.description, r.cause, r.result, "
				+ "r.impact, r.impact_cost, r.creation_date, r.planning_end_date, r.actual_end_date, "
				+ "p.name AS project_name, types.name AS type_name, s.name AS severity_name, l.name AS likelihood_name, r.done_date, r.handled_date,  "
				+ "c.name AS criticality_name, statuses.name AS status_name, priorities.name AS priority_name, "
				+ "count(*) over() AS totalCount "
				+ "FROM risks AS r "
				+ "INNER JOIN projects AS p ON r.project_id = p.id "
				+ "INNER JOIN types ON r.type_id = types.id "
				+ "INNER JOIN severities AS s ON r.severity_id = s.id "
				+ "INNER JOIN likelihoods AS l ON r.likelihood_id = l.id "
				+ "INNER JOIN criticalities AS c ON r.criticality_id = c.id "
				+ "INNER JOIN statuses ON r.status_id = statuses.id "
				+ "INNER JOIN priorities ON r.priority_id = priorities.id "
				+ "WHERE r.id =? ";
		try {
			return jdbcTemplate.queryForObject(SELECT_GETBYID, primaryKey, new RiskModelViewMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Risk> listRiskOneProject(int clientId, int projectId) {
		List<Risk> listRisk = null;
		String SELECT_RISK =  "SELECT r.id, r.name, r.client_id, "
				+ "r.project_id, r.type_id, r.severity_id, r.likelihood_id, r.criticality_id, r.status_id, "
				+ "r.priority_id, r.update_date, r.update_by, r.description, r.cause, r.result, "
				+ "r.impact, r.impact_cost, r.creation_date, r.planning_end_date, r.actual_end_date, "
				+ "p.name AS project_name, types.name AS type_name, s.name AS severity_name, l.name AS likelihood_name, r.done_date, r.handled_date,  "
				+ "c.name AS criticality_name, statuses.name AS status_name, priorities.name AS priority_name, "
				+ "count(*) over() AS totalCount "
				+ "FROM risks AS r "
				+ "INNER JOIN projects AS p ON r.project_id = p.id "
				+ "INNER JOIN types ON r.type_id = types.id "
				+ "INNER JOIN severities AS s ON r.severity_id = s.id "
				+ "INNER JOIN likelihoods AS l ON r.likelihood_id = l.id "
				+ "INNER JOIN criticalities AS c ON r.criticality_id = c.id "
				+ "INNER JOIN statuses ON r.status_id = statuses.id "
				+ "INNER JOIN priorities ON r.priority_id = priorities.id "
				+ "WHERE r.client_id = ? AND r.project_id = ?";

		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(projectId);

		try {
			listRisk = jdbcTemplate.query(SELECT_RISK, params.toArray(), new RiskModelViewMapper());
			return listRisk;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listRisk;
	}

}