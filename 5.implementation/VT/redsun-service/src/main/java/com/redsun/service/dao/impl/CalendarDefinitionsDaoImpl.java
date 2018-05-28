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

import com.redsun.service.dao.CalendarDefinitionsDao;
import com.redsun.service.dao.mapper.CalendarDefinitionsRowMapper;
import com.redsun.service.entities.CalendarDefinitions;

/**
 * CalendarDefinitions DAO implementation 
 */
@Repository
public class CalendarDefinitionsDaoImpl implements CalendarDefinitionsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private CalendarDefinitionsRowMapper calendarDefinitionsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into calendar_definitions (name, sort_order, idle, client_id) values (?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update calendar_definitions set name = ?, sort_order = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from calendar_definitions where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from calendar_definitions";

	private final static String SQL_COUNT = 
		"select count(*) from calendar_definitions where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, sort_order, idle, client_id, 0 as ext_col_count from calendar_definitions where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, sort_order, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from calendar_definitions where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final CalendarDefinitions calendarDefinitions) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(calendarDefinitions));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final CalendarDefinitions calendarDefinitions)  {
		return new Object[] {
			calendarDefinitions.getName(), 			
			calendarDefinitions.getSortOrder(), 			
			calendarDefinitions.getIdle(), 			
			calendarDefinitions.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final CalendarDefinitions calendarDefinitions) {
		return new Object[] {		
			calendarDefinitions.getName(),
			calendarDefinitions.getSortOrder(),
			calendarDefinitions.getIdle(),
			calendarDefinitions.getClientId(),
			calendarDefinitions.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final CalendarDefinitions calendarDefinitions)  {
		return new Object[] {
			calendarDefinitions.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final CalendarDefinitions calendarDefinitions) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(calendarDefinitions), keyHolder);

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
	public int update(final CalendarDefinitions calendarDefinitions) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(calendarDefinitions));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return calendarDefinitions.getId();
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
	public List<CalendarDefinitions> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<CalendarDefinitions> result = jdbcTemplate.query(SQL_SELECT, primaryKey, calendarDefinitionsMapper);
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
	public List<CalendarDefinitions> listCalendarDefinitionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CalendarDefinitions calendarDefinitions) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(calendarDefinitions.getId() != null) {
			filterName = calendarDefinitions.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(calendarDefinitions.getName() != null) {
			filterName = calendarDefinitions.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(calendarDefinitions.getSortOrder() != null) {
			filterName = calendarDefinitions.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(calendarDefinitions.getIdle() != null) {
			filterName = calendarDefinitions.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(calendarDefinitions.getClientId() != null) {
			filterName = calendarDefinitions.getClientId().toString();
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
		
		List<CalendarDefinitions> result = jdbcTemplate.query(sql, params.toArray(), calendarDefinitionsMapper);
		return result;
	}

}
