package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.PaymentDelaysDao;
import com.redsun.service.dao.mapper.PaymentDelaysRowMapper;
import com.redsun.service.entities.PaymentDelays;

/**
 * PaymentDelays DAO implementation 
 */
@Repository
public class PaymentDelaysDaoImpl implements PaymentDelaysDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PaymentDelaysRowMapper paymentDelaysMapper;
	private static Logger log = Logger.getLogger(RiskDaoImpl.class);

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //days int4
			java.sql.Types.INTEGER, //end_of_month bit
			java.sql.Types.INTEGER, //sort_order int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into payment_delays (name, days, end_of_month, sort_order, idle, client_id) values (?, ?, ?::bit, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update payment_delays set name = ?, days = ?, end_of_month = ?::bit, sort_order = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from payment_delays where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from payment_delays";

	private final static String SQL_COUNT = 
		"select count(*) from payment_delays where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, days, end_of_month, sort_order, idle, client_id, 0 as ext_col_count from payment_delays where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, days, end_of_month, sort_order, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from payment_delays where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final PaymentDelays paymentDelays) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(paymentDelays));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final PaymentDelays paymentDelays)  {
		return new Object[] {
			paymentDelays.getName(), 			
			paymentDelays.getDays(), 			
			paymentDelays.getEndOfMonth(), 			
			paymentDelays.getSortOrder(), 			
			paymentDelays.getIdle(), 			
			paymentDelays.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final PaymentDelays paymentDelays) {
		return new Object[] {		
			paymentDelays.getName(),
			paymentDelays.getDays(),
			paymentDelays.getEndOfMonth(),
			paymentDelays.getSortOrder(),
			paymentDelays.getIdle(),
			paymentDelays.getClientId(),
			paymentDelays.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final PaymentDelays paymentDelays)  {
		return new Object[] {
			paymentDelays.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final PaymentDelays paymentDelays) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(paymentDelays), keyHolder);

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
	public int update(final PaymentDelays paymentDelays) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(paymentDelays));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return paymentDelays.getId();
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
	public List<PaymentDelays> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<PaymentDelays> result = jdbcTemplate.query(SQL_SELECT, primaryKey, paymentDelaysMapper);
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
	public List<PaymentDelays> listPaymentDelaysForPageAndFilter(final int itemsPerPage, final int pageNo, final PaymentDelays paymentDelays) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(paymentDelays.getId() != null) {
			filterName = paymentDelays.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(paymentDelays.getName() != null) {
			filterName = paymentDelays.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by days.
		if(paymentDelays.getDays() != null) {
			filterName = paymentDelays.getDays().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(days) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by end_of_month.
		if(paymentDelays.getEndOfMonth() != null) {
			filterName = paymentDelays.getEndOfMonth().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(end_of_month) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sort_order.
		if(paymentDelays.getSortOrder() != null) {
			filterName = paymentDelays.getSortOrder().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sort_order) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(paymentDelays.getIdle() != null) {
			filterName = paymentDelays.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(paymentDelays.getClientId() != null) {
			filterName = paymentDelays.getClientId().toString();
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
		
		List<PaymentDelays> result = jdbcTemplate.query(sql, params.toArray(), paymentDelaysMapper);
		return result;
	}

	@Override
	public List<PaymentDelays> listAll(int clientId) {
		List<PaymentDelays> listPaymentDelays = null;
		String SELECT_PAYMENTDEPLAYS = "select id, name, days, end_of_month, sort_order, idle, client_id, 0 as ext_col_count from payment_delays where client_id = ?";

		List<Object> params = new ArrayList<Object>();
		params.add(clientId);

		try {
			listPaymentDelays = jdbcTemplate.query(SELECT_PAYMENTDEPLAYS, params.toArray(), paymentDelaysMapper);
			return listPaymentDelays;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listPaymentDelays;
	}

}
