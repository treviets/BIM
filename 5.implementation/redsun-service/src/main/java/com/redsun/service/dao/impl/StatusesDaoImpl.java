package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.StatusesDao;
import com.redsun.service.dao.mapper.StatusesRowMapper;
import com.redsun.service.entities.Statuses;

/**
 * Statuses DAO implementation 
 */
@Repository
public class StatusesDaoImpl implements StatusesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private StatusesRowMapper statusesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //scope
			java.sql.Types.VARCHAR, //color varchar(7)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into statuses (name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, client_id) values (?, ?::bit, ?, ?, ?, ?::bit, ?::bit, ?::bit, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update statuses set name = ?, set_done_status = ?::bit, set_idle_status = ?, color = ?, sort_order = ?, idle = ?::bit, set_handled_status = ?::bit, is_copy_status = ?::bit, set_cancelled_status = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from statuses where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from statuses";

	private final static String SQL_COUNT = 
		"select count(*) from statuses where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, client_id, 0 as ext_col_count from statuses where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, client_id, 1 as ext_col_count, count(*) over() as total_count from statuses where true";
	private final static String SQL_SELECT_ALL = 
			"select id, name, scope, color, sort_order, client_id, 0 as ext_col_count from statuses where client_id =  ?";
	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Statuses statuses) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(statuses));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Statuses statuses)  {
		return new Object[] {
			statuses.getName(), 
			statuses.getScope(),
			statuses.getColor(), 			
			statuses.getSortOrder(), 			
			statuses.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Statuses statuses) {
		return new Object[] {		
				statuses.getName(), 
				statuses.getScope(),
				statuses.getColor(), 			
				statuses.getSortOrder(), 			
				statuses.getClientId(),
			statuses.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Statuses statuses)  {
		return new Object[] {
			statuses.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Statuses statuses) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(statuses), keyHolder);

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
	public int update(final Statuses statuses) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(statuses));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return statuses.getId();
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
	public List<Statuses> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Statuses> result = jdbcTemplate.query(SQL_SELECT, primaryKey, statusesMapper);
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
	public List<Statuses> listStatusesForPageAndFilter(final int itemsPerPage, final int pageNo, final Statuses statuses) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Statuses> result = jdbcTemplate.query(sql, params.toArray(), statusesMapper);
		return result;
	}

	@Override
	public List<Statuses> listAllStatuses(int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		List<Statuses> result = jdbcTemplate.query(SQL_SELECT_ALL, params.toArray(), statusesMapper);
		return result;
	}

	@Override
	public List<Statuses> getByScope(int clientId, String scope) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
	 String SQL_SELECT_ALL_FOR_TASK = 
				"select id, name, scope, color, sort_order, client_id, 0 as ext_col_count from statuses where scope ='"+scope+"' and client_id =  ?";
		
		List<Statuses> result = jdbcTemplate.query(SQL_SELECT_ALL_FOR_TASK, params.toArray(), statusesMapper);
		return result;
	}

}
