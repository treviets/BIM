package com.redsun.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TaskMaterialTrackingDao;
import com.redsun.service.entities.TaskMaterialTracking;

@Repository
public class TaskMaterialTrackingDaoImpl implements TaskMaterialTrackingDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] { 
																						
			java.sql.Types.INTEGER, // task_id
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.DOUBLE, //actual_material
			java.sql.Types.VARCHAR, // owner
			java.sql.Types.INTEGER, // material_id
			java.sql.Types.DOUBLE, //net_price
			java.sql.Types.DOUBLE //price
	};

	private static String ADD_RESOURCE_TRACKING = "INSERT INTO task_material_daily_tracking(task_id, project_id, actual_material, date_log, owner, material_id, net_price, price) "
		    + "VALUES (?, ?, ?, 'now()'::date, ?, ?, ?, ?)";

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(TaskMaterialTracking taskMaterialTracking) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_RESOURCE_TRACKING, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(taskMaterialTracking));
		return psc;
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(TaskMaterialTracking taskMaterialTracking) {
		return new Object[] { 
				taskMaterialTracking.getTaskId(),
				taskMaterialTracking.getProjectId(),
				taskMaterialTracking.getActualMaterial(),
				taskMaterialTracking.getOwner(),
				taskMaterialTracking.getMaterialId(),
				taskMaterialTracking.getNetPrice(),
				taskMaterialTracking.getPrice()
				};
	}
	//getValuesForPrimaryKey
		protected Object[] getValuesForPrimaryKey(TaskMaterialTracking taskMaterialTracking)  {
			return new Object[] {
					taskMaterialTracking.getId() // id: java.lang.Integer
			};
		}
	public int insert(TaskMaterialTracking taskMaterialTracking) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(taskMaterialTracking), keyHolder);

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