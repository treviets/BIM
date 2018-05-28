package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TypesDao;
import com.redsun.service.dao.mapper.TypesRowMapper;
import com.redsun.service.entities.Types;

/**
 * Types DAO implementation 
 */
@Repository
public class TypesDaoImpl implements TypesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
    @Autowired
    private TypesRowMapper typesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //scope varchar(100)
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.VARCHAR, //color varchar(7)
			java.sql.Types.INTEGER, //id_workflow int4
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //id_planning_mode int4
			java.sql.Types.INTEGER, //id_category int4
			java.sql.Types.INTEGER //client_id int4
	};

	private static Logger log = Logger.getLogger(TypesDaoImpl.class);

	private final static String SQL_INSERT = 
		"insert into types (scope, name, sort_order, idle, color, id_workflow, mandatory_description, mandatory_result_on_done, mandatory_resource_on_handled, lock_handled, lock_done, lock_idle, code, internal_data, description, lock_cancelled, show_in_flash, id_planning_mode, mandatory_resolution_on_done, lock_solved, id_category, lock_no_left_on_done, client_id) values (?, ?, ?, ?::bit, ?, ?, ?::bit, ?::bit, ?::bit, ?::bit, ?::bit, ?::bit, ?, ?, ?, ?::bit, ?::bit, ?, ?::bit, ?::bit, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update types set scope = ?, name = ?, sort_order = ?, idle = ?::bit, color = ?, id_workflow = ?, mandatory_description = ?::bit, mandatory_result_on_done = ?::bit, mandatory_resource_on_handled = ?::bit, lock_handled = ?::bit, lock_done = ?::bit, lock_idle = ?::bit, code = ?, internal_data = ?, description = ?, lock_cancelled = ?::bit, show_in_flash = ?::bit, id_planning_mode = ?, mandatory_resolution_on_done = ?::bit, lock_solved = ?::bit, id_category = ?, lock_no_left_on_done = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from types where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from types";

	private final static String SQL_COUNT = 
		"select count(*) from types where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, scope, name, sort_order, idle, color, id_workflow, mandatory_description, mandatory_result_on_done, mandatory_resource_on_handled, lock_handled, lock_done, lock_idle, code, internal_data, description, lock_cancelled, show_in_flash, id_planning_mode, mandatory_resolution_on_done, lock_solved, id_category, lock_no_left_on_done, client_id, 0 as ext_col_count from types where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, scope, name, sort_order, idle, color, id_workflow, mandatory_description, mandatory_result_on_done, mandatory_resource_on_handled, lock_handled, lock_done, lock_idle, code, internal_data, description, lock_cancelled, show_in_flash, id_planning_mode, mandatory_resolution_on_done, lock_solved, id_category, lock_no_left_on_done, client_id, 1 as ext_col_count, count(*) over() as total_count from types where true";

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Types types) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(types));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Types types)  {
		return new Object[] {
			types.getScope(), 			
			types.getName(), 			
			types.getSortOrder(), 			
			types.getColor(), 			
			types.getIdWorkflow(), 			
			types.getDescription(), 			
			types.getIdPlanningMode(), 			
			types.getIdCategory(), 			
			types.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Types types) {
		return new Object[] {		
			types.getScope(),
			types.getName(),
			types.getSortOrder(),
			types.getColor(),
			types.getIdWorkflow(),
			types.getDescription(),
			types.getIdPlanningMode(),
			types.getIdCategory(),
			types.getClientId(),
			types.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Types types)  {
		return new Object[] {
			types.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Types types) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(types), keyHolder);

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
	public int update(final Types types) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(types));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return types.getId();
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
	public List<Types> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Types> result = jdbcTemplate.query(SQL_SELECT, primaryKey, typesMapper);
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
	public List<Types> listTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final Types types) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Types> result = jdbcTemplate.query(sql, params.toArray(), typesMapper);
		return result;
	}

	@Override
	public List<Types> listAllTypes(int clientId, String scope) {
		List<Types> listTypes = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		 String SQL_SELECT_ALL_FOR_TYPE = 
					"select id, scope, name, sort_order, color, id_workflow,"
					+ " description, id_planning_mode, id_category, client_id, 0 as ext_col_count"
					+ " from types where scope = "+ "'"+scope+"' and client_id = ?";
		try {
			listTypes = jdbcTemplate.query(SQL_SELECT_ALL_FOR_TYPE, params.toArray(), new TypesRowMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTypes;
	}

}
