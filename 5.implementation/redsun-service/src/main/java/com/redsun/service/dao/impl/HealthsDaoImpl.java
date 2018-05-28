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

import com.redsun.service.dao.HealthsDao;
import com.redsun.service.dao.mapper.HealthsRowMapper;
import com.redsun.service.entities.Healths;

/**
 * Healths DAO implementation 
 */
@Repository
public class HealthsDaoImpl implements HealthsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private HealthsRowMapper healthsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //color varchar(7)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.VARCHAR, //icon varchar(100)
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into healths (name, color, sort_order, idle, icon, client_id) values (?, ?, ?, ?::bit, ?, ?)";

	private final static String SQL_UPDATE = 
		"update healths set name = ?, color = ?, sort_order = ?, idle = ?::bit, icon = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from healths where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from healths";

	private final static String SQL_COUNT = 
		"select count(*) from healths where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, color, sort_order, idle, icon, client_id, 0 as ext_col_count from healths where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, color, sort_order, idle, icon, client_id, 1 as ext_col_count, count(*) over() as total_count from healths where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Healths healths) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(healths));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Healths healths)  {
		return new Object[] {
			healths.getName(), 			
			healths.getColor(), 			
			healths.getSortOrder(), 			
			healths.getIdle(), 			
			healths.getIcon(), 			
			healths.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Healths healths) {
		return new Object[] {		
			healths.getName(),
			healths.getColor(),
			healths.getSortOrder(),
			healths.getIdle(),
			healths.getIcon(),
			healths.getClientId(),
			healths.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Healths healths)  {
		return new Object[] {
			healths.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Healths healths) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(healths), keyHolder);

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
	public int update(final Healths healths) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(healths));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return healths.getId();
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
	public List<Healths> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Healths> result = jdbcTemplate.query(SQL_SELECT, primaryKey, healthsMapper);
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
	public List<Healths> listHealthsForPageAndFilter(final int itemsPerPage, final int pageNo, final Healths healths) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(healths.getId() != null) {
			filterName = healths.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(healths.getName() != null) {
			filterName = healths.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by color.
		if(healths.getColor() != null) {
			filterName = healths.getColor().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(color) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(healths.getSortOrder() != null) {
			filterName = healths.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(healths.getIdle() != null) {
			filterName = healths.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by icon.
		if(healths.getIcon() != null) {
			filterName = healths.getIcon().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(icon) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(healths.getClientId() != null) {
			filterName = healths.getClientId().toString();
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
		
		List<Healths> result = jdbcTemplate.query(sql, params.toArray(), healthsMapper);
		return result;
	}

}
