package com.redsun.service.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.RolesDao;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.dao.mapper.RolesRowMapper;
import com.redsun.service.entities.Roles;

/**
 * Roles DAO implementation 
 */
@Repository
public class RolesDaoImpl implements RolesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private RolesRowMapper rolesMapper;
    
    @Autowired
    private MapsRowMapper mapsRowMapper;

    // Constant for search expression.
    private Map<String, Map<String, Object>> searchExpression = null;
    
    private Map<String, Map<String, Object>> getSearchExpreesion() {
    	if(searchExpression == null) {
    		searchExpression = new HashMap<String, Map<String, Object>>();
    		// name.
    		Map<String, Object> name = new HashMap<String, Object>();
    		name.put("sql", " or UPPER(roles.name) like UPPER(?)");
    		name.put("type", String.class);
    		name.put("param", "%{0}%");
    		searchExpression.put("name", name);
    		// project name.
    		Map<String, Object> project_name = new HashMap<String, Object>();
    		project_name.put("sql", " or UPPER(projects.name) like UPPER(?)");
    		project_name.put("type", String.class);
    		project_name.put("param", "%{0}%");
    		searchExpression.put("projectName", project_name);
    		// description.
    		Map<String, Object> description = new HashMap<String, Object>();
    		description.put("sql", " or UPPER(roles.description) like UPPER(?)");
    		description.put("type", String.class);
    		description.put("param", "%{0}%");
    		searchExpression.put("description", description);
    	}
		return searchExpression;
    }

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.NUMERIC, //default_cost numeric
			java.sql.Types.INTEGER //client_id int4
	};

	private static Logger log = Logger.getLogger(RolesDaoImpl.class);
	
	private final static String SQL_INSERT = 
		"insert into roles (name, description, sort_order, idle, default_cost, client_id) values (?, ?, ?, ?::bit, ?, ?)";

	private final static String SQL_UPDATE = 
		"update roles set name = ?, description = ?, sort_order = ?, idle = ?::bit, default_cost = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from roles where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from roles";

	private final static String SQL_COUNT = 
		"select count(*) from roles where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, description, sort_order, idle, default_cost, client_id, 0 as ext_col_count from roles where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, description, sort_order, idle, default_cost, client_id, 1 as ext_col_count, count(*) over() as total_count from roles where true";
	
	private final static String SQL_SELECT_EXTEND_PAGING = 
		"SELECT roles.id as id, roles.name as name, roles.description as description, roles.default_cost as default_cost, count(*) over() as total_count FROM roles roles WHERE true";
	private final static String SQL_SELECT_ALL = 
			"select id, name, description, sort_order, idle, default_cost, client_id, 0 as ext_col_count from roles where client_id = ?";
		
	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Roles roles) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(roles));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Roles roles)  {
		return new Object[] {
			roles.getName(), 			
			roles.getDescription(), 			
			roles.getSortOrder(), 			
			roles.getIdle(), 			
			roles.getDefaultCost(), 			
			roles.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Roles roles) {
		return new Object[] {		
			roles.getName(),
			roles.getDescription(),
			roles.getSortOrder(),
			roles.getIdle(),
			roles.getDefaultCost(),
			roles.getClientId(),
			roles.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Roles roles)  {
		return new Object[] {
			roles.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Roles roles) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(roles), keyHolder);

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
	public int update(final Roles roles) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(roles));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return roles.getId();
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
	public List<Roles> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Roles> result = jdbcTemplate.query(SQL_SELECT, primaryKey, rolesMapper);
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
	public List<Roles> listRolesForPageAndFilter(final int itemsPerPage, final int pageNo, final Roles roles) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(roles.getId() != null) {
			filterName = roles.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(roles.getName() != null) {
			filterName = roles.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(roles.getDescription() != null) {
			filterName = roles.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(roles.getSortOrder() != null) {
			filterName = roles.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(roles.getIdle() != null) {
			filterName = roles.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by default_cost.
		if(roles.getDefaultCost() != null) {
			filterName = roles.getDefaultCost().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(default_cost) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(roles.getClientId() != null) {
			filterName = roles.getClientId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(client_id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		
		if(filterSql.equals("") == false) {
			sql += " and (false" + filterSql + ")";
		}
		
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Roles> result = jdbcTemplate.query(sql, params.toArray(), rolesMapper);
		return result;
	}
	
	// List extend for page and filter.
	public List<Map<Object, Object>> listRolesExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Get the search expression object builded in.
		Map<String, Map<String, Object>> expressionBuildIn = getSearchExpreesion();
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_EXTEND_PAGING;
		List<Object> params = new ArrayList<Object>();
		// Build where expression.
		String filterSql = "";
		for(Map.Entry<String, Object> pro : filter.entrySet()) {
			if(pro.getValue().equals("") == false) {
				// Get slq and param from the searchExpression variable.
				Map<String, Object> expression = expressionBuildIn.get(pro.getKey());
				filterSql += expression.get("sql");
				if(expression.get("type") == String.class) {
					params.add("%" + pro.getValue() + "%");
				}
				else {
					params.add(pro.getValue());
				}
			}
		}

		if(filterSql.equals("") == false) {
			sql += " and (false" + filterSql + ")";
		}
		
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Map<Object, Object>> result = jdbcTemplate.query(sql, params.toArray(), mapsRowMapper);
		return result;
	}

	@Override
	public List<Roles> getAll(int clientId) {
		List<Roles> listRoles = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listRoles = jdbcTemplate.query(SQL_SELECT_ALL, params.toArray(), new RolesRowMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listRoles;
	}

}
