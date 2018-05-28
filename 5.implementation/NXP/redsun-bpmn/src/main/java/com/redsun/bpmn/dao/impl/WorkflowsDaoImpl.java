package com.redsun.bpmn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.bpmn.dao.WorkflowsDao;
import com.redsun.bpmn.dao.mapper.WorkflowsRowMapper;
import com.redsun.bpmn.entities.Workflows;

/**
 * Workflows DAO implementation 
 */
@Repository
public class WorkflowsDaoImpl implements WorkflowsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private WorkflowsRowMapper workflowsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.VARCHAR, //workflow_update varchar(100)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into workflows (name, description, idle, workflow_update, sort_order, client_id) values (?, ?, ?::bit, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update workflows set name = ?, description = ?, idle = ?::bit, workflow_update = ?, sort_order = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from workflows where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from workflows";

	private final static String SQL_COUNT = 
		"select count(*) from workflows where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, description, idle, workflow_update, sort_order, client_id, 0 as ext_col_count from workflows where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, description, idle, workflow_update, sort_order, client_id, 1 as ext_col_count, count(*) over() as total_count from workflows where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Workflows workflows) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(workflows));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Workflows workflows)  {
		return new Object[] {
			workflows.getName(), 			
			workflows.getDescription(), 			
			workflows.getIdle(), 			
			workflows.getWorkflowUpdate(), 			
			workflows.getSortOrder(), 			
			workflows.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Workflows workflows) {
		return new Object[] {		
			workflows.getName(),
			workflows.getDescription(),
			workflows.getIdle(),
			workflows.getWorkflowUpdate(),
			workflows.getSortOrder(),
			workflows.getClientId(),
			workflows.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Workflows workflows)  {
		return new Object[] {
			workflows.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Workflows workflows) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(workflows), keyHolder);

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
	public int update(final Workflows workflows) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(workflows));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return workflows.getId();
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
	public List<Workflows> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Workflows> result = jdbcTemplate.query(SQL_SELECT, primaryKey, workflowsMapper);
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
	public List<Workflows> listWorkflowsForPageAndFilter(final int itemsPerPage, final int pageNo, final Workflows workflows) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(workflows.getId() != null) {
			filterName = workflows.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(workflows.getName() != null) {
			filterName = workflows.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(workflows.getDescription() != null) {
			filterName = workflows.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(workflows.getIdle() != null) {
			filterName = workflows.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by workflow_update.
		if(workflows.getWorkflowUpdate() != null) {
			filterName = workflows.getWorkflowUpdate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(workflow_update) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(workflows.getSortOrder() != null) {
			filterName = workflows.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(workflows.getClientId() != null) {
			filterName = workflows.getClientId().toString();
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
		
		List<Workflows> result = jdbcTemplate.query(sql, params.toArray(), workflowsMapper);
		return result;
	}

}
