package com.redsun.bpmn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.bpmn.dao.ResourceStepDao;
import com.redsun.bpmn.dao.mapper.ResourceStepRowMapper;
import com.redsun.bpmn.entities.ResourceStep;


/**
 * Document Step DAO implementation 
 */
@Repository
public class ResourceStepDaoImpl implements ResourceStepDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ResourceStepRowMapper resourceStepMapper;
    
    private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.VARCHAR, // step_id
			java.sql.Types.VARCHAR, // code
			java.sql.Types.VARCHAR, // email
	};
    
    private final static String AUTO_INCREMENTED_COLUMN = "id";
    
    private final static String SQL_INSERT_RESOURCE_STEP = 
    	"insert into resource_step (project_id, step_id, code, email) values (?, ?, ?, ?)";
    
	private final static String SQL_SELECT_ALL = 
		"SELECT * FROM resource_step WHERE project_id = ? AND step_id = ?";
	
	private final static String SQL_UPDATE = 
		"UPDATE resource_step set project_id=?, step_id=?, code=? WHERE project_id = ? AND step_id = ?";
	
	private final static String SQL_DELETE = 
		"DELETE from resource_step WHERE project_id =? AND step_id =? AND code =?";
	
	// Insert document into database
	@Override
	public int insert(ResourceStep resourceStep) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(resourceStep), keyHolder);

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
	
	// Get all document of project
	@Override
	public List<ResourceStep> getAllResourceForStep(int projectId, String stepId) {
		Object[] primaryKey = new Object[] { projectId, stepId };
		List<ResourceStep> result = jdbcTemplate.query(SQL_SELECT_ALL, primaryKey, resourceStepMapper);
			
		return result;
	}
	
	// Update document of step
	@Override
	public int update(ResourceStep resourceStep) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(resourceStep));
		
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}

		return resourceStep.getId();
	}
	
	 // Delete resource of step
	@Override
	public int delete(int projectId, String stepId, String code) {
		Object[] primaryKey = new Object[] { projectId, stepId, code};
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return 1 ;
	}
	
	// Prepare data for update 
	protected Object[] getValuesForUpdate(final ResourceStep resourceStep) {
		return new Object[] {		
				resourceStep.getId(), 
				resourceStep.getProjectId(), 
				resourceStep.getStepId(), 
				resourceStep.getResourceCode(),
				resourceStep.getProjectId(), 
				resourceStep.getStepId()
		};
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(final ResourceStep resourceStep) {
		return new Object[] { 
				resourceStep.getProjectId(), 
				resourceStep.getStepId(), 
				resourceStep.getResourceCode(),
				resourceStep.getEmailOfResponsible()
		};
	}

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final ResourceStep resourceStep) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT_RESOURCE_STEP, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(resourceStep));
		
		return psc;
	}

	
}
