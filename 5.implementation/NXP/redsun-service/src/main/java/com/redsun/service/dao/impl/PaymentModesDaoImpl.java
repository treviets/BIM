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

import com.redsun.service.dao.PaymentModesDao;
import com.redsun.service.dao.mapper.PaymentModesRowMapper;
import com.redsun.service.entities.PaymentModes;

/**
 * PaymentModes DAO implementation 
 */
@Repository
public class PaymentModesDaoImpl implements PaymentModesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PaymentModesRowMapper paymentModesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into payment_modes (name, sort_order, idle, client_id) values (?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update payment_modes set name = ?, sort_order = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from payment_modes where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from payment_modes";

	private final static String SQL_COUNT = 
		"select count(*) from payment_modes where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, sort_order, idle, client_id, 0 as ext_col_count from payment_modes where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, sort_order, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from payment_modes where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final PaymentModes paymentModes) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(paymentModes));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final PaymentModes paymentModes)  {
		return new Object[] {
			paymentModes.getName(), 			
			paymentModes.getSortOrder(), 			
			paymentModes.getIdle(), 			
			paymentModes.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final PaymentModes paymentModes) {
		return new Object[] {		
			paymentModes.getName(),
			paymentModes.getSortOrder(),
			paymentModes.getIdle(),
			paymentModes.getClientId(),
			paymentModes.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final PaymentModes paymentModes)  {
		return new Object[] {
			paymentModes.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final PaymentModes paymentModes) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(paymentModes), keyHolder);

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
	public int update(final PaymentModes paymentModes) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(paymentModes));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return paymentModes.getId();
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
	public List<PaymentModes> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<PaymentModes> result = jdbcTemplate.query(SQL_SELECT, primaryKey, paymentModesMapper);
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
	public List<PaymentModes> listPaymentModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PaymentModes paymentModes) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(paymentModes.getId() != null) {
			filterName = paymentModes.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(paymentModes.getName() != null) {
			filterName = paymentModes.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(paymentModes.getSortOrder() != null) {
			filterName = paymentModes.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(paymentModes.getIdle() != null) {
			filterName = paymentModes.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(paymentModes.getClientId() != null) {
			filterName = paymentModes.getClientId().toString();
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
		
		List<PaymentModes> result = jdbcTemplate.query(sql, params.toArray(), paymentModesMapper);
		return result;
	}

}
