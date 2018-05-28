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

import com.redsun.service.dao.DocumentVersionsDao;
import com.redsun.service.dao.mapper.DocumentVersionsMapper;
import com.redsun.service.entities.DocumentVersions;

/**
 * Documents DAO implementation
 */
@Repository
public class DocumentVersionsDaoImpl implements DocumentVersionsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(DocumentVersionsDaoImpl.class);
	private final static String AUTO_INCREMENTED_COLUMN = "id";
	private final static String SQL_DELETE_DOCUMENTVERSION = 
			"delete from document_versions where id = ?";
	@Autowired
	private DocumentVersionsMapper documentversionsMapper;

	private final static int[] SQL_INSERT_TYPES = new int[] { 

			java.sql.Types.VARCHAR, // name
			java.sql.Types.INTEGER, //project_id
			java.sql.Types.INTEGER, //task_id
			java.sql.Types.INTEGER, //document_id
			java.sql.Types.VARCHAR,//author_name
			java.sql.Types.VARCHAR, //link
			java.sql.Types.VARCHAR, //description
			java.sql.Types.INTEGER, //version
			java.sql.Types.INTEGER //client_id

	};
	static private String ADD_DOCUMENT__VERSION = "INSERT INTO document_versions("
            + "name, project_id, task_id, document_id, author_name, link, description, version, " 
            + "create_date_time, update_date_time, client_id) " 
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, " 
            + "'now()'::date, 'now()'::date, ?)" ;
	// Get Prepared Statement Creator.
			private PreparedStatementCreator getPreparedStatementCreator(final DocumentVersions documentVersions) {
				PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_DOCUMENT__VERSION, SQL_INSERT_TYPES);

				factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
				PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(documentVersions));
				return psc;
			}
			protected Object[] getValuesForInsert(final DocumentVersions documentVersions) {
				return new Object[] { 
						documentVersions.getName(),
						documentVersions.getProjectId(),
						documentVersions.getTaskId(),
						documentVersions.getDocumentId(),
						documentVersions.getAuthorName(),
						documentVersions.getLink(),
						documentVersions.getDescription(),
						documentVersions.getVersion() + 1,
						documentVersions.getClientId()
						};
			}
			//getValuesForPrimaryKey
				protected Object[] getValuesForPrimaryKey(final DocumentVersions documentVersions)  {
					return new Object[] {
							documentVersions.getId() // id: java.lang.Integer
					};
				}
			public int insert(final DocumentVersions documentVersions) {
				KeyHolder keyHolder = new GeneratedKeyHolder();

				int result = jdbcTemplate.update(getPreparedStatementCreator(documentVersions), keyHolder);

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
	public List<DocumentVersions> getByDocumentId(int documentId) {
		List<DocumentVersions> listDocumentVersions = null;
		List<Object> params = new ArrayList<Object>();
		params.add(documentId);
		String SQL_SELECT_GETBY_DOCUMENTID = "SELECT dv.id, dv.name, dv.project_id, dv.task_id, dv.document_id, dv.client_id, count(*) over() AS total_count,"
				+ "dv.version, dv.create_date_time, dv.update_date_time, dv.link, dv.description, dv.author_name, "
				+ "d.name AS document_name, p.name AS project_name FROM document_versions AS dv "
				+ "INNER JOIN documents AS d ON dv.document_id = d.id "
				+ "INNER JOIN projects AS p ON dv.project_id = p.id WHERE dv.document_id = ? ";

		try {
			listDocumentVersions = jdbcTemplate.query(SQL_SELECT_GETBY_DOCUMENTID, params.toArray(), documentversionsMapper);
			return listDocumentVersions;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listDocumentVersions;
	}

	@Override
	public DocumentVersions getById(int id) {
		Object[] primaryKey = new Object[] { id };
		String SQL_SELECT_GETBY_ID = "SELECT dv.id, dv.name, dv.project_id, dv.task_id, dv.document_id, dv.client_id, count(*) over() AS total_count,"
				+ "dv.version, dv.create_date_time, dv.update_date_time, dv.link, dv.description, dv.author_name, "
				+ "d.name AS document_name, p.name AS project_name FROM document_versions AS dv "
				+ "INNER JOIN documents AS d ON dv.document_id = d.id "
				+ "INNER JOIN projects AS p ON dv.project_id = p.id WHERE dv.id = ? ";
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_GETBY_ID, primaryKey, documentversionsMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Override
	public List<DocumentVersions> listOnPage(int clientId, int projectId) {
		List<DocumentVersions> listDocumentVersions = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(projectId);
		String SQL_SELECT_ALL = "SELECT dv.id, dv.name, dv.project_id, dv.task_id, dv.document_id, dv.client_id, count(*) over() AS total_count,"
				+ "dv.version, dv.create_date_time, dv.update_date_time, dv.link, dv.description, dv.author_name, "
				+ "d.name AS document_name, p.name AS project_name FROM document_versions AS dv "
				+ "INNER JOIN documents AS d ON dv.document_id = d.id "
				+ "INNER JOIN projects AS p ON dv.project_id = p.id where dv.client_id = ? and dv.project_id =?  ";
		

		try {
			listDocumentVersions = jdbcTemplate.query(SQL_SELECT_ALL, params.toArray(), documentversionsMapper);
			return listDocumentVersions;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listDocumentVersions;
	}
	@Override
	public List<DocumentVersions> getByTaskId(int taskId) {
		List<DocumentVersions> listDocumentVersions = null;
		List<Object> params = new ArrayList<Object>();
		params.add(taskId);
		String SQL_SELECT_GETBY_TASKID = "SELECT dv.id, dv.name, dv.project_id, dv.task_id, dv.document_id, dv.client_id, count(*) over() AS total_count,"
				+ "dv.version, dv.create_date_time, dv.update_date_time, dv.link, dv.description, dv.author_name, "
				+ "d.name AS document_name, p.name AS project_name FROM document_versions AS dv "
				+ "INNER JOIN documents AS d ON dv.document_id = d.id "
				+ "INNER JOIN projects AS p ON dv.project_id = p.id WHERE dv.task_id = ? ";

		try {
			listDocumentVersions = jdbcTemplate.query(SQL_SELECT_GETBY_TASKID, params.toArray(), documentversionsMapper);
			return listDocumentVersions;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listDocumentVersions;
	}
	// Delete.
	@Override
	public int delete(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE_DOCUMENTVERSION, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}


}
