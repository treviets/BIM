package com.redsun.social.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.social.dao.FileSharingDao;
import com.redsun.social.dao.mapper.FileSharingMapper;
import com.redsun.social.entities.FileSharing;

@Repository
public class FileSharingDaoImpl implements FileSharingDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String createFileSharing = "Insert into file_sharing (client_id, project_id, group_id, name, created_by, title, description, scopes, file_path) values (?,?,?,?,?,?,?,?,?)";
	private String getFileSharing = "Select id, client_id, project_id, group_id, name, created_by, title, created_date, description, file_path, scopes from file_sharing where project_id = ? and group_id = ? and client_id = ?";
	private String deleteFileSharing = "Delete from file_sharing where id=?";
	
	@Autowired
	FileSharingMapper fileSharingMapper;
	
	private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER, // client_id
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.INTEGER, // group_id
			java.sql.Types.VARCHAR, // name
			java.sql.Types.VARCHAR, // create_by
			java.sql.Types.VARCHAR, // title
			java.sql.Types.VARCHAR, // Description
			java.sql.Types.INTEGER, // scope
			java.sql.Types.VARCHAR, // filePath

	};
	private final static String AUTO_INCREMENTED_COLUMN = "id";
	
	private PreparedStatementCreator getPreparedStatementCreator(final FileSharing fileSharing) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(createFileSharing, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(fileSharing));
		return psc;
	}
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final FileSharing fileSharing) {
		return new Object[] { 
			fileSharing.getClientId(),
			fileSharing.getProjectId(),
			fileSharing.getGroupId(),
			fileSharing.getName(),
			fileSharing.getCreatedBy(),
			fileSharing.getTitle(),
			fileSharing.getDescription(),
			fileSharing.getScopes(),
			fileSharing.getFilePath()
		};
	}
	
	@Override
	public int createFileSharing(FileSharing fileSharing) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(getPreparedStatementCreator(fileSharing), keyHolder);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after Create Chatting : " + result + " (0 or 1 expected) ");
		}
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<FileSharing> getFileSharingByGroup(int projectId, int groupId, int clientId) {
		List<FileSharing> fileSharing = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(groupId);
		params.add(clientId);
		fileSharing = jdbcTemplate.query(getFileSharing, params.toArray(), fileSharingMapper);
		return fileSharing;
	}

	@Override
	public int deleteFileSharing(int fileSharingId) {
		Object[] primaryKey = new Object[] { fileSharingId };
		int result = jdbcTemplate.update(deleteFileSharing, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return fileSharingId ;
	}

	@Override
	public FileSharing getById(int fileId) {
		Object[] primaryKey = new Object[] { fileId };
		String SQL_SELECT_GETBY_ID = "Select id, client_id, project_id, name, created_by, title, created_date, description, file_path, scopes from file_sharing where id= ?";
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_GETBY_ID, primaryKey, fileSharingMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
