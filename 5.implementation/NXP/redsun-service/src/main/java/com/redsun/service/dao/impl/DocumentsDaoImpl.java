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

import com.redsun.service.dao.DocumentsDao;
import com.redsun.service.dao.mapper.DocumentDetailMapper;
import com.redsun.service.entities.Documents;

/**
 * Documents DAO implementation 
 */
@Repository
public class DocumentsDaoImpl implements DocumentsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(DocumentsDaoImpl.class);
	private final static String AUTO_INCREMENTED_COLUMN = "id";
	private static String ADD_DOCUMENT = "insert into documents (name, project_id, task_id, type_id, status_id, create_date_time, author_name, description, client_id) values (?, ?, ?, ?, ?, 'now'::date, ?, ?, ?)";
	private final static int[] SQL_INSERT_TYPES = new int[] { 
																						
			java.sql.Types.VARCHAR, // name
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.INTEGER, // task_id
			java.sql.Types.INTEGER, // type_id
			java.sql.Types.INTEGER, // status_id
			java.sql.Types.VARCHAR, //author_name
			java.sql.Types.VARCHAR, // description
			java.sql.Types.INTEGER // client_id
	};
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Documents document) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(ADD_DOCUMENT, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(document));
		return psc;
	}

	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Documents document) {
		return new Object[] { 
				document.getName(),
				document.getProjectId(),
				document.getTaskId(),
				document.getTypeId(),
				document.getStatusId(),
				document.getAuthorName(),
				document.getDescription(),
				document.getClientId()
				};
	}
	//getValuesForPrimaryKey
		protected Object[] getValuesForPrimaryKey(final Documents document)  {
			return new Object[] {
					document.getId() // id: java.lang.Integer
			};
		}
	public int insert(final Documents document) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int result = jdbcTemplate.update(getPreparedStatementCreator(document), keyHolder);

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

	
    @Autowired
    private DocumentDetailMapper documentsMapper;


	@Override
	public List<Documents> listDocumentsOneProject(int clientId, int projectId) {
		List<Documents> listDocuments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(projectId);
		   String SQL_SELECT_FOR_ONE_PROJECT = 
				   "select d.id, d.name, d.description, d.project_id, d.task_id, d.type_id, d.status_id, d.author_name, d.client_id, count(*) over() as total_count,"
					+ "projects.name as project_name, types.name as type_name, statuses.name as status_name, d.create_date_time " 
					+ "from documents as d inner join projects on d.project_id = projects.id "
					+ "inner join types on d.type_id = types.id "
					+ "inner join statuses on d.status_id = statuses.id where d.client_id = ? and d.project_id = ? ";


		try {
			listDocuments = jdbcTemplate.query(SQL_SELECT_FOR_ONE_PROJECT, params.toArray(), documentsMapper);
			return listDocuments;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listDocuments;
	}

	@Override
	public Documents getByTaskId(int taskId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(taskId);
		params.add(clientId);
		   String SQL_GET_BY_TASKID = 
				   "select d.id, d.name, d.description, d.project_id, d.task_id, d.type_id, d.status_id, d.author_name, d.client_id, count(*) over() as total_count,"
					+ "projects.name as project_name, types.name as type_name, statuses.name as status_name, d.create_date_time " 
					+ "from documents as d inner join projects on d.project_id = projects.id "
					+ "inner join types on d.type_id = types.id "
					+ "inner join statuses on d.status_id = statuses.id where d.task_id = ? and d.client_id = ?  ";
		try {
			return jdbcTemplate.queryForObject(SQL_GET_BY_TASKID, params.toArray(), documentsMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
