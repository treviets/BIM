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

import com.redsun.service.dao.QualitiesDao;
import com.redsun.service.dao.mapper.QualitiesRowMapper;
import com.redsun.service.entities.Qualities;

/**
 * Qualities DAO implementation 
 */
@Repository
public class QualitiesDaoImpl implements QualitiesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private QualitiesRowMapper qualitiesMapper;

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
		"insert into qualities (name, color, sort_order, icon, idle, client_id) values (?, ?, ?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update qualities set name = ?, color = ?, sort_order = ?, icon = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from qualities where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from qualities";

	private final static String SQL_COUNT = 
		"select count(*) from qualities where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, color, sort_order, icon, idle, client_id, 0 as ext_col_count from qualities where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, color, sort_order, icon, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from qualities where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Qualities qualities) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(qualities));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Qualities qualities)  {
		return new Object[] {
			qualities.getName(), 			
			qualities.getColor(), 			
			qualities.getSortOrder(), 			
			qualities.getIcon(), 			
			qualities.getIdle(), 			
			qualities.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Qualities qualities) {
		return new Object[] {		
			qualities.getName(),
			qualities.getColor(),
			qualities.getSortOrder(),
			qualities.getIcon(),
			qualities.getIdle(),
			qualities.getClientId(),
			qualities.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Qualities qualities)  {
		return new Object[] {
			qualities.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Qualities qualities) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(qualities), keyHolder);

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
	public int update(final Qualities qualities) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(qualities));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return qualities.getId();
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
	public List<Qualities> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Qualities> result = jdbcTemplate.query(SQL_SELECT, primaryKey, qualitiesMapper);
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
	public List<Qualities> listQualitiesForPageAndFilter(final int itemsPerPage, final int pageNo, final Qualities qualities) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(qualities.getId() != null) {
			filterName = qualities.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(qualities.getName() != null) {
			filterName = qualities.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by color.
		if(qualities.getColor() != null) {
			filterName = qualities.getColor().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(color) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(qualities.getSortOrder() != null) {
			filterName = qualities.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by icon.
		if(qualities.getIcon() != null) {
			filterName = qualities.getIcon().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(icon) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(qualities.getIdle() != null) {
			filterName = qualities.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(qualities.getClientId() != null) {
			filterName = qualities.getClientId().toString();
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
		
		List<Qualities> result = jdbcTemplate.query(sql, params.toArray(), qualitiesMapper);
		return result;
	}

}
