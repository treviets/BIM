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

import com.redsun.service.dao.DeliveryModesDao;
import com.redsun.service.dao.mapper.DeliveryModesRowMapper;
import com.redsun.service.entities.DeliveryModes;

/**
 * DeliveryModes DAO implementation 
 */
@Repository
public class DeliveryModesDaoImpl implements DeliveryModesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private DeliveryModesRowMapper deliveryModesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into delivery_modes (name, sort_order, idle, client_id) values (?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update delivery_modes set name = ?, sort_order = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from delivery_modes where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from delivery_modes";

	private final static String SQL_COUNT = 
		"select count(*) from delivery_modes where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, sort_order, idle, client_id, 0 as ext_col_count from delivery_modes where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, sort_order, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from delivery_modes where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final DeliveryModes deliveryModes) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(deliveryModes));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final DeliveryModes deliveryModes)  {
		return new Object[] {
			deliveryModes.getName(), 			
			deliveryModes.getSortOrder(), 			
			deliveryModes.getIdle(), 			
			deliveryModes.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final DeliveryModes deliveryModes) {
		return new Object[] {		
			deliveryModes.getName(),
			deliveryModes.getSortOrder(),
			deliveryModes.getIdle(),
			deliveryModes.getClientId(),
			deliveryModes.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final DeliveryModes deliveryModes)  {
		return new Object[] {
			deliveryModes.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final DeliveryModes deliveryModes) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(deliveryModes), keyHolder);

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
	public int update(final DeliveryModes deliveryModes) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(deliveryModes));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return deliveryModes.getId();
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
	public List<DeliveryModes> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<DeliveryModes> result = jdbcTemplate.query(SQL_SELECT, primaryKey, deliveryModesMapper);
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
	public List<DeliveryModes> listDeliveryModesForPageAndFilter(final int itemsPerPage, final int pageNo, final DeliveryModes deliveryModes) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(deliveryModes.getId() != null) {
			filterName = deliveryModes.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(deliveryModes.getName() != null) {
			filterName = deliveryModes.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(deliveryModes.getSortOrder() != null) {
			filterName = deliveryModes.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(deliveryModes.getIdle() != null) {
			filterName = deliveryModes.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(deliveryModes.getClientId() != null) {
			filterName = deliveryModes.getClientId().toString();
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
		
		List<DeliveryModes> result = jdbcTemplate.query(sql, params.toArray(), deliveryModesMapper);
		return result;
	}

}
