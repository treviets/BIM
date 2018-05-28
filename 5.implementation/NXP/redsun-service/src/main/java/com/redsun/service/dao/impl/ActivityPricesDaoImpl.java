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

import com.redsun.service.dao.ActivityPricesDao;
import com.redsun.service.dao.mapper.ActivityPricesRowMapper;
import com.redsun.service.entities.ActivityPrices;

/**
 * ActivityPrices DAO implementation 
 */
@Repository
public class ActivityPricesDaoImpl implements ActivityPricesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ActivityPricesRowMapper activityPricesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_activity_type int4
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.NUMERIC, //price_cost numeric
			java.sql.Types.INTEGER, //subcontractor int4
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.NUMERIC, //subcontractor_cost numeric
			java.sql.Types.INTEGER, //id_team int4
			java.sql.Types.NUMERIC, //commission_cost numeric
			java.sql.Types.INTEGER, //is_ref int4
			java.sql.Types.INTEGER, //pct int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into activity_prices (id_project, id_activity_type, name, price_cost, subcontractor, sort_order, idle, subcontractor_cost, id_team, commission_cost, is_ref, pct, id_user, creation_date, client_id) values (?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update activity_prices set id_project = ?, id_activity_type = ?, name = ?, price_cost = ?, subcontractor = ?, sort_order = ?, idle = ?::bit, subcontractor_cost = ?, id_team = ?, commission_cost = ?, is_ref = ?, pct = ?, id_user = ?, creation_date = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from activity_prices where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from activity_prices";

	private final static String SQL_COUNT = 
		"select count(*) from activity_prices where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, id_activity_type, name, price_cost, subcontractor, sort_order, idle, subcontractor_cost, id_team, commission_cost, is_ref, pct, id_user, creation_date, client_id, 0 as ext_col_count from activity_prices where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_project, id_activity_type, name, price_cost, subcontractor, sort_order, idle, subcontractor_cost, id_team, commission_cost, is_ref, pct, id_user, creation_date, client_id, 1 as ext_col_count, count(*) over() as total_count from activity_prices where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final ActivityPrices activityPrices) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(activityPrices));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final ActivityPrices activityPrices)  {
		return new Object[] {
			activityPrices.getIdProject(), 			
			activityPrices.getIdActivityType(), 			
			activityPrices.getName(), 			
			activityPrices.getPriceCost(), 			
			activityPrices.getSubcontractor(), 			
			activityPrices.getSortOrder(), 			
			activityPrices.getIdle(), 			
			activityPrices.getSubcontractorCost(), 			
			activityPrices.getIdTeam(), 			
			activityPrices.getCommissionCost(), 			
			activityPrices.getIsRef(), 			
			activityPrices.getPct(), 			
			activityPrices.getIdUser(), 			
			activityPrices.getCreationDate(), 			
			activityPrices.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final ActivityPrices activityPrices) {
		return new Object[] {		
			activityPrices.getIdProject(),
			activityPrices.getIdActivityType(),
			activityPrices.getName(),
			activityPrices.getPriceCost(),
			activityPrices.getSubcontractor(),
			activityPrices.getSortOrder(),
			activityPrices.getIdle(),
			activityPrices.getSubcontractorCost(),
			activityPrices.getIdTeam(),
			activityPrices.getCommissionCost(),
			activityPrices.getIsRef(),
			activityPrices.getPct(),
			activityPrices.getIdUser(),
			activityPrices.getCreationDate(),
			activityPrices.getClientId(),
			activityPrices.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final ActivityPrices activityPrices)  {
		return new Object[] {
			activityPrices.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final ActivityPrices activityPrices) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(activityPrices), keyHolder);

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
	public int update(final ActivityPrices activityPrices) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(activityPrices));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return activityPrices.getId();
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
	public List<ActivityPrices> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<ActivityPrices> result = jdbcTemplate.query(SQL_SELECT, primaryKey, activityPricesMapper);
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
	public List<ActivityPrices> listActivityPricesForPageAndFilter(final int itemsPerPage, final int pageNo, final ActivityPrices activityPrices) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(activityPrices.getId() != null) {
			filterName = activityPrices.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_project.
		if(activityPrices.getIdProject() != null) {
			filterName = activityPrices.getIdProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity_type.
		if(activityPrices.getIdActivityType() != null) {
			filterName = activityPrices.getIdActivityType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(activityPrices.getName() != null) {
			filterName = activityPrices.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by price_cost.
		if(activityPrices.getPriceCost() != null) {
			filterName = activityPrices.getPriceCost().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(price_cost) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by subcontractor.
		if(activityPrices.getSubcontractor() != null) {
			filterName = activityPrices.getSubcontractor().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(subcontractor) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(activityPrices.getSortOrder() != null) {
			filterName = activityPrices.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(activityPrices.getIdle() != null) {
			filterName = activityPrices.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by subcontractor_cost.
		if(activityPrices.getSubcontractorCost() != null) {
			filterName = activityPrices.getSubcontractorCost().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(subcontractor_cost) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_team.
		if(activityPrices.getIdTeam() != null) {
			filterName = activityPrices.getIdTeam().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_team) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by commission_cost.
		if(activityPrices.getCommissionCost() != null) {
			filterName = activityPrices.getCommissionCost().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(commission_cost) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by is_ref.
		if(activityPrices.getIsRef() != null) {
			filterName = activityPrices.getIsRef().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(is_ref) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by pct.
		if(activityPrices.getPct() != null) {
			filterName = activityPrices.getPct().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(pct) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(activityPrices.getIdUser() != null) {
			filterName = activityPrices.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(activityPrices.getCreationDate() != null) {
			filterName = activityPrices.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(activityPrices.getClientId() != null) {
			filterName = activityPrices.getClientId().toString();
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
		
		List<ActivityPrices> result = jdbcTemplate.query(sql, params.toArray(), activityPricesMapper);
		return result;
	}

}
