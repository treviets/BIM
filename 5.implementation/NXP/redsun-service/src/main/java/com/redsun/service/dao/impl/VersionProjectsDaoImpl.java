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

import com.redsun.service.dao.VersionProjectsDao;
import com.redsun.service.dao.mapper.VersionProjectsRowMapper;
import com.redsun.service.entities.VersionProjects;

/**
 * VersionProjects DAO implementation 
 */
@Repository
public class VersionProjectsDaoImpl implements VersionProjectsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private VersionProjectsRowMapper versionProjectsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_version int4
			java.sql.Types.DATE, //start_date date
			java.sql.Types.DATE, //end_date date
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into version_projects (id_project, id_version, start_date, end_date, idle, client_id) values (?, ?, ?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update version_projects set id_project = ?, id_version = ?, start_date = ?, end_date = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from version_projects where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from version_projects";

	private final static String SQL_COUNT = 
		"select count(*) from version_projects where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, id_version, start_date, end_date, idle, client_id, 0 as ext_col_count from version_projects where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_project, id_version, start_date, end_date, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from version_projects where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final VersionProjects versionProjects) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(versionProjects));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final VersionProjects versionProjects)  {
		return new Object[] {
			versionProjects.getIdProject(), 			
			versionProjects.getIdVersion(), 			
			versionProjects.getStartDate(), 			
			versionProjects.getEndDate(), 			
			versionProjects.getIdle(), 			
			versionProjects.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final VersionProjects versionProjects) {
		return new Object[] {		
			versionProjects.getIdProject(),
			versionProjects.getIdVersion(),
			versionProjects.getStartDate(),
			versionProjects.getEndDate(),
			versionProjects.getIdle(),
			versionProjects.getClientId(),
			versionProjects.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final VersionProjects versionProjects)  {
		return new Object[] {
			versionProjects.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final VersionProjects versionProjects) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(versionProjects), keyHolder);

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
	public int update(final VersionProjects versionProjects) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(versionProjects));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return versionProjects.getId();
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
	public List<VersionProjects> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<VersionProjects> result = jdbcTemplate.query(SQL_SELECT, primaryKey, versionProjectsMapper);
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
	public List<VersionProjects> listVersionProjectsForPageAndFilter(final int itemsPerPage, final int pageNo, final VersionProjects versionProjects) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(versionProjects.getId() != null) {
			filterName = versionProjects.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_project.
		if(versionProjects.getIdProject() != null) {
			filterName = versionProjects.getIdProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_version.
		if(versionProjects.getIdVersion() != null) {
			filterName = versionProjects.getIdVersion().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_version) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by start_date.
		if(versionProjects.getStartDate() != null) {
			filterName = versionProjects.getStartDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(start_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by end_date.
		if(versionProjects.getEndDate() != null) {
			filterName = versionProjects.getEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(versionProjects.getIdle() != null) {
			filterName = versionProjects.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(versionProjects.getClientId() != null) {
			filterName = versionProjects.getClientId().toString();
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
		
		List<VersionProjects> result = jdbcTemplate.query(sql, params.toArray(), versionProjectsMapper);
		return result;
	}

}
