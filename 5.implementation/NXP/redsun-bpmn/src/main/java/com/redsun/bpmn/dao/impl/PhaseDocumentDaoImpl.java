package com.redsun.bpmn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.bpmn.dao.PhaseDocumentDao;
import com.redsun.bpmn.dao.mapper.PhaseDocumentRowMapper;
import com.redsun.bpmn.entities.PhaseDocument;


/**
 * PhaseDocument DAO implementation 
 */
@Repository
public class PhaseDocumentDaoImpl implements PhaseDocumentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PhaseDocumentRowMapper phaseDocumentMapper;
    
    private final static String AUTO_INCREMENTED_COLUMN = "id";
    private final static String SQL_INSERT_PHASE_DOCUMENT = "insert into phase_document (project_id, active_step, upload_by, upload_date, update_by, update_date, doc_name, url) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.VARCHAR, // active_step
			java.sql.Types.VARCHAR, // upload_by
			java.sql.Types.VARCHAR, // upload_date
			java.sql.Types.VARCHAR, // update_by
			java.sql.Types.VARCHAR, // update_date
			java.sql.Types.VARCHAR, // doc_name
			java.sql.Types.VARCHAR  // doc_url
	};
	
	private final static String SQL_SELECT_ALL = 
			"SELECT id, project_id, active_step, upload_by, upload_date, update_by, update_date, doc_name, url FROM phase_document WHERE project_id = ?";
	
	private final static String SQL_SELECT_ALL_BY_ID = 
			"SELECT id, project_id, active_step, upload_by, upload_date, update_by, update_date, doc_name, url FROM phase_document WHERE project_id = ? AND active_step = ?";
	
	private final static String SQL_UPDATE = 
			"UPDATE phase_document set id = ?, project_id = ?, active_step = ?, upload_by = ?, upload_date = ?, update_by = ?, update_date = ?, doc_name = ?, url = ? WHERE project_id = ? AND active_step = ?";
	
	private final static String SQL_DELETE = 
			"DELETE from phase_document WHERE project_id =? AND active_step =? AND doc_name =?";
	// Prepare data for update 
	protected Object[] getValuesForUpdate(final PhaseDocument phaseDocument) {
		return new Object[] {		
			phaseDocument.getId(), 
			phaseDocument.getIdProject(), 
			phaseDocument.getActiveStep(), 
			phaseDocument.getUploadBy(),
			phaseDocument.getUploadDate(),
			phaseDocument.getUpdateBy(),
			phaseDocument.getUpdateDate(),
			phaseDocument.getDocumentName(),
			phaseDocument.getDocumentUrl(),
			phaseDocument.getIdProject()
		};
	}
	

	// Get Values For Insert.
	protected Object[] getValuesForInsert(final PhaseDocument phaseDocument) {
		return new Object[] { 
			phaseDocument.getIdProject(), 
			phaseDocument.getActiveStep(), 
			phaseDocument.getUploadBy(),
			phaseDocument.getUploadDate(),
			phaseDocument.getUpdateBy(),
			phaseDocument.getUpdateDate(),
			phaseDocument.getDocumentName(),
			phaseDocument.getDocumentUrl(),
		};
	}

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final PhaseDocument phaseDocument) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT_PHASE_DOCUMENT, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(phaseDocument));
		
		return psc;
	}
		
	
	// Get all document of project
	@Override
	public List<PhaseDocument> getAllPhaseDocument(int projectId) {
		Object[] primaryKey = new Object[] { projectId };
		List<PhaseDocument> result = jdbcTemplate.query(SQL_SELECT_ALL, primaryKey, phaseDocumentMapper);
		
		return result;
	}
	

	// Get all document of step
	@Override
	public List<PhaseDocument> getAllPhaseDocumentForStep(int projectId, int activeStep, int activeSubStep) {
		Object[] primaryKey = new Object[] { projectId };
		List<PhaseDocument> result = jdbcTemplate.query(SQL_SELECT_ALL_BY_ID, primaryKey, phaseDocumentMapper);
		
		return result;
	}
	
	
	// Insert document into database
	@Override
	public int insert(PhaseDocument phaseDocument) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(phaseDocument), keyHolder);

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
	public int update(PhaseDocument phaseDocument) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(phaseDocument));
		
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}

		return phaseDocument.getId();
	}

	// Delete document into database
	@Override
	public int delete(PhaseDocument phaseDocument) {
		Object[] primaryKey = new Object[] { phaseDocument.getIdProject(), phaseDocument.getActiveStep(), phaseDocument.getDocumentName()};
		
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		
		return 1;
	}
	
	
}
