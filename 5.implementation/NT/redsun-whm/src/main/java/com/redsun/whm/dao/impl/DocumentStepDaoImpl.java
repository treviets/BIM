package com.redsun.whm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.whm.dao.DocumentStepDao;
import com.redsun.whm.dao.mapper.DocumentStepRowMapper;
import com.redsun.whm.entities.DocumentStep;


/**
 * Document Step DAO implementation 
 */
@Repository
public class DocumentStepDaoImpl implements DocumentStepDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private DocumentStepRowMapper documentStepMapper;
    
    private final static String AUTO_INCREMENTED_COLUMN = "id";
    private final static String SQL_INSERT_DOCUMENT_STEP = "insert into document_step (project_id, step_id, document_name, document_description) values (?, ?, ?, ?)";
    private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.VARCHAR, // step_id
			java.sql.Types.VARCHAR, // document_name
			java.sql.Types.VARCHAR  // document_description
	};
	
	private final static String SQL_SELECT_ALL = 
			"SELECT id, project_id, step_id, document_name, document_description FROM document_step WHERE project_id = ? AND step_id = ?";
	
	private final static String SQL_UPDATE = 
			"UPDATE document_step set id = ?, project_id = ?, step_id = ?, document_name = ?, document_description = ? WHERE project_id = ? AND step_id = ?";
	
	private final static String SQL_DELETE = "DELETE from document_step WHERE project_id =? AND step_id =? AND document_name =?";
	// Get all document of project
	@Override
	public List<DocumentStep> getAllDocumentForStep(int projectId, String stepId) {
		Object[] primaryKey = new Object[] { projectId, stepId };
		List<DocumentStep> result = jdbcTemplate.query(SQL_SELECT_ALL, primaryKey, documentStepMapper);
			
		return result;
	}
		
	// Insert document into database
	@Override
	public int insert(DocumentStep documentStep) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(documentStep), keyHolder);

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
		
	// Update document of step
	@Override
	public int update(DocumentStep documentStep) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(documentStep));
		
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}

		return documentStep.getId();
	}

	// Update document of step
	@Override
	public int delete(DocumentStep documentStep) {
		Object[] primaryKey = new Object[] { documentStep.getIdProject(), documentStep.getIdStep(), documentStep.getDocumentName()};
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return 1 ;
	}
	
	// Prepare data for update 
	protected Object[] getValuesForUpdate(final DocumentStep documentStep) {
		return new Object[] {		
			documentStep.getId(), 
			documentStep.getIdProject(), 
			documentStep.getIdStep(), 
			documentStep.getDocumentName(), 
			documentStep.getDocumentDescription()
		};
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(final DocumentStep documentStep) {
		return new Object[] { 
				documentStep.getIdProject(), 
				documentStep.getIdStep(), 
				documentStep.getDocumentName(), 
				documentStep.getDocumentDescription()
		};
	}

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final DocumentStep documentStep) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT_DOCUMENT_STEP, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(documentStep));
		
		return psc;
	}

		
	
	
	
	
}
