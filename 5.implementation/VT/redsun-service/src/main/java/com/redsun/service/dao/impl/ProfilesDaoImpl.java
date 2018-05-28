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

import com.redsun.service.dao.ProfilesDao;
import com.redsun.service.dao.mapper.ProfilesRowMapper;
import com.redsun.service.entities.Profiles;

/**
 * Profiles DAO implementation 
 */
@Repository
public class ProfilesDaoImpl implements ProfilesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ProfilesRowMapper profilesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.VARCHAR, //profile_code varchar(3)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into profiles (name, description, profile_code, sort_order, idle, client_id) values (?, ?, ?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update profiles set name = ?, description = ?, profile_code = ?, sort_order = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from profiles where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from profiles";

	private final static String SQL_COUNT = 
		"select count(*) from profiles where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, description, profile_code, sort_order, idle, client_id, 0 as ext_col_count from profiles where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, description, profile_code, sort_order, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from profiles where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Profiles profiles) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(profiles));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Profiles profiles)  {
		return new Object[] {
			profiles.getName(), 			
			profiles.getDescription(), 			
			profiles.getProfileCode(), 			
			profiles.getSortOrder(), 			
			profiles.getIdle(), 			
			profiles.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Profiles profiles) {
		return new Object[] {		
			profiles.getName(),
			profiles.getDescription(),
			profiles.getProfileCode(),
			profiles.getSortOrder(),
			profiles.getIdle(),
			profiles.getClientId(),
			profiles.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Profiles profiles)  {
		return new Object[] {
			profiles.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Profiles profiles) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(profiles), keyHolder);

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
	public int update(final Profiles profiles) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(profiles));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return profiles.getId();
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
	public List<Profiles> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Profiles> result = jdbcTemplate.query(SQL_SELECT, primaryKey, profilesMapper);
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
	public List<Profiles> listProfilesForPageAndFilter(final int itemsPerPage, final int pageNo, final Profiles profiles) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(profiles.getId() != null) {
			filterName = profiles.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(profiles.getName() != null) {
			filterName = profiles.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(profiles.getDescription() != null) {
			filterName = profiles.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by profile_code.
		if(profiles.getProfileCode() != null) {
			filterName = profiles.getProfileCode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(profile_code) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(profiles.getSortOrder() != null) {
			filterName = profiles.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(profiles.getIdle() != null) {
			filterName = profiles.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(profiles.getClientId() != null) {
			filterName = profiles.getClientId().toString();
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
		
		List<Profiles> result = jdbcTemplate.query(sql, params.toArray(), profilesMapper);
		return result;
	}

}
