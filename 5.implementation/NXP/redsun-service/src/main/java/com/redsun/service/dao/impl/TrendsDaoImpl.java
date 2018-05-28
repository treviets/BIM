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

import com.redsun.service.dao.TrendsDao;
import com.redsun.service.dao.mapper.TrendsRowMapper;
import com.redsun.service.entities.Trends;

/**
 * Trends DAO implementation 
 */
@Repository
public class TrendsDaoImpl implements TrendsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private TrendsRowMapper trendsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //color varchar(7)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.VARCHAR, //icon varchar(100)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into trends (name, color, sort_order, icon, idle, client_id) values (?, ?, ?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update trends set name = ?, color = ?, sort_order = ?, icon = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from trends where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from trends";

	private final static String SQL_COUNT = 
		"select count(*) from trends where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, color, sort_order, icon, idle, client_id, 0 as ext_col_count from trends where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, color, sort_order, icon, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from trends where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Trends trends) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(trends));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Trends trends)  {
		return new Object[] {
			trends.getName(), 			
			trends.getColor(), 			
			trends.getSortOrder(), 			
			trends.getIcon(), 			
			trends.getIdle(), 			
			trends.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Trends trends) {
		return new Object[] {		
			trends.getName(),
			trends.getColor(),
			trends.getSortOrder(),
			trends.getIcon(),
			trends.getIdle(),
			trends.getClientId(),
			trends.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Trends trends)  {
		return new Object[] {
			trends.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Trends trends) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(trends), keyHolder);

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
	public int update(final Trends trends) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(trends));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return trends.getId();
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
	public List<Trends> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Trends> result = jdbcTemplate.query(SQL_SELECT, primaryKey, trendsMapper);
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
	public List<Trends> listTrendsForPageAndFilter(final int itemsPerPage, final int pageNo, final Trends trends) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(trends.getId() != null) {
			filterName = trends.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(trends.getName() != null) {
			filterName = trends.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by color.
		if(trends.getColor() != null) {
			filterName = trends.getColor().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(color) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(trends.getSortOrder() != null) {
			filterName = trends.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by icon.
		if(trends.getIcon() != null) {
			filterName = trends.getIcon().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(icon) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(trends.getIdle() != null) {
			filterName = trends.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(trends.getClientId() != null) {
			filterName = trends.getClientId().toString();
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
		
		List<Trends> result = jdbcTemplate.query(sql, params.toArray(), trendsMapper);
		return result;
	}

}
