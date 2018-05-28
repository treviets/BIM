package com.redsun.service.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.PaymentsDao;
import com.redsun.service.dao.mapper.PaymentsRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.Payments;

/**
 * Payments DAO implementation 
 */
@Repository
public class PaymentsDaoImpl implements PaymentsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private PaymentsRowMapper paymentsMapper;
    
    @Autowired
    private MapsRowMapper mapsRowMapper;

    // Constant for search expression.
    private Map<String, Map<String, Object>> searchExpression = null;
    
    private Map<String, Map<String, Object>> getSearchExpreesion() {
    	if(searchExpression == null) {
    		searchExpression = new HashMap<String, Map<String, Object>>();
    		// name.
    		Map<String, Object> name = new HashMap<String, Object>();
    		name.put("sql", " or UPPER(payments.name) like UPPER(?)");
    		name.put("type", String.class);
    		name.put("param", "%{0}%");
    		searchExpression.put("name", name);
    		// bill name.
    		Map<String, Object> bill_name = new HashMap<String, Object>();
    		bill_name.put("sql", " or UPPER(bills.name) like UPPER(?)");
    		bill_name.put("type", String.class);
    		bill_name.put("param", "%{0}%");
    		searchExpression.put("billName", bill_name);
    		// payment type name.
    		Map<String, Object> type_name = new HashMap<String, Object>();
    		type_name.put("sql", " or UPPER(types.name) like UPPER(?)");
    		type_name.put("type", String.class);
    		type_name.put("param", "%{0}%");
    		searchExpression.put("paymentTypeName", type_name);
    		// description.
    		Map<String, Object> description = new HashMap<String, Object>();
    		description.put("sql", " or UPPER(payments.description) like UPPER(?)");
    		description.put("type", String.class);
    		description.put("param", "%{0}%");
    		searchExpression.put("description", description);
    		
    	}
		return searchExpression;
    }

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_bill int4
			java.sql.Types.DATE, //payment_date date
			java.sql.Types.INTEGER, //id_payment_mode int4
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //id_payment_type int4
			java.sql.Types.NUMERIC, //payment_amount numeric
			java.sql.Types.NUMERIC, //payment_fee_amount numeric
			java.sql.Types.NUMERIC, //payment_credit_amount numeric
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.VARCHAR, //reference_bill varchar(100)
			java.sql.Types.INTEGER, //id_client int4
			java.sql.Types.INTEGER, //id_recipient int4
			java.sql.Types.NUMERIC, //bill_amount numeric
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into payments (name, id_bill, payment_date, id_payment_mode, idle, id_payment_type, payment_amount, payment_fee_amount, payment_credit_amount, description, id_user, creation_date, reference_bill, id_client, id_recipient, bill_amount, client_id) values (?, ?, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update payments set name = ?, id_bill = ?, payment_date = ?, id_payment_mode = ?, idle = ?::bit, id_payment_type = ?, payment_amount = ?, payment_fee_amount = ?, payment_credit_amount = ?, description = ?, id_user = ?, creation_date = ?, reference_bill = ?, id_client = ?, id_recipient = ?, bill_amount = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from payments where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from payments";

	private final static String SQL_COUNT = 
		"select count(*) from payments where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, id_bill, payment_date, id_payment_mode, idle, id_payment_type, payment_amount, payment_fee_amount, payment_credit_amount, description, id_user, creation_date, reference_bill, id_client, id_recipient, bill_amount, client_id, 0 as ext_col_count from payments where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, id_bill, payment_date, id_payment_mode, idle, id_payment_type, payment_amount, payment_fee_amount, payment_credit_amount, description, id_user, creation_date, reference_bill, id_client, id_recipient, bill_amount, client_id, 1 as ext_col_count, count(*) over() as total_count from payments where true";
	
	private final static String SQL_SELECT_EXTEND_PAGING = 
		"SELECT payments.id as id, payments.id_bill as id_bill, bills.name as bill_name, payments.id_payment_type as id_payment_type, types.name as payment_type_name, payments.name as name, payments.description as description, count(*) over() as total_count FROM payments payments INNER JOIN bills bills ON payments.id_bill = bills.id LEFT JOIN types types ON payments.id_payment_type = types.id WHERE bills.id_project = ?";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Payments payments) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(payments));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Payments payments)  {
		return new Object[] {
			payments.getName(), 			
			payments.getIdBill(), 			
			payments.getPaymentDate(), 			
			payments.getIdPaymentMode(), 			
			payments.getIdle(), 			
			payments.getIdPaymentType(), 			
			payments.getPaymentAmount(), 			
			payments.getPaymentFeeAmount(), 			
			payments.getPaymentCreditAmount(), 			
			payments.getDescription(), 			
			payments.getIdUser(), 			
			payments.getCreationDate(), 			
			payments.getReferenceBill(), 			
			payments.getIdClient(), 			
			payments.getIdRecipient(), 			
			payments.getBillAmount(), 			
			payments.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Payments payments) {
		return new Object[] {		
			payments.getName(),
			payments.getIdBill(),
			payments.getPaymentDate(),
			payments.getIdPaymentMode(),
			payments.getIdle(),
			payments.getIdPaymentType(),
			payments.getPaymentAmount(),
			payments.getPaymentFeeAmount(),
			payments.getPaymentCreditAmount(),
			payments.getDescription(),
			payments.getIdUser(),
			payments.getCreationDate(),
			payments.getReferenceBill(),
			payments.getIdClient(),
			payments.getIdRecipient(),
			payments.getBillAmount(),
			payments.getClientId(),
			payments.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Payments payments)  {
		return new Object[] {
			payments.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Payments payments) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(payments), keyHolder);

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
	public int update(final Payments payments) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(payments));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return payments.getId();
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
	public List<Payments> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Payments> result = jdbcTemplate.query(SQL_SELECT, primaryKey, paymentsMapper);
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
	public List<Payments> listPaymentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Payments payments) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(payments.getId() != null) {
			filterName = payments.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(payments.getName() != null) {
			filterName = payments.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_bill.
		if(payments.getIdBill() != null) {
			filterName = payments.getIdBill().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_bill) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_date.
		if(payments.getPaymentDate() != null) {
			filterName = payments.getPaymentDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_payment_mode.
		if(payments.getIdPaymentMode() != null) {
			filterName = payments.getIdPaymentMode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_payment_mode) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(payments.getIdle() != null) {
			filterName = payments.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_payment_type.
		if(payments.getIdPaymentType() != null) {
			filterName = payments.getIdPaymentType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_payment_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_amount.
		if(payments.getPaymentAmount() != null) {
			filterName = payments.getPaymentAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_fee_amount.
		if(payments.getPaymentFeeAmount() != null) {
			filterName = payments.getPaymentFeeAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_fee_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_credit_amount.
		if(payments.getPaymentCreditAmount() != null) {
			filterName = payments.getPaymentCreditAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_credit_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(payments.getDescription() != null) {
			filterName = payments.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(payments.getIdUser() != null) {
			filterName = payments.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(payments.getCreationDate() != null) {
			filterName = payments.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by reference_bill.
		if(payments.getReferenceBill() != null) {
			filterName = payments.getReferenceBill().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(reference_bill) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_client.
		if(payments.getIdClient() != null) {
			filterName = payments.getIdClient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_client) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_recipient.
		if(payments.getIdRecipient() != null) {
			filterName = payments.getIdRecipient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_recipient) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by bill_amount.
		if(payments.getBillAmount() != null) {
			filterName = payments.getBillAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(bill_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(payments.getClientId() != null) {
			filterName = payments.getClientId().toString();
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
		
		List<Payments> result = jdbcTemplate.query(sql, params.toArray(), paymentsMapper);
		return result;
	}
	
	// List extend for page and filter.
	public List<Map<Object, Object>> listPaymentsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Get the search expression object builded in.
		Map<String, Map<String, Object>> expressionBuildIn = getSearchExpreesion();
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_EXTEND_PAGING;
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(filter.get("idProject").toString()));
		// Build where expression.
		String filterSql = "";
		for(Map.Entry<String, Object> pro : filter.entrySet()) {
			if(pro.getValue().equals("") == false) {
				// Get slq and param from the searchExpression variable.
				Map<String, Object> expression = expressionBuildIn.get(pro.getKey());
				if(null != expression) {
					filterSql += expression.get("sql");
					if(expression.get("type") == String.class) {
						params.add("%" + pro.getValue() + "%");
					}
					else {
						params.add(pro.getValue());
					}
				}
			}
		}

		if(filterSql.equals("") == false) {
			sql += " and (false" + filterSql + ")";
		}
		
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Map<Object, Object>> result = jdbcTemplate.query(sql, params.toArray(), mapsRowMapper);
		return result;
	}

}
