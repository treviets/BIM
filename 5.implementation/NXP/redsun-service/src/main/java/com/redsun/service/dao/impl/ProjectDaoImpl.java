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

import com.redsun.service.dao.ProjectDao;
import com.redsun.service.dao.mapper.ProjectDetailMapper;
import com.redsun.service.dao.mapper.ProjectMapper;
import com.redsun.service.entities.Project;



@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	static private String SELECT_GETBYID =   "SELECT  p.id,  p.name, p.code, p.location, p.parent_id, p.description, p.code, " 
		       + "p.done_date, p.start_date, p.end_date, p.duration, p.work_sunday, p.closed_date, p.creation_date, p.last_update_date_time, p.update_by, " 
		       + "p.how_many_day_payment_delay, p.status_id, p.amount, update_by, " 
		       + "p.client_id, p.type_id, t.name AS type_name, "
		       + "p.customer_id, c.name AS customer_name, "
		       + "s.name AS status_name "
		       + "FROM projects AS p "
		       + "INNER JOIN types AS t ON p.type_id = t.id "
		       + "INNER JOIN clients AS c ON p.customer_id = c.id "
		       + "INNER JOIN statuses AS s ON p.status_id = s.id "
		       + "WHERE p.id = ?";
	static private String SELECT_ALL_PROJECT = "SELECT id, code, name, location, description, parent_id, client_id, start_date, amount, count(*) over() as total_count"
      +" FROM projects WHERE client_id = ? AND project_type = ? ORDER BY name";
	
	static private String SELECT_PROJECT_BY_RESOURCE = "SELECT p.id, p.code, p.name, p.location, p.description, p.parent_id, p.client_id, p.start_date, p.amount, count(*) over() as total_count " + 
			"FROM projects p LEFT JOIN project_resources pr on p.id = pr.project_id LEFT JOIN resources r on pr.resource_id = r.id " + 
			"WHERE r.code=?";
    
	static private String ADD_PROJECT = "INSERT INTO projects("
            +"name, location, description, customer_id, code, "
            +"parent_id, start_date, end_date, duration, work_sunday, closed_date, done_date, creation_date, "
            +"type_id, how_many_day_payment_delay, status_id, client_id, update_by, amount, project_type) " 
            +" VALUES (?, ?, ?, ?, ?, " 
            +"?, ?::date,?::date, ?, ?::bit, ?::date, ?::date,'now()'::date," 
           +" ?, ?, ?, ?, ?, ?, ?)";
	static private String DELETE_PROJECT ="DELETE FROM public.projects WHERE id = ?";
	private static Logger log = Logger.getLogger(ProjectDaoImpl.class);
	private final static String AUTO_INCREMENTED_COLUMN = "id";
	private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.VARCHAR, // name
			java.sql.Types.VARCHAR, // location
			java.sql.Types.VARCHAR, // description
			java.sql.Types.INTEGER, // customer_id
			java.sql.Types.VARCHAR, // code
			java.sql.Types.INTEGER, // parent_id
			java.sql.Types.DATE, //start_date
			java.sql.Types.DATE, //end_date
			java.sql.Types.INTEGER, //duration
			java.sql.Types.INTEGER, //work_sunday
			java.sql.Types.DATE, //closed_date
			java.sql.Types.DATE, // done_date
			java.sql.Types.INTEGER, // type_id
			java.sql.Types.VARCHAR, // how_many_day_payment_delay
			java.sql.Types.INTEGER, // status_id
			java.sql.Types.INTEGER, // client_id
			java.sql.Types.VARCHAR, // update_by
			java.sql.Types.DOUBLE, // amount.
			java.sql.Types.INTEGER // project_type

	};
	// Get Prepared Statement Creator.
		private PreparedStatementCreator getPreparedStatementCreator(final Project project) {
			PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_PROJECT, SQL_INSERT_TYPES);

			factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
			PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(project));
			return psc;
		}
		// Get Values For Insert.
		protected Object[] getValuesForInsert(final Project project) {
			return new Object[] { 
					project.getName(),
					project.getLocation(),
					project.getDescription(),
					project.getCustomerId(),
					project.getCode(),
					project.getParentId(),
					project.getStartDate(),
					project.getEndDate(),
					project.getDuration(),
					project.getWorkSunday(),
					project.getClosedDate(),
					project.getDoneDate(),
					project.getTypeId(),
					project.getHowManyDayPaymentDelay(),
					project.getStatusId(),
					project.getClientId(),
					project.getUpdateBy(),
					project.getAmount(),
					project.getProjectType()
					};
		}
		//getValuesForPrimaryKey
			protected Object[] getValuesForPrimaryKey(final Project project)  {
				return new Object[] {
					project.getId() // id: java.lang.Integer
				};
			}
		public int insert(final Project project) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			int result = jdbcTemplate.update(getPreparedStatementCreator(project), keyHolder);

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

	public Project getById(int projectId){
		Object[] primaryKey = new Object[] { projectId };
		try {
			return jdbcTemplate.queryForObject(SELECT_GETBYID, primaryKey, new ProjectDetailMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Project> listAllProjects(int clientId , Integer... projectType){
		int proType = 1;
		if(projectType != null && projectType.length > 0){
			proType = projectType[0];
		}
		
		List<Project> listProject = null;
		try {
			listProject = jdbcTemplate.query(SELECT_ALL_PROJECT, new Object[] { clientId, proType }, new ProjectMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProject;
	}
	
	//no done
	public List<Project> listProjects(int clientId, int itemsPerPage, int pageNo, Integer... projectType) {
		// TODO Auto-generated method stub
		List<Project> listProject = null;
		int offset = (pageNo-1)*itemsPerPage;
		int proType = 1;
		if(projectType != null && projectType.length > 0){
			proType = projectType[0];
		}
		String sql =  "SELECT id, name, code, location, description, client_id, parent_id, start_date, amount,  count(*) over() as total_count "
			       + "FROM projects WHERE client_id = ? AND project_type = ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(proType);
		params.add(itemsPerPage);
		params.add(offset);
		
		sql = sql + " ORDER BY id ASC limit ? offset ?";
		try {
			listProject = jdbcTemplate.query(sql, params.toArray(), new ProjectMapper());
			return listProject;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProject;
	}
	@Override
	public int update(Project project) {

		int result = 0;
		try {
			String UPDATE_PROJECT = "UPDATE projects "
				+ "SET name=?, description=?, customer_id=?, code=?, " 
				+ "parent_id=?, "
				+ "done_date=?::date, start_date=?::date, end_date=?::date, duration= ?, work_sunday=?::bit, closed_date=?::date, type_id=?, " 
				+ "last_update_date_time=?::date, " 
				+ "how_many_day_payment_delay=?, amount=?, status_id=?, client_id=?, update_by=? WHERE id = " + project.getId();

			result = jdbcTemplate.update(UPDATE_PROJECT,
					new Object[] {
							project.getName(),
							project.getDescription(),
							project.getCustomerId(),
							project.getCode(),
							project.getParentId(),
							project.getDoneDate(),
							project.getStartDate(),
							project.getEndDate(),
							project.getDuration(),
							project.getWorkSunday(),
							project.getClosedDate(),
							project.getTypeId(),
							"now()",
							project.getHowManyDayPaymentDelay(),
							project.getAmount(),
							project.getStatusId(),
							project.getClientId(),
							project.getUpdateBy()
							
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int projectId) {
		int result = jdbcTemplate.update(DELETE_PROJECT, new Object[] { projectId });
		return result;
	}
	@Override
	public List<Project> getByProjectId(int projectId) {
		List<Project> listProject = null;
		try {
			listProject = jdbcTemplate.query(SELECT_GETBYID, new Object[] { projectId }, new ProjectDetailMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProject;
	}
	
	@Override
	public List<Project> getProjectByResourceUsername(String username){
		List<Project> listProjects = null;
		try {
			listProjects = jdbcTemplate.query(SELECT_PROJECT_BY_RESOURCE, new Object[] { username }, new ProjectDetailMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjects;
	}
}
