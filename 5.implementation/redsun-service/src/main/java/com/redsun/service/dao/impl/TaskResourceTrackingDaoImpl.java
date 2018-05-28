package com.redsun.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TaskResourceTrackingDao;
import com.redsun.service.entities.TaskResourceTracking;

@Repository
public class TaskResourceTrackingDaoImpl implements TaskResourceTrackingDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] { 
																						
			java.sql.Types.INTEGER, // task_id
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.INTEGER, //actual-time
			java.sql.Types.INTEGER, //over_time
			java.sql.Types.DOUBLE, //coefficient
			java.sql.Types.VARCHAR, // owner
			java.sql.Types.INTEGER, // resource_id
			java.sql.Types.INTEGER, // quantity
			java.sql.Types.DOUBLE   // salary
	};

	private static String ADD_RESOURCE_TRACKING = "INSERT INTO task_resource_daily_tracking(task_id, project_id, actual_time, over_time, coefficient, date_log, owner, quantity, resource_id, salary) "
		    + "VALUES (?, ?, ?, ?, ?, 'now()'::date, ?, ?, ?, ?)";

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(TaskResourceTracking taskResourceTracking) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_RESOURCE_TRACKING, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(taskResourceTracking));
		return psc;
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(TaskResourceTracking taskResourceTracking) {
		return new Object[] { 
				taskResourceTracking.getTaskId(),
				taskResourceTracking.getProjectId(), 
				taskResourceTracking.getActualTime(), 
				taskResourceTracking.getOverTime(),
				taskResourceTracking.getCoefficient(),
				taskResourceTracking.getOwner(),
				taskResourceTracking.getQuantity(),
				taskResourceTracking.getResourceId(),
				taskResourceTracking.getSalary()
				};
	}
	//getValuesForPrimaryKey
		protected Object[] getValuesForPrimaryKey(TaskResourceTracking taskResourceTracking)  {
			return new Object[] {
					taskResourceTracking.getId() // id: java.lang.Integer
			};
		}
	public int insert(TaskResourceTracking taskResourceTracking) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(taskResourceTracking), keyHolder);

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


}