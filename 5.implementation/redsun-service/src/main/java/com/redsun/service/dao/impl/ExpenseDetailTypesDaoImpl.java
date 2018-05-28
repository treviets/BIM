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

import com.redsun.service.dao.ExpenseDetailTypesDao;
import com.redsun.service.dao.mapper.ExpenseDetailTypesRowMapper;
import com.redsun.service.entities.ExpenseDetailTypes;

/**
 * ExpenseDetailTypes DAO implementation 
 */
@Repository
public class ExpenseDetailTypesDaoImpl implements ExpenseDetailTypesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ExpenseDetailTypesRowMapper expenseDetailTypesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.NUMERIC, //value01 numeric
			java.sql.Types.NUMERIC, //value02 numeric
			java.sql.Types.NUMERIC, //value03 numeric
			java.sql.Types.VARCHAR, //unit01 varchar(20)
			java.sql.Types.VARCHAR, //unit02 varchar(20)
			java.sql.Types.VARCHAR, //unit03 varchar(20)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //individual bit
			java.sql.Types.INTEGER, //project bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into expense_detail_types (name, sort_order, value01, value02, value03, unit01, unit02, unit03, idle, description, individual, project, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?::bit, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update expense_detail_types set name = ?, sort_order = ?, value01 = ?, value02 = ?, value03 = ?, unit01 = ?, unit02 = ?, unit03 = ?, idle = ?::bit, description = ?, individual = ?::bit, project = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from expense_detail_types where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from expense_detail_types";

	private final static String SQL_COUNT = 
		"select count(*) from expense_detail_types where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, sort_order, value01, value02, value03, unit01, unit02, unit03, idle, description, individual, project, client_id, 0 as ext_col_count from expense_detail_types where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, sort_order, value01, value02, value03, unit01, unit02, unit03, idle, description, individual, project, client_id, 1 as ext_col_count, count(*) over() as total_count from expense_detail_types where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final ExpenseDetailTypes expenseDetailTypes) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(expenseDetailTypes));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final ExpenseDetailTypes expenseDetailTypes)  {
		return new Object[] {
			expenseDetailTypes.getName(), 			
			expenseDetailTypes.getSortOrder(), 			
			expenseDetailTypes.getValue01(), 			
			expenseDetailTypes.getValue02(), 			
			expenseDetailTypes.getValue03(), 			
			expenseDetailTypes.getUnit01(), 			
			expenseDetailTypes.getUnit02(), 			
			expenseDetailTypes.getUnit03(), 			
			expenseDetailTypes.getIdle(), 			
			expenseDetailTypes.getDescription(), 			
			expenseDetailTypes.getIndividual(), 			
			expenseDetailTypes.getProject(), 			
			expenseDetailTypes.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final ExpenseDetailTypes expenseDetailTypes) {
		return new Object[] {		
			expenseDetailTypes.getName(),
			expenseDetailTypes.getSortOrder(),
			expenseDetailTypes.getValue01(),
			expenseDetailTypes.getValue02(),
			expenseDetailTypes.getValue03(),
			expenseDetailTypes.getUnit01(),
			expenseDetailTypes.getUnit02(),
			expenseDetailTypes.getUnit03(),
			expenseDetailTypes.getIdle(),
			expenseDetailTypes.getDescription(),
			expenseDetailTypes.getIndividual(),
			expenseDetailTypes.getProject(),
			expenseDetailTypes.getClientId(),
			expenseDetailTypes.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final ExpenseDetailTypes expenseDetailTypes)  {
		return new Object[] {
			expenseDetailTypes.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final ExpenseDetailTypes expenseDetailTypes) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(expenseDetailTypes), keyHolder);

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
	public int update(final ExpenseDetailTypes expenseDetailTypes) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(expenseDetailTypes));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return expenseDetailTypes.getId();
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
	public List<ExpenseDetailTypes> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<ExpenseDetailTypes> result = jdbcTemplate.query(SQL_SELECT, primaryKey, expenseDetailTypesMapper);
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
	public List<ExpenseDetailTypes> listExpenseDetailTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final ExpenseDetailTypes expenseDetailTypes) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(expenseDetailTypes.getId() != null) {
			filterName = expenseDetailTypes.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(expenseDetailTypes.getName() != null) {
			filterName = expenseDetailTypes.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(expenseDetailTypes.getSortOrder() != null) {
			filterName = expenseDetailTypes.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by value01.
		if(expenseDetailTypes.getValue01() != null) {
			filterName = expenseDetailTypes.getValue01().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(value01) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by value02.
		if(expenseDetailTypes.getValue02() != null) {
			filterName = expenseDetailTypes.getValue02().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(value02) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by value03.
		if(expenseDetailTypes.getValue03() != null) {
			filterName = expenseDetailTypes.getValue03().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(value03) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by unit01.
		if(expenseDetailTypes.getUnit01() != null) {
			filterName = expenseDetailTypes.getUnit01().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(unit01) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by unit02.
		if(expenseDetailTypes.getUnit02() != null) {
			filterName = expenseDetailTypes.getUnit02().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(unit02) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by unit03.
		if(expenseDetailTypes.getUnit03() != null) {
			filterName = expenseDetailTypes.getUnit03().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(unit03) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(expenseDetailTypes.getIdle() != null) {
			filterName = expenseDetailTypes.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(expenseDetailTypes.getDescription() != null) {
			filterName = expenseDetailTypes.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by individual.
		if(expenseDetailTypes.getIndividual() != null) {
			filterName = expenseDetailTypes.getIndividual().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(individual) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by project.
		if(expenseDetailTypes.getProject() != null) {
			filterName = expenseDetailTypes.getProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(expenseDetailTypes.getClientId() != null) {
			filterName = expenseDetailTypes.getClientId().toString();
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
		
		List<ExpenseDetailTypes> result = jdbcTemplate.query(sql, params.toArray(), expenseDetailTypesMapper);
		return result;
	}

}
