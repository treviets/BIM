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

import com.redsun.service.dao.PlanningModesDao;
import com.redsun.service.dao.mapper.PlanningModesRowMapper;
import com.redsun.service.entities.PlanningModes;

/**
 * PlanningModes DAO implementation 
 */
@Repository
public class PlanningModesDaoImpl implements PlanningModesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PlanningModesRowMapper planningModesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //code varchar(5)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //mandatory_start_date bit
			java.sql.Types.INTEGER, //mandatory_end_date bit
			java.sql.Types.VARCHAR, //apply_to varchar(20)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //mandatory_duration bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into planning_modes (name, code, sort_order, mandatory_start_date, mandatory_end_date, apply_to, idle, mandatory_duration, client_id) values (?, ?, ?, ?::bit, ?::bit, ?, ?::bit, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update planning_modes set name = ?, code = ?, sort_order = ?, mandatory_start_date = ?::bit, mandatory_end_date = ?::bit, apply_to = ?, idle = ?::bit, mandatory_duration = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from planning_modes where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from planning_modes";

	private final static String SQL_COUNT = 
		"select count(*) from planning_modes where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, code, sort_order, mandatory_start_date, mandatory_end_date, apply_to, idle, mandatory_duration, client_id, 0 as ext_col_count from planning_modes where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, code, sort_order, mandatory_start_date, mandatory_end_date, apply_to, idle, mandatory_duration, client_id, 1 as ext_col_count, count(*) over() as total_count from planning_modes where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final PlanningModes planningModes) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(planningModes));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final PlanningModes planningModes)  {
		return new Object[] {
			planningModes.getName(), 			
			planningModes.getCode(), 			
			planningModes.getSortOrder(), 			
			planningModes.getMandatoryStartDate(), 			
			planningModes.getMandatoryEndDate(), 			
			planningModes.getApplyTo(), 			
			planningModes.getIdle(), 			
			planningModes.getMandatoryDuration(), 			
			planningModes.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final PlanningModes planningModes) {
		return new Object[] {		
			planningModes.getName(),
			planningModes.getCode(),
			planningModes.getSortOrder(),
			planningModes.getMandatoryStartDate(),
			planningModes.getMandatoryEndDate(),
			planningModes.getApplyTo(),
			planningModes.getIdle(),
			planningModes.getMandatoryDuration(),
			planningModes.getClientId(),
			planningModes.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final PlanningModes planningModes)  {
		return new Object[] {
			planningModes.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final PlanningModes planningModes) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(planningModes), keyHolder);

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
	public int update(final PlanningModes planningModes) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(planningModes));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return planningModes.getId();
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
	public List<PlanningModes> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<PlanningModes> result = jdbcTemplate.query(SQL_SELECT, primaryKey, planningModesMapper);
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
	public List<PlanningModes> listPlanningModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PlanningModes planningModes) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(planningModes.getId() != null) {
			filterName = planningModes.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(planningModes.getName() != null) {
			filterName = planningModes.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by code.
		if(planningModes.getCode() != null) {
			filterName = planningModes.getCode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(code) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(planningModes.getSortOrder() != null) {
			filterName = planningModes.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by mandatory_start_date.
		if(planningModes.getMandatoryStartDate() != null) {
			filterName = planningModes.getMandatoryStartDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(mandatory_start_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by mandatory_end_date.
		if(planningModes.getMandatoryEndDate() != null) {
			filterName = planningModes.getMandatoryEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(mandatory_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by apply_to.
		if(planningModes.getApplyTo() != null) {
			filterName = planningModes.getApplyTo().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(apply_to) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(planningModes.getIdle() != null) {
			filterName = planningModes.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by mandatory_duration.
		if(planningModes.getMandatoryDuration() != null) {
			filterName = planningModes.getMandatoryDuration().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(mandatory_duration) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(planningModes.getClientId() != null) {
			filterName = planningModes.getClientId().toString();
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
		
		List<PlanningModes> result = jdbcTemplate.query(sql, params.toArray(), planningModesMapper);
		return result;
	}

}
