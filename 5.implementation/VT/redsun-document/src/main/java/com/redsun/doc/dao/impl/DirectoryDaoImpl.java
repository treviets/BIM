package com.redsun.doc.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.doc.dao.DirectoryDao;
import com.redsun.doc.dao.mapper.DirectoryMapper;
import com.redsun.doc.dao.mapper.DirectoryResourceMapper;
import com.redsun.doc.dao.mapper.DocumentMapper;
import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Document;


@Repository
public class DirectoryDaoImpl implements DirectoryDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private DirectoryMapper directoryMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(300)
			java.sql.Types.VARCHAR, //location varchar(1000)
			java.sql.Types.INTEGER, //parent_id int4
			java.sql.Types.INTEGER, //project_id int4
			java.sql.Types.INTEGER, //client_id int4
			java.sql.Types.DATE, //create_date_time date
			java.sql.Types.DATE, //update_date_time date
			java.sql.Types.VARCHAR, //create_user_name varchar(45)
			java.sql.Types.INTEGER //trash
	};
	
	private final static int[] ASSIGN_RESOURCE_SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //directory_id integer
			java.sql.Types.INTEGER, //project_id integer
			java.sql.Types.INTEGER, //resource_id integer
			java.sql.Types.INTEGER, //client_id int4
			java.sql.Types.VARCHAR //create_user_name varchar(45)
	};

	private final static String SQL_INSERT = 
		"insert into directories (name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_INSERT_DIRECTORY_DEF = 
			"insert into default_directories (name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name, trash) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_INSERT_ASSIGN_RESOURCE = 
			"insert into directory_resources (directory_id, project_id, resource_id, client_id, user_name) values (?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update directories set name = ?, location = ?, parent_id = ?, project_id = ?, client_id = ?, create_date_time = ?, update_date_time = ?, create_user_name = ?, trash = ? where id = ?";
	
	private final static String SQL_UPDATE_DIRECTORY_DEF = 
			"update default_directories set name = ?, location = ?, parent_id = ?, project_id = ?, client_id = ?, create_date_time = ?, update_date_time = ?, create_user_name = ?, trash = ? where id = ?";
	
	private final static String SQL_UPDATE_PARENTID_BATCH = 
			"update directories set parent_id = ? where id = ?";

	private final static String SQL_UPDATE_LOCATION = 
		"update directories set location = replace(location, ?, ?)";
	
	private final static String SQL_UPDATE_LOCATION_DIRECTORY_DEF = 
			"update default_directories set location = replace(location, ?, ?)";

	private final static String SQL_DELETE = 
		"delete from directories where id = ?";
	
	private final static String SQL_DELETE_DIRECTORY_DEF = 
			"delete from default_directories where id = ?";
	
	private final static String SQL_DELETE_FOLDER_ASSIGN = 
			"delete from directory_resources where directory_id = ?";
	
	private final static String SQL_MOVE_FOLDER_TO_TRASH = "UPDATE directories SET trash = ? WHERE id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from directories";

	private final static String SQL_COUNT = 
		"select count(*) from directories where id = ?";

	private final static String SQL_EXISTS_NAME = 
		"select count(*) from directories where UPPER(location) = UPPER(?) AND UPPER(name) = UPPER(?)";
	
	private final static String SQL_EXISTS_NAME_DEF = 
			"select count(*) from default_directories where UPPER(location) = UPPER(?) AND UPPER(name) = UPPER(?)";

	private final static String SQL_EXISTS_ID_AND_NAME = 
		"select count(*) from directories where id != ? and UPPER(name) = UPPER(?)";
	
	private final static String SQL_EXISTS_ID_AND_NAME_DEF = 
			"select count(*) from default_directories where id != ? and UPPER(name) = UPPER(?)";
	
	private final static String SQL_SELECT = 
		"select id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name from directories where trash = 0 and id = ?";
	
	private final static String SQL_SELECT_DIRECTORY_DEF = 
			"select id, name, location, parent_id, project_id, client_id, create_date_time, update_date_time, create_user_name from default_directories where trash = 0 and id = ?";

	private final static String SQL_SELECT_DR = 
			"select id, directory_id, project_id, resource_id from directory_resources where directory_id = ?";
	
	static private String SELECT_ALL_DIRECTORY_BY_PROJECT = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d"
			+ " INNER JOIN directory_resources dr ON d.id = dr.directory_id"
			+ " WHERE d.project_id = ? AND d.client_id = ? AND dr.user_name = ? AND d.trash = ?";
	
	static private String SELECT_ALL_DIRECTORY_BY_PROJECT_INIT = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d"
			+ " WHERE d.project_id = ? AND d.client_id = ? AND d.create_user_name = ? AND d.trash = ?";
	
	static private String SELECT_ALL_DIRECTORY_BY_ADMIN = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d"
			+ " WHERE d.client_id = ? AND d.trash = ?"
			+ " ORDER BY d.parent_id, d.name";
	
	static private String SELECT_ALL_DEFAULT_DIRECTORY = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM default_directories d"
			+ " ORDER BY d.parent_id, d.name";
	
	static private String SELECT_ALL_DIRECTORY = "SELECT distinct d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d"
			+ " LEFT JOIN directory_resources dr ON d.id = dr.directory_id"
			+ " WHERE d.client_id = ? AND d.trash = ? AND d.create_user_name = ? OR dr.user_name = ?"
			+ " ORDER BY d.parent_id, d.name";
	
	static private String SELECT_FOLDERS_BY_ID = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d"
			+ " WHERE d.id = ? OR d.parent_id = ?";
	
	static private String SELECT_FOLDERS_BY_LOCATION = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d"
			+ " WHERE d.id = ? OR UPPER(REPLACE(d.location," + "'" + File.separatorChar  + "'" + ", '-')) LIKE UPPER(?) "
			+ " ORDER BY d.parent_id DESC, d.id DESC";
	
	static private String SELECT_FOLDERS_DEF_BY_LOCATION = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM default_directories d"
			+ " WHERE d.id = ? OR UPPER(REPLACE(d.location," + "'" + File.separatorChar  + "'" + ", '-')) LIKE UPPER(?) "
			+ " ORDER BY d.parent_id DESC, d.id DESC";
	
	static private String SELECT_FILES_BY_FOLDER = "SELECT d.id, d.name, d.location, d.create_date_time FROM documents d WHERE d.directory_id = ?";
	
	static private String SELECT_FILE_BY_ID = "SELECT d.id, d.name, d.location, d.create_date_time FROM documents d WHERE d.id = ?";
	
	private final static String SELECT_DOUBLE_FILE = "SELECT d.id, d.name, d.location, d.create_date_time FROM documents d WHERE d.directory_id = ? AND d.name = ?";
	
	static private String SELECT_FOLDER_FROM_TRASH = "SELECT d.id, d.name, d.location, d.parent_id, d.project_id, d.client_id, d.create_date_time, d.update_date_time, d.create_user_name FROM directories d WHERE d.client_id = ? AND d.trash = 1";
	
	private static Logger log = Logger.getLogger(DirectoryDaoImpl.class);

	

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Directory directory) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(directory));
		return psc ;
	}
	
	private PreparedStatementCreator getPreparedStatementCreatorDef(final Directory directory) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT_DIRECTORY_DEF, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(directory));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Directory directory)  {
		return new Object[] {
			directory.getName(), 			
			directory.getLocation(), 			
			directory.getParentId(), 			
			directory.getProjectId(), 			
			directory.getClientId(), 			
			directory.getCreateDate(), 			
			directory.getUpdateDate(), 			
			directory.getCreateUserName(),
			directory.getTrash()
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Directory directory) {
		return new Object[] {		
			directory.getName(), 			
			directory.getLocation(), 			
			directory.getParentId(), 			
			directory.getProjectId(), 			
			directory.getClientId(), 			
			directory.getCreateDate(), 			
			directory.getUpdateDate(), 			
			directory.getCreateUserName(),
			directory.getTrash(),
			directory.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Directory directory)  {
		return new Object[] {
			directory.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Directory directory) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(directory), keyHolder);

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

	public void insertBatch(final List<Directory> directories){	
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (Directory directory : directories) {
            parameters.add(new Object[] {directory.getName(), directory.getLocation(), directory.getParentId(), directory.getProjectId()
            							, directory.getClientId(), directory.getCreateDate(), directory.getUpdateDate(), directory.getCreateUserName(), directory.getTrash()});
        }
        jdbcTemplate.batchUpdate(SQL_INSERT, parameters);
        
	}

    // Update.
	@Override
	public int update(final Directory directory) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(directory));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return directory.getId();
	}	
	
	public void updateBatch(final List<Directory> directories){	
		List<Object[]> parameters = new ArrayList<Object[]>();
        for (Directory directory : directories) {
            parameters.add(new Object[] {directory.getParentId(), directory.getId()});
        }
        jdbcTemplate.batchUpdate(SQL_UPDATE_PARENTID_BATCH, parameters);
        
	}
	
	// Update location.
	@Override
	public int updateLocation(final String oldLocation, final String newLocation) {
		Object[] params = new Object[] { oldLocation, newLocation };
		int result = jdbcTemplate.update(SQL_UPDATE_LOCATION, params);
		
		return result;
	}

	// move folder to trash.
		@Override
		public int moveFolderToTrash(int id, int isTrash) {
			Object[] param = new Object[] { isTrash, id };
			int result = jdbcTemplate.update(SQL_MOVE_FOLDER_TO_TRASH, param);
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
	
	@Override
	public int deleteDR(final Integer directoryId) {
		Object[] supperKey = new Object[] { directoryId };
		int result = jdbcTemplate.update(SQL_DELETE_FOLDER_ASSIGN, supperKey);
		/*
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		*/
		return directoryId ;
	}

	// Get By Id.
	@Override
	public List<Directory> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Directory> result = jdbcTemplate.query(SQL_SELECT, primaryKey, directoryMapper);
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

    // Exists name.
	@Override
	public boolean existName(final String location, final String name) {
		Object[] params = new Object[] { location, name };
		long count = jdbcTemplate.queryForObject(SQL_EXISTS_NAME, params, Long.class);
		return count > 0 ;
	}

    // Exists id and name.
	@Override
	public boolean existIdAndName(final Integer id, final String name) {
		Object[] params = new Object[] { id, name };
		long count = jdbcTemplate.queryForObject(SQL_EXISTS_ID_AND_NAME, params, Long.class);
		return count > 0 ;
	}
	
	@Override
	public List<Directory> listDirectory(int projectId, int clientId, String userName, int trash) {
		// TODO Auto-generated method stub
		List<Directory> listDirectories = null;
		try{
			listDirectories = jdbcTemplate.query(SELECT_ALL_DIRECTORY_BY_PROJECT, new Object[]{projectId, clientId, userName, trash}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectories;
	}
	
	@Override
	public List<Directory> getDirectoryByProjectInit(int projectId, int clientId, String userName, int trash) {
		// TODO Auto-generated method stub
		List<Directory> listDirectories = null;
		try{
			listDirectories = jdbcTemplate.query(SELECT_ALL_DIRECTORY_BY_PROJECT_INIT, new Object[]{projectId, clientId, userName, trash}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectories;
	}
	
	@Override
	public List<Directory> listDefaultDirectory() {
		// TODO Auto-generated method stub
		List<Directory> listDefaultDirectories = null;
		try{
			listDefaultDirectories = jdbcTemplate.query(SELECT_ALL_DEFAULT_DIRECTORY, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDefaultDirectories;
	}
	
	@Override
	public List<Directory> listDirectory(int clientId, String userName, int trash) {
		// TODO Auto-generated method stub
		List<Directory> listDirectories = null;
		try{
			if(userName.equals("admin"))
				listDirectories = jdbcTemplate.query(SELECT_ALL_DIRECTORY_BY_ADMIN, new Object[]{clientId, trash}, new DirectoryMapper());
			else
				listDirectories = jdbcTemplate.query(SELECT_ALL_DIRECTORY, new Object[]{clientId, trash, userName, userName}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectories;
	}
	
	@Override
	public List<Directory> getDirectoryByLocation(int id, String location) {
		// TODO Auto-generated method stub
		location = location + "%";
		List<Directory> listDirectories = null;
		try{
			listDirectories = jdbcTemplate.query(SELECT_FOLDERS_BY_LOCATION, new Object[]{id, location}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectories;
	}
	
	@Override
	public List<Directory> listDirectory(int id) {
		// TODO Auto-generated method stub
		List<Directory> listDirectories = null;
		try{
			listDirectories = jdbcTemplate.query(SELECT_FOLDERS_BY_ID, new Object[]{id, id}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectories;
	}

	@Override
	public List<DirectoryResource> getDirectoryResource(int directoryId) {
		// TODO Auto-generated method stub
		List<DirectoryResource> listDS = null;
		try{
			listDS = jdbcTemplate.query(SQL_SELECT_DR, new Object[]{directoryId}, new DirectoryResourceMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDS;
	}
	
	@Override
	public List<Directory> getDirectoryFromTrash(int clientId) {
		// TODO Auto-generated method stub
		List<Directory> listDirectory = null;
		try{
			listDirectory = jdbcTemplate.query(SELECT_FOLDER_FROM_TRASH, new Object[]{clientId}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectory;
	}
	
	@Override
	public List<Document> listFiles(int directoryId) {
		// TODO Auto-generated method stub
		List<Document> docs = null;
		try{
			docs = jdbcTemplate.query(SELECT_FILES_BY_FOLDER, new Object[]{directoryId}, new DocumentMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return docs;
	}
	
	@Override
	public List<Document> listFiles(int directoryId, String fileName) {
		// TODO Auto-generated method stub
		List<Document> docs = null;
		try{
			docs = jdbcTemplate.query(SELECT_DOUBLE_FILE, new Object[]{directoryId, fileName}, new DocumentMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return docs;
	}
	
	@Override
	public List<Document> getFile(int id) {
		// TODO Auto-generated method stub
		List<Document> docs = null;
		try{
			docs = jdbcTemplate.query(SELECT_FILE_BY_ID, new Object[]{id}, new DocumentMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return docs;
	}

	@Override
	public Integer insertDef(Directory directory) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreatorDef(directory), keyHolder);

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

	@Override
	public int updateDef(Directory directory) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE_DIRECTORY_DEF, getValuesForUpdate(directory));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return directory.getId();
	}

	@Override
	public int deleteDef(Integer id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE_DIRECTORY_DEF, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	@Override
	public List<Directory> getDirectoryDefById(Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Directory> result = jdbcTemplate.query(SQL_SELECT_DIRECTORY_DEF, primaryKey, directoryMapper);
		return result;
	}

	@Override
	public int updateLocationDef(String oldLocation, String newLocation) {
		Object[] params = new Object[] { oldLocation, newLocation };
		int result = jdbcTemplate.update(SQL_UPDATE_LOCATION_DIRECTORY_DEF, params);
		
		return result;
	}
	
	@Override
	public boolean existNameDef(final String location, final String name) {
		Object[] params = new Object[] { location, name };
		long count = jdbcTemplate.queryForObject(SQL_EXISTS_NAME_DEF, params, Long.class);
		return count > 0 ;
	}
	
	@Override
	public boolean existIdAndNameDef(final Integer id, final String name) {
		Object[] params = new Object[] { id, name };
		long count = jdbcTemplate.queryForObject(SQL_EXISTS_ID_AND_NAME_DEF, params, Long.class);
		return count > 0 ;
	}
	
	@Override
	public List<Directory> getDirectoryDefByLocation(int id, String location) {
		// TODO Auto-generated method stub
		location = location + "%";
		List<Directory> listDirectories = null;
		try{
			listDirectories = jdbcTemplate.query(SELECT_FOLDERS_DEF_BY_LOCATION, new Object[]{id, location}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listDirectories;
	}
}