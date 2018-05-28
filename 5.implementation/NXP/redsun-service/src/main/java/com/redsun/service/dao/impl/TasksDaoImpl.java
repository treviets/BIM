package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TasksDao;
import com.redsun.service.dao.mapper.TaskRowMapper;
import com.redsun.service.entities.Task;
import com.redsun.service.utils.RedSunConstants;

@Repository
public class TasksDaoImpl implements TasksDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(TasksDaoImpl.class);

	private static String ADD_TASK = "INSERT INTO tasks("
            + "task_name, wbs, description, parent_id, id_affectation, responsible, "
            + "task_owner, start_date, end_date, estimate_time," 
            + "actually_time, project_id, status_id, priority_id, type_id, completed, comment, is_trash, client_id)"
            +" VALUES (?, ?, ?, ?, ?, ?," 
            +" ?, ?::date, ?::date, ?," 
            +"?, ?, ?, ?, ?, ?, ?, ?::bit, ?)";
	private static String DELETE_TASK = "DELETE FROM public.tasks WHERE id = ?";
	private final static String AUTO_INCREMENTED_COLUMN = "id";
	private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.VARCHAR, // name
			java.sql.Types.VARCHAR, //wbs
			java.sql.Types.VARCHAR, // description
			java.sql.Types.VARCHAR, // parent_id
			java.sql.Types.INTEGER, // affectation_id
			java.sql.Types.INTEGER, // responsible
			java.sql.Types.VARCHAR, // task_owner
			java.sql.Types.DATE, //start_date
			java.sql.Types.DATE, //end_date
			java.sql.Types.INTEGER, // estimate_time
			java.sql.Types.INTEGER, // actual_time
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.INTEGER, // status_id
			java.sql.Types.INTEGER, // priority_id
			java.sql.Types.INTEGER, // type_id
			java.sql.Types.INTEGER, // completed
			java.sql.Types.VARCHAR, // comment
			java.sql.Types.INTEGER, // is_trash
			java.sql.Types.INTEGER // client_id

	};
	// Get Prepared Statement Creator.
		private PreparedStatementCreator getPreparedStatementCreator(final Task task) {
			PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_TASK, SQL_INSERT_TYPES);

			factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
			PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(task));
			return psc;
		}
		// Get Values For Insert.
		protected Object[] getValuesForInsert(final Task task) {
			return new Object[] { 
					task.getTaskName(),
					task.getWbs(),
					task.getDescription(),
					task.getParentId(),
					task.getAffectationId(),
					task.getResponsible(),
					task.getTaskOwner(),
					task.getStartDate(),
					task.getEndDate(),
					task.getEstimateTime(),
					task.getActuallyTime(), 
					task.getProjectId(),
					task.getStatusId(),
					task.getPriorityId(),
					task.getTypeId(),
					task.getCompleted(),
					task.getComment(),
					task.getIsTrash(),
					task.getClientId()
					};
		}
		//getValuesForPrimaryKey
			protected Object[] getValuesForPrimaryKey(final Task task)  {
				return new Object[] {
						task.getId() // id: java.lang.Integer
				};
			}
		public int insert(final Task task) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			int result = jdbcTemplate.update(getPreparedStatementCreator(task), keyHolder);

			if (result != 1) {
				throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
			}

			//update total duration for parent task
			//autoUpdateParentTask(task);
			
			Number key = keyHolder.getKey();
			if (key != null) {
				return key.intValue();
			} else {
				throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
			}
		}

	@Override
	public List<Task> listTask(int clientId, int itemsPerPage, int pageNo) {
		List<Task> listTask = null;
		int offset = (pageNo - 1) * itemsPerPage;
		String SELECT_TASK = "SELECT id, task_name, wbs, description, parent_id, responsible, " 
	            	+ "id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time, "
	            	+ "actually_time, project_id, status_id, priority_id, type_id, client_id, "
					+ "count(*) over() AS total_count, completed, comment, is_trash " 
					+ "FROM tasks "
					+ "WHERE client_id = ? AND is_trash = '0' ";

		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(itemsPerPage);
		params.add(offset);

		SELECT_TASK = SELECT_TASK + " ORDER BY id ASC limit ? offset ?";

		try {
			listTask = jdbcTemplate.query(SELECT_TASK, params.toArray(), new TaskRowMapper());
			return listTask;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTask;
	}

	@Override
	public int update(Task task) {
		synchronized (task) {
			int result = 0;
			try {
				String UPDATE_TASK = "UPDATE tasks "
						+"SET task_name=?, description=?, parent_id=?, id_affectation=?, " 
						+"responsible=?, task_owner=?, start_date=?::date, end_date=?::date, create_date=?::date," 
						+"update_date=?::date, estimate_time=?, actually_time=?, project_id=?," 
						+"status_id=?, priority_id=?, type_id=?, completed=?, comment=?, is_trash=?::bit, client_id=? WHERE id = ?";

				result = jdbcTemplate.update(UPDATE_TASK,
						new Object[] {
								task.getTaskName(),
								task.getDescription(),
								task.getParentId(),
								task.getAffectationId(),
								task.getResponsible(),
								task.getTaskOwner(),
								task.getStartDate(),
								task.getEndDate(),
								task.getCreateDate(),
								"now()",
								task.getEstimateTime(),
								task.getActuallyTime(), 
								task.getProjectId(),
								task.getStatusId(),
								task.getPriorityId(),
								task.getTypeId(),
								task.getCompleted(),
								task.getComment(),
								task.getIsTrash(),
								task.getClientId(),
								task.getId()
								});
				if(result > 0 && task.getComment() != null && task.getComment().length() > 0){
					String INSERT_COMMENT = "INSERT INTO COMMENTS (id_task, comment_by, content, create_date_time )"
							+ " VALUES (?, ?, ?, 'now'::date)";
					result = jdbcTemplate.update(INSERT_COMMENT, new Object[]{
							task.getId(),
							task.getTaskOwner(),
							task.getComment()
					});
				}
				/*update all sub task to completed if its parent is completed
				if(task.getCompleted() == 100){
					autoUpdateSubTask(task);
				}*/
				
				//update parent task in case this is a sub task
				autoUpdateParentTask(task);
				
				return result;
			} catch (Exception e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		
	}
	
	
	/*
	 * This method service for update status of all sub task to 100% (completed) once their parent task was 100%(completed)
	 */
	private void autoUpdateParentTask(Task task){
		Task parentTask  = getParentTask(task.getParentId());
		if(parentTask != null){
			double totalParentDuration = getAllDurationOfParent(parentTask.getWbs());
			List<Task> listOfSubTasks = listsSubTasks(parentTask.getWbs());
			int newCompleted = 0;
			
			for(Task sub: listOfSubTasks){
				double curentTaskDuration = sub.getEstimateTime();
				double percentOfCurrentTaskInParentTask = ((curentTaskDuration * 100)/totalParentDuration)/100;
				double percentCompleteOfCurrentTaskInParentTask = percentOfCurrentTaskInParentTask * sub.getCompleted();
				String percentCompleteInString = String.valueOf(percentCompleteOfCurrentTaskInParentTask);
				newCompleted += Integer.valueOf(percentCompleteInString.substring(0, percentCompleteInString.lastIndexOf(".")));
			}
			
			//parentTask.setEstimateTime(totalParentDuration);
			parentTask.setCompleted(newCompleted);
			update(parentTask);
		}
	}
	
	/*
	 * This method service for update status of all sub task to 100% (completed) once their parent task was 100%(completed)
	 */
	private void autoUpdateSubTask(Task task){
		String wbs = task.getWbs();
		if(wbs != null){
			//get sub tasks
			List<Task> listSubTasks = listsSubTasks(wbs);
			for(Task subTask : listSubTasks){
				subTask.setCompleted(100);
				update(subTask);
			}
		}
	}
	
	@Override
	public int delete(int id) {
		int result = jdbcTemplate.update(DELETE_TASK, new Object[] { id });
		return result;
	}

	@Override
	public Task getById(int id) {
		Object[] primaryKey = new Object[] { id };
		String SELECT_GETBYID =  "SELECT id, task_name, wbs, description, parent_id, responsible, " 
            	+ "id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time, "
            	+ "actually_time, project_id, status_id, priority_id, type_id, client_id, "
				+ "count(*) over() AS total_count, completed, comment, is_trash " 
				+ "FROM tasks "
				+ "WHERE id =? ";
		try {
			return jdbcTemplate.queryForObject(SELECT_GETBYID, primaryKey, new TaskRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	private double getAllDurationOfParent(String parentId) {
		double duration = 0;
		
		String SELECT_TASK_ONE_PARENT = "SELECT SUM(estimate_time) as duration " 
				+ "FROM tasks "
				+ "WHERE parent_id = ? AND is_trash = '0'";

		List<Object> params = new ArrayList<Object>();
		params.add(parentId);
		try {
			duration = jdbcTemplate.queryForObject(SELECT_TASK_ONE_PARENT, params.toArray(), Double.class);
			return duration;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return duration;
	}
	
	private List<Task> listsSubTasks(String parentId) {
		List<Task> listTask = null;
		String SELECT_TASK_ONE_PARENT = "SELECT id, task_name, wbs, description, parent_id, responsible, " 
            	+ "id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time, "
            	+ "actually_time, project_id, status_id, priority_id, type_id, client_id, "
				+ "count(*) over() AS total_count, completed, comment, is_trash " 
				+ "FROM tasks "
				+ "WHERE parent_id = ? AND is_trash = '0'";

		List<Object> params = new ArrayList<Object>();
		params.add(parentId);
		try {
			listTask = jdbcTemplate.query(SELECT_TASK_ONE_PARENT, params.toArray(), new TaskRowMapper());
			return listTask;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTask;
	}
	
	private Task getParentTask(String parentId) {
		List<Task> listTask = null;
		String SELECT_PARENT_TASK = "SELECT id, task_name, wbs, description, parent_id, responsible, " 
            	+ "id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time, "
            	+ "actually_time, project_id, status_id, priority_id, type_id, client_id, "
				+ "count(*) over() AS total_count, completed, comment, is_trash " 
				+ "FROM tasks "
				+ "WHERE wbs = ? AND is_trash = '0'";

		List<Object> params = new ArrayList<Object>();
		params.add(parentId);
		try {
			listTask = jdbcTemplate.query(SELECT_PARENT_TASK, params.toArray(), new TaskRowMapper());
			if(listTask != null && listTask.size() > 0){
				return listTask.get(0);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Task> listTaskOfOneProject(int clientId, int projectId, int resourceId, String userName) {
		List<Task> listTask = null;
		String SELECT_TASK_ONE_PROJECT = "SELECT id, task_name, wbs, description, parent_id, responsible, " 
            	+ "id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time, "
            	+ "actually_time, project_id, status_id, priority_id, type_id, client_id, "
				+ "count(*) over() AS total_count, completed, comment, is_trash " 
				+ "FROM tasks "
				+ "WHERE client_id=? AND project_id = ? AND is_trash = '0'";

		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(projectId);

		if(RedSunConstants.ADMIN_USER.equals(userName)){
			SELECT_TASK_ONE_PROJECT += " ORDER BY id, start_date";
		}
		try {
			listTask = jdbcTemplate.query(SELECT_TASK_ONE_PROJECT, params.toArray(), new TaskRowMapper());
			return listTask;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTask;
	}
	

	//----------------------------------------------
	// 4D: TODO: Replace named from wbs to code.
	//----------------------------------------------
	
	
	private final static String SQL_DELETE_BY_PROJECT_ID_AND_CODE = "UPDATE tasks SET is_trash = 1::bit WHERE project_id = ? AND wbs = ?";
	private final static String SQL_COUNT_BY_PROJECT_ID_AND_CODE = "SELECT count(*) FROM tasks WHERE project_id = ? AND wbs = ?";
	private final static String SQL_SELECT_LIST_OF_TASKS_BY_PROJECT_ID_AND_CODES = 
			"SELECT id, task_name, wbs, description, parent_id, responsible, " 
	            	+ "id_affectation, task_owner, start_date, end_date, create_date, update_date, estimate_time, "
	            	+ "actually_time, project_id, status_id, priority_id, type_id, client_id, "
					+ "count(*) over() AS total_count, completed, comment, is_trash " 
					+ "FROM tasks "
					+ "WHERE project_id = :project_id AND wbs IN (:codes)";
	
	
	// Delete by project id and code.
	public Integer deleteByProjectIdAndCode(Integer projectId, String code) {
		Object[] params = new Object[] { projectId, code };
		Integer result = jdbcTemplate.update(SQL_DELETE_BY_PROJECT_ID_AND_CODE, params);
		return result;
	}

	// Count by project id and code.
	public Integer countByProjectIdAndCode(Integer projectId, String code) {
		Object[] params = new Object[] { projectId, code };
		Integer result = jdbcTemplate.queryForObject(SQL_COUNT_BY_PROJECT_ID_AND_CODE, params, Integer.class);
		return result;
	}

	// List of tasks by project id and codes.
	public List<Task> listTasksByProjectIdAndCodes(Integer projectId, List<String> codes) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("project_id", projectId);
		params.addValue("codes", codes);
		List<Task> result = namedParameterJdbcTemplate.query(SQL_SELECT_LIST_OF_TASKS_BY_PROJECT_ID_AND_CODES, params, new TaskRowMapper());
		return result;
	}
	
}