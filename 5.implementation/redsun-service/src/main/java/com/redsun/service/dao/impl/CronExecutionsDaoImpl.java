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

import com.redsun.service.dao.CronExecutionsDao;
import com.redsun.service.dao.mapper.CronExecutionsRowMapper;
import com.redsun.service.entities.CronExecutions;

/**
 * CronExecutions DAO implementation 
 */
@Repository
public class CronExecutionsDaoImpl implements CronExecutionsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private CronExecutionsRowMapper cronExecutionsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //cron varchar(100)
			java.sql.Types.VARCHAR, //file_executed varchar(500)
			java.sql.Types.INTEGER, //idle int4
			java.sql.Types.VARCHAR, //fonction_name varchar(256)
			java.sql.Types.VARCHAR, //next_time varchar(64)
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into cron_executions (cron, file_executed, idle, fonction_name, next_time, client_id) values (?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update cron_executions set cron = ?, file_executed = ?, idle = ?, fonction_name = ?, next_time = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from cron_executions where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from cron_executions";

	private final static String SQL_COUNT = 
		"select count(*) from cron_executions where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, cron, file_executed, idle, fonction_name, next_time, client_id, 0 as ext_col_count from cron_executions where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, cron, file_executed, idle, fonction_name, next_time, client_id, 1 as ext_col_count, count(*) over() as total_count from cron_executions where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final CronExecutions cronExecutions) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(cronExecutions));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final CronExecutions cronExecutions)  {
		return new Object[] {
			cronExecutions.getCron(), 			
			cronExecutions.getFileExecuted(), 			
			cronExecutions.getIdle(), 			
			cronExecutions.getFonctionName(), 			
			cronExecutions.getNextTime(), 			
			cronExecutions.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final CronExecutions cronExecutions) {
		return new Object[] {		
			cronExecutions.getCron(),
			cronExecutions.getFileExecuted(),
			cronExecutions.getIdle(),
			cronExecutions.getFonctionName(),
			cronExecutions.getNextTime(),
			cronExecutions.getClientId(),
			cronExecutions.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final CronExecutions cronExecutions)  {
		return new Object[] {
			cronExecutions.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final CronExecutions cronExecutions) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(cronExecutions), keyHolder);

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
	public int update(final CronExecutions cronExecutions) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(cronExecutions));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return cronExecutions.getId();
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
	public List<CronExecutions> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<CronExecutions> result = jdbcTemplate.query(SQL_SELECT, primaryKey, cronExecutionsMapper);
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
	public List<CronExecutions> listCronExecutionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CronExecutions cronExecutions) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(cronExecutions.getId() != null) {
			filterName = cronExecutions.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by cron.
		if(cronExecutions.getCron() != null) {
			filterName = cronExecutions.getCron().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(cron) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by file_executed.
		if(cronExecutions.getFileExecuted() != null) {
			filterName = cronExecutions.getFileExecuted().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(file_executed) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(cronExecutions.getIdle() != null) {
			filterName = cronExecutions.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by fonction_name.
		if(cronExecutions.getFonctionName() != null) {
			filterName = cronExecutions.getFonctionName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(fonction_name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by next_time.
		if(cronExecutions.getNextTime() != null) {
			filterName = cronExecutions.getNextTime().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(next_time) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(cronExecutions.getClientId() != null) {
			filterName = cronExecutions.getClientId().toString();
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
		
		List<CronExecutions> result = jdbcTemplate.query(sql, params.toArray(), cronExecutionsMapper);
		return result;
	}

}
