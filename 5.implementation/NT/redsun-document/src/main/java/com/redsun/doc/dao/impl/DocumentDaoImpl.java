package com.redsun.doc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.doc.dao.DocumentDao;
import com.redsun.doc.dao.mapper.DocumentMapper;
import com.redsun.doc.entities.Document;


@Repository
public class DocumentDaoImpl implements DocumentDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private DocumentMapper documentMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(1000)
			java.sql.Types.VARCHAR, //location varchar(1000)
			java.sql.Types.INTEGER, //project_id int4
			java.sql.Types.INTEGER, //client_id int4
			java.sql.Types.VARCHAR, //ref_type varchar(100)
			java.sql.Types.INTEGER, //directory_id int4
			java.sql.Types.VARCHAR, //mime_type varchar(100)
			java.sql.Types.INTEGER, //file_size int4
			java.sql.Types.VARCHAR, //link varchar(1024)
			java.sql.Types.INTEGER, //document_type int4
			java.sql.Types.INTEGER, //document_version_id int4
			java.sql.Types.SMALLINT, //locked int2
			java.sql.Types.VARCHAR, //description varchar(400)
			java.sql.Types.VARCHAR, //user_name varchar(45)
			java.sql.Types.DATE //create_date_time date
	};

	private final static String SQL_INSERT = 
		"insert into documents (name, location, project_id, client_id, ref_type, directory_id, mime_type, file_size, link, document_type, document_version_id, locked, description, user_name, create_date_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update documents set name = ?, location = ?, project_id = ?, client_id = ?, ref_type = ?, directory_id = ?, mime_type = ?, file_size = ?, link = ?, document_type = ?, document_version_id = ?, locked = ?, description = ?, user_name = ?, create_date_time = ? where id = ?";

	private final static String SQL_UPDATE_LOCATION = 
		"update documents set location = replace(location, ?, ?)";

	private final static String SQL_UPDATE_LOCATION_BY_REF = 
		"update documents set location = ? where ref_type = ? AND directory_id = ?";

	private final static String SQL_DELETE = 
		"delete from documents where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from documents";

	private final static String SQL_COUNT = 
		"select count(*) from documents where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, location, project_id, client_id, ref_type, directory_id, mime_type, file_size, link, document_type, document_version_id, locked, description, user_name, create_date_time from documents where id = ?";

	//private static Logger log = Logger.getLogger(DocumentDaoImpl.class);

	

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Document document) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(document));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Document document)  {
		return new Object[] {
			document.getName(), 			
			document.getLocation(), 			
			document.getProjectId(), 			
			document.getClientId(), 
			document.getRefType(),
			document.getDirectoryId(),
			document.getMimeType(),
			document.getFileSize(),
			document.getLink(),
			document.getDocumentType(),
			document.getDocumentVersionId(),
			document.getLocked(),
			document.getDescription(),
			document.getUserName(),
			document.getCreateDateTime()
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Document document) {
		return new Object[] {		
			document.getName(), 			
			document.getLocation(), 			
			document.getProjectId(), 			
			document.getClientId(), 
			document.getRefType(),
			document.getDirectoryId(),
			document.getMimeType(),
			document.getFileSize(),
			document.getLink(),
			document.getDocumentType(),
			document.getDocumentVersionId(),
			document.getLocked(),
			document.getDescription(),
			document.getUserName(),
			document.getCreateDateTime(),
			document.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Document document)  {
		return new Object[] {
			document.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Document document) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(document), keyHolder);

		if (result != 1) {
			throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
		}
		
		Number key = keyHolder.getKey();
		if (key != null) {
			return key.intValue();
		}
		else {
			throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
		}
	}

    // Update.
	@Override
	public int update(final Document document) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(document));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return document.getId();
	}	
	
	// Update location.
	@Override
	public int updateLocation(final String oldLocation, final String newLocation) {
		Object[] params = new Object[] { oldLocation, newLocation };
		int result = jdbcTemplate.update(SQL_UPDATE_LOCATION, params);
		
		return result;
	}

	// Update location by ref.
	@Override
	public int updateDocumentByRef(final String ref_type, final Integer ref_id, final String location) {
		Object[] params = new Object[] { location, ref_type, ref_id };
		int result = jdbcTemplate.update(SQL_UPDATE_LOCATION_BY_REF, params);

		return result;
	}

    // Delete.
	@Override
	public int delete(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	// Get By Id.
	@Override
	public List<Document> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Document> result = jdbcTemplate.query(SQL_SELECT, primaryKey, documentMapper);
		return result;
	}

    // Exists.
	@Override
	public boolean exists(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		long count = jdbcTemplate.queryForObject(SQL_COUNT, primaryKey, Long.class);
		return count > 0 ;
	}

    // Count.
	@Override
	public long count() {
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, Long.class);
	}	

}