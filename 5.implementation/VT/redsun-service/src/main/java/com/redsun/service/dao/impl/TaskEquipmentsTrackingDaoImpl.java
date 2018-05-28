package com.redsun.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TaskEquipmentsTrackingDao;
import com.redsun.service.entities.TaskEquipments;

@Repository
public class TaskEquipmentsTrackingDaoImpl implements TaskEquipmentsTrackingDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] { 
																						
			java.sql.Types.INTEGER, // task_id
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.INTEGER, //actual-time
			java.sql.Types.VARCHAR, // owner
			java.sql.Types.INTEGER, // quantity
			java.sql.Types.INTEGER, // equipment_id
			java.sql.Types.DOUBLE   // salary
	};

	private static String ADD_EQUIPMENT_TRACKING = "INSERT INTO task_equipment_daily_tracking(task_id, project_id, actual_time, date_log, owner, quantity, equipment_id, price) "
		    + "VALUES (?, ?, ?, 'now()'::date, ?, ?, ?, ?)";

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(TaskEquipments taskEquipments) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_EQUIPMENT_TRACKING, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(taskEquipments));
		return psc;
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(TaskEquipments taskEquipments) {
		return new Object[] { 
				taskEquipments.getTaskId(),
				taskEquipments.getProjectId(), 
				taskEquipments.getActualTime(), 
				taskEquipments.getOwner(),
				taskEquipments.getQuantity(),
				taskEquipments.getEquipmentId(),
				taskEquipments.getPrice()
				};
	}
	//getValuesForPrimaryKey
		protected Object[] getValuesForPrimaryKey(TaskEquipments taskEquipments)  {
			return new Object[] {
					taskEquipments.getId() // id: java.lang.Integer
			};
		}
	public int insert(TaskEquipments taskEquipments) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(taskEquipments), keyHolder);

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