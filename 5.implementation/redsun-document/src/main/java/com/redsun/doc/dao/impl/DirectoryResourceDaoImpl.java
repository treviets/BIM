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

import com.redsun.doc.dao.DirectoryDao;
import com.redsun.doc.dao.DirectoryResourceDao;
import com.redsun.doc.dao.mapper.DirectoryMapper;
import com.redsun.doc.dao.mapper.DocumentMapper;
import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Document;


@Repository
public class DirectoryResourceDaoImpl implements DirectoryResourceDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private DirectoryMapper directoryMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";
	
	private final static int[] ASSIGN_RESOURCE_SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //directory_id integer
			java.sql.Types.INTEGER, //project_id integer
			java.sql.Types.INTEGER, //resource_id integer
			java.sql.Types.INTEGER, //client_id int4
			java.sql.Types.VARCHAR //create_user_name varchar(45)
	};

	private final static String SQL_INSERT_ASSIGN_RESOURCE = 
			"insert into directory_resources (directory_id, project_id, resource_id, client_id, user_name) values (?, ?, ?, ?, ?)";
	
	private final static String SQL_DELETE = 
			"delete from directory_resources where resource_id = ?";
	
	private final static String SQL_UPDATE_FOLDER_ASSIGNED_TO_TRASH_OP1 = 
			"update directories set trash = 1 where id = ? and create_user_name = ?";
	
	private final static String SQL_UPDATE_FOLDER_ASSIGNED_TO_TRASH_OP2 = 
			"update directories set trash = 1 where create_user_name = ?";

	static private String SELECT_DIRECTORY_ASSIGNED = "SELECT distinct 1 id, '' as name, '' as location, 1 parent_id, 0 project_id, 0 client_id, null as create_date_time, null as update_date_time, d.create_user_name FROM directories d"
			+ " WHERE d.client_id = ? AND d.trash = 0 AND d.create_user_name != 'admin'"
			+ " UNION"
			+ " SELECT distinct 1 id, '' as name, '' as location, 1 parent_id, 0 project_id, 0 client_id, null as create_date_time, null as update_date_time, user_name as create_user_name FROM directories d"
			+ " LEFT JOIN directory_resources dr ON d.id = dr.directory_id"
			+ " WHERE d.client_id = ? AND d.trash = 0 AND user_name !='admin'";
	
	static private String SELECT_DIRECTORY_ASSIGNED_BY_ID = "SELECT distinct 1 id, '' as name, '' as location, 0 parent_id, 0 project_id, 0 client_id, null as create_date_time, null as update_date_time, d.create_user_name FROM directories d"
			+ " WHERE d.client_id = ? AND d.id = ?"
			+ " UNION"
			+ " SELECT distinct 1 id, '' as name, '' as location, dr.resource_id parent_id, 0 project_id, 0 client_id, null as create_date_time, null as update_date_time, user_name as create_user_name FROM directories d"
			+ " LEFT JOIN directory_resources dr ON d.id = dr.directory_id"
			+ " WHERE d.client_id = ? AND d.id = ?";
	
	private static Logger log = Logger.getLogger(DirectoryResourceDaoImpl.class);

	

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final DirectoryResource directoryResource) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT_ASSIGN_RESOURCE, ASSIGN_RESOURCE_SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(directoryResource));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final DirectoryResource directoryResource)  {
		return new Object[] {
			directoryResource.getDirectoryId(), 					
			directoryResource.getProjectId(),
			directoryResource.getResourceId(),
			directoryResource.getClientId(), 
			directoryResource.getUserName()
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final DirectoryResource directoryResource) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(directoryResource), keyHolder);

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
	public List<Directory> getResourceAssigned(int clientId){
		List<Directory> listResourceAssigned = null;
		try{
			listResourceAssigned = jdbcTemplate.query(SELECT_DIRECTORY_ASSIGNED, new Object[]{clientId, clientId}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listResourceAssigned;
	}
	
	@Override
	public List<Directory> getResourceAssignedById(int clientId, int directoryId){
		List<Directory> listResourceAssignedById = null;
		try{
			listResourceAssignedById = jdbcTemplate.query(SELECT_DIRECTORY_ASSIGNED_BY_ID, new Object[]{clientId, directoryId, clientId, directoryId}, new DirectoryMapper());
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return listResourceAssignedById;
	}

	@Override
	public int deleteDirectoryResource(Integer resourceId) {
		// TODO Auto-generated method stub
		Object[] primaryKey = new Object[] { resourceId };
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		return resourceId ;
	}
	
	@Override
	public int deleteFolderAssignedToTrash(final int directoryId, final String userName) {
		if(directoryId == 0){
			Object[] params = new Object[] { userName };
			int result = jdbcTemplate.update(SQL_UPDATE_FOLDER_ASSIGNED_TO_TRASH_OP2, params);
			return result;
		}
		else{
			Object[] params = new Object[] { directoryId, userName };
			int result = jdbcTemplate.update(SQL_UPDATE_FOLDER_ASSIGNED_TO_TRASH_OP1, params);
			return result;
		}
		
	}
}