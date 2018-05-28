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

import com.redsun.service.dao.TeamsDao;
import com.redsun.service.dao.mapper.TeamsRowMapper;
import com.redsun.service.entities.Teams;

/**
 * Teams DAO implementation 
 */
@Repository
public class TeamsDaoImpl implements TeamsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private TeamsRowMapper teamsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into teams (name, description, idle, id_resource, client_id) values (?, ?, ?::bit, ?, ?)";

	private final static String SQL_UPDATE = 
		"update teams set name = ?, description = ?, idle = ?::bit, id_resource = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from teams where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from teams";

	private final static String SQL_COUNT = 
		"select count(*) from teams where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, description, idle, id_resource, client_id, 0 as ext_col_count from teams where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, description, idle, id_resource, client_id, 1 as ext_col_count, count(*) over() as total_count from teams where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Teams teams) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(teams));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Teams teams)  {
		return new Object[] {
			teams.getName(), 			
			teams.getDescription(), 			
			teams.getIdle(), 			
			teams.getIdResource(), 			
			teams.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Teams teams) {
		return new Object[] {		
			teams.getName(),
			teams.getDescription(),
			teams.getIdle(),
			teams.getIdResource(),
			teams.getClientId(),
			teams.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Teams teams)  {
		return new Object[] {
			teams.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Teams teams) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(teams), keyHolder);

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
	public int update(final Teams teams) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(teams));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return teams.getId();
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
	public List<Teams> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Teams> result = jdbcTemplate.query(SQL_SELECT, primaryKey, teamsMapper);
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
	public List<Teams> listTeamsForPageAndFilter(final int itemsPerPage, final int pageNo, final Teams teams) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(teams.getId() != null) {
			filterName = teams.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(teams.getName() != null) {
			filterName = teams.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(teams.getDescription() != null) {
			filterName = teams.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(teams.getIdle() != null) {
			filterName = teams.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(teams.getIdResource() != null) {
			filterName = teams.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(teams.getClientId() != null) {
			filterName = teams.getClientId().toString();
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
		
		List<Teams> result = jdbcTemplate.query(sql, params.toArray(), teamsMapper);
		return result;
	}

}
