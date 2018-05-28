package com.redsun.service.dao.impl;

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

import com.redsun.service.dao.ResourcesDao;
import com.redsun.service.dao.mapper.ResourcesFilterMapper;
import com.redsun.service.dao.mapper.ResourcesRowMapper;
import com.redsun.service.entities.Resources;
import com.redsun.service.utils.RedSunConstants;

/**
 * Resources DAO implementation 
 */
@Repository
public class ResourcesDaoImpl implements ResourcesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ResourcesRowMapper resourcesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //code varchar(10)
			java.sql.Types.VARCHAR, //title
			java.sql.Types.VARCHAR, //idCard
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //full_name varchar(100)
			java.sql.Types.VARCHAR, //email varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.VARCHAR, //phone varchar(20)
			java.sql.Types.VARCHAR, //mobile varchar(20)
			java.sql.Types.VARCHAR, //fax varchar(20)
			java.sql.Types.VARCHAR, //address varchar(4000)
			java.sql.Types.VARCHAR, //street varchar(50)
			java.sql.Types.VARCHAR, //zip varchar(50)
			java.sql.Types.VARCHAR, //city varchar(50)
			java.sql.Types.VARCHAR, //state varchar(50)
			java.sql.Types.VARCHAR, //country varchar(50)
			java.sql.Types.DOUBLE, //salary decimal
			java.sql.Types.INTEGER, //is_user
			java.sql.Types.INTEGER, //is_resource
			java.sql.Types.INTEGER, //is_contact
			java.sql.Types.INTEGER, //is_trash
			java.sql.Types.INTEGER //client_id int4
	};
	private final static String SQL_INSERT = 
		"insert into resources (code, title, id_card, name, full_name, email, description, phone, mobile, fax, address, street, zip, city, state, country, salary, is_user, is_resource, is_contact, is_trash, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?::bit, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update resources set code=?,  title =?, id_card =?, name = ?, full_name = ?, email = ?, description = ?, phone = ?, mobile = ?, fax = ?, address = ?, street = ?, zip = ?, city = ?, state = ?, country = ?, salary = ?, is_user =  ?::bit, is_resource = ?::bit, is_contact = ?::bit, is_trash = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = "delete from resources where id = ?";
	
	private final static String SQL_RESTORE = "Update resources set is_trash = ?::bit where id = ? and client_id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from resources where client_id =  ?";

	private final static String SQL_COUNT = 
		"select count(*) from resources where id = ?";
	
	private final static String SQL_GET_TITLE = 
		"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
			+ " from resources as r"
			+ " where r.title = 'Worker' and r.client_id = ?";
	
	private final static String SQL_GET_BY_ID = 
		"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
		+ " from resources as r"
		+ " where r.client_id = ? and r.id = ? AND r.is_trash = '0'";
	private final static String SQL_SELECT_ALL = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
		+ " FROM resources as r" 
		+ " WHERE r.is_trash =?::bit AND r.client_id = ? AND r.is_contact = '0' ORDER BY r.name";
	
	private final static String SQL_SELECT_ALL_FOR_All_TYPE = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
		+ " FROM resources as r" 
		+ " WHERE r.is_trash =?::bit AND r.client_id = ? ORDER BY r.name";
	
	private final static String SQL_SELECT_TO_EXIST = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
		+ " FROM resources as r" 
		+ " WHERE r.client_id = ?  AND r.id != ? ";
	private final static String SQL_SELECT_FILTER = "(SELECT r.id , r.name, r.code, r.salary, r.is_trash, r.client_id "
							+ "FROM resources as r WHERE r.is_trash = '0') " 
							+ "EXCEPT "
							+ "(SELECT r.id , r.name, r.code, r.salary, r.is_trash, r.client_id "
							+ "FROM resources as r " 
							+ "INNER JOIN project_resources as pr ON pr.resource_id = r.id WHERE pr.project_id = ? and pr.client_id = ?)";
	private final static String SQL_GET_BY_CODE = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
				+ " FROM resources AS r"
				+ " WHERE r.code = ? AND r.client_id = ? AND r.is_trash = '0'";
			
	private final static String SQL_SELECT_PAGING = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
					+ " FROM resources as r"
					+ " WHERE r.client_id = ? AND r.is_contact = '0' AND r.is_trash = '0'";
	private final static String SQL_SELECT_EXTERIOR = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
					+ " FROM resources as r"
					+ " WHERE r.client_id = ? AND r.is_contact = '1' AND r.is_trash = '0'";

	private final static String SQL_BPMN = 
			"SELECT r.id, r.code, r.title, r.id_card, r.name, r.full_name, r.email, r.description, r.phone, r.mobile, r.fax, r.address, r.street, r.zip, r.city, r.state, r.country, r.salary, r.is_user,  r.is_resource,  r.is_contact, r.is_trash, r.client_id, count(*) over() as total_count "
				+ " FROM resources AS r"
				+ " WHERE r.code = ? AND r.is_trash = '0'";
			
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Resources resources) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(resources));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Resources resources)  {
		return new Object[] {
			resources.getCode(),
			resources.getTitle(),
			resources.getIdCard(),
			resources.getName(), 			
			resources.getFullName(), 			
			resources.getEmail(), 			
			resources.getDescription(), 			
			resources.getPhone(), 			
			resources.getMobile(), 			
			resources.getFax(), 			
			resources.getAddress(), 			
			resources.getStreet(), 			
			resources.getZip(), 			
			resources.getCity(), 			
			resources.getState(), 			
			resources.getCountry(), 
			resources.getSalary(),
			resources.getIsUser(),
			resources.getIsResource(),
			resources.getIsContact(),
			resources.getIsTrash(),
			resources.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Resources resources) {
		return new Object[] {	
			resources.getCode(),
			resources.getTitle(),
			resources.getIdCard(),
			resources.getName(),
			resources.getFullName(),
			resources.getEmail(),
			resources.getDescription(),
			resources.getPhone(),
			resources.getMobile(),
			resources.getFax(),
			resources.getAddress(),
			resources.getStreet(),
			resources.getZip(),
			resources.getCity(),
			resources.getState(),
			resources.getCountry(),
			resources.getSalary(),
			resources.getIsUser(),
			resources.getIsResource(),
			resources.getIsContact(),
			resources.getIsTrash(),
			resources.getClientId(),
			resources.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Resources resources)  {
		return new Object[] {
			resources.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Resources resources) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(resources), keyHolder);

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
	public int update(final Resources resources) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(resources));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return resources.getId();
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
	public int restore(final Integer resourceId, final Integer clientId) {
		Object[] primaryKey = new Object[] { RedSunConstants.NOT_IN_TRASH_FLAT, resourceId, clientId };
		int result = jdbcTemplate.update(SQL_RESTORE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after Restore : " + result + " (0 or 1 expected) ");
		}
		return result ;
	}

	// Get By Id.
	@Override
	public Resources getById(int clientId, int id) {
		Object[] primaryKey = new Object[] { clientId, id };
		try {
			return jdbcTemplate.queryForObject(SQL_GET_BY_ID, primaryKey, resourcesMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
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
	public long count(int clientId) {
		Object[] primaryKey = new Object[] { clientId };
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, primaryKey, Long.class);
	}

	// ToString.
	protected String toString(final Object[] objects) {
		if (objects != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			int i = 0 ;
			for (Object o : objects ) {
				if ( i > 0 ) {
					sb.append(", ");
				}
				sb.append(o.toString());
				i++;
			}
			sb.append(")");
			return sb.toString();
		}
		else {
			return "null";
		}
	}

	// List for page and filter.
	public List<Resources> listResourcesForPageAndFilter(final int clientId, final int itemsPerPage, final int pageNo) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		// Paging.
		sql += " ORDER BY r.name ASC limit ? offset ?";
		params.add(clientId);
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Resources> result = jdbcTemplate.query(sql, params.toArray(), resourcesMapper);
		return result;
	}

	@Override
	public List<Resources> getAll(Integer clientId, Integer... deletedFlag) {
		int deletedStatus = 0;
		if(deletedFlag != null && deletedFlag.length > 0){
			deletedStatus = deletedFlag[0].intValue();
		}
		Object[] primaryKey = new Object[] { deletedStatus, clientId };
		List<Resources> result = jdbcTemplate.query(SQL_SELECT_ALL, primaryKey, resourcesMapper);
		return result;
	}
	
	@Override
	public List<Resources> getAllForAllType(Integer clientId, Integer... deletedFlag) {
		int deletedStatus = 0;
		if(deletedFlag != null && deletedFlag.length > 0){
			deletedStatus = deletedFlag[0].intValue();
		}
		Object[] primaryKey = new Object[] { deletedStatus, clientId };
		List<Resources> result = jdbcTemplate.query(SQL_SELECT_ALL_FOR_All_TYPE, primaryKey, resourcesMapper);
		return result;
	}

	@Override
	public List<Resources> filterMember(int projectId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		List<Resources> result = jdbcTemplate.query(SQL_SELECT_FILTER, params.toArray(), new ResourcesFilterMapper());
		return result;
	}

	@Override
	public List<Resources> listResourcesExterior(int clientId, int itemsPerPage, int pageNo) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_EXTERIOR;
		List<Object> params = new ArrayList<Object>();
		
		// Paging.
		sql += " ORDER BY r.name ASC limit ? offset ?";
		params.add(clientId);
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Resources> result = jdbcTemplate.query(sql, params.toArray(), resourcesMapper);
		return result;
	}
	
	@Override
	public List<Resources> getByCode(String code, int clientId) {
		Object[] primaryKey = new Object[] { code, clientId };
		return jdbcTemplate.query(SQL_GET_BY_CODE, primaryKey, resourcesMapper);
	}

	@Override
	public List<Resources> filterTitle(int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		List<Resources> result = jdbcTemplate.query(SQL_GET_TITLE, params.toArray(), resourcesMapper);
		return result;
	}

	@Override
	public List<Resources> getToExist(int clientId, int resourceId) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(resourceId);
		List<Resources> result = jdbcTemplate.query(SQL_SELECT_TO_EXIST, params.toArray(), resourcesMapper);
		return result;
	}
	
	@Override
	public List<Resources> getForBPMN(String code) {
		Object[] primaryKey = new Object[] { code };
		return jdbcTemplate.query(SQL_BPMN, primaryKey, resourcesMapper);
	}
}
