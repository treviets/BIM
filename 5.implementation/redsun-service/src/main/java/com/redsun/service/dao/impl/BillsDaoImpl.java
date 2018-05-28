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

import com.redsun.service.dao.BillsDao;
import com.redsun.service.dao.mapper.BillsRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.Bills;

/**
 * Bills DAO implementation 
 */
@Repository
public class BillsDaoImpl implements BillsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private BillsRowMapper billsMapper;
    
    @Autowired
    private MapsRowMapper mapsRowMapper;

    // Constant for search expression.
    private Map<String, Map<String, Object>> searchExpression = null;
    
    private Map<String, Map<String, Object>> getSearchExpreesion() {
    	if(searchExpression == null) {
    		searchExpression = new HashMap<String, Map<String, Object>>();
    		// name.
    		Map<String, Object> name = new HashMap<String, Object>();
    		name.put("sql", " or UPPER(bills.name) like UPPER(?)");
    		name.put("type", String.class);
    		name.put("param", "%{0}%");
    		searchExpression.put("name", name);
    		// project name.
    		Map<String, Object> project_name = new HashMap<String, Object>();
    		project_name.put("sql", " or UPPER(projects.name) like UPPER(?)");
    		project_name.put("type", String.class);
    		project_name.put("param", "%{0}%");
    		searchExpression.put("projectName", project_name);
    		// bill type name.
    		Map<String, Object> type_name = new HashMap<String, Object>();
    		type_name.put("sql", " or UPPER(types.name) like UPPER(?)");
    		type_name.put("type", String.class);
    		type_name.put("param", "%{0}%");
    		searchExpression.put("billTypeName", type_name);
    		// description.
    		Map<String, Object> description = new HashMap<String, Object>();
    		description.put("sql", " or UPPER(bills.description) like UPPER(?)");
    		description.put("type", String.class);
    		description.put("param", "%{0}%");
    		searchExpression.put("description", description);
    		
    	}
		return searchExpression;
    }

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_bill_type int4
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_client int4
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.INTEGER, //id_recipient int4
			java.sql.Types.VARCHAR, //billing_type varchar(10)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //date date
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.INTEGER, //done bit
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //bill_id int4
			java.sql.Types.NUMERIC, //tax numeric
			java.sql.Types.NUMERIC, //untaxed_amount numeric
			java.sql.Types.NUMERIC, //full_amount numeric
			java.sql.Types.INTEGER, //cancelled bit
			java.sql.Types.INTEGER, //id_activity_type int4
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.INTEGER, //payment_done bit
			java.sql.Types.DATE, //payment_date date
			java.sql.Types.NUMERIC, //payment_amount numeric
			java.sql.Types.INTEGER, //id_payment_delay int4
			java.sql.Types.DATE, //payment_due_date date
			java.sql.Types.INTEGER, //id_delivery_mode int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER, //payments_count int4
			java.sql.Types.INTEGER, //command_amount_pct int4
			java.sql.Types.DATE, //send_date date
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into bills (id_bill_type, name, id_project, id_client, id_contact, id_recipient, billing_type, description, date, id_status, done, idle, bill_id, tax, untaxed_amount, full_amount, cancelled, id_activity_type, reference, payment_done, payment_date, payment_amount, id_payment_delay, payment_due_date, id_delivery_mode, id_resource, id_user, creation_date, payments_count, command_amount_pct, send_date, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?, ?, ?, ?, ?::bit, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update bills set id_bill_type = ?, name = ?, id_project = ?, id_client = ?, id_contact = ?, id_recipient = ?, billing_type = ?, description = ?, date = ?, id_status = ?, done = ?::bit, idle = ?::bit, bill_id = ?, tax = ?, untaxed_amount = ?, full_amount = ?, cancelled = ?::bit, id_activity_type = ?, reference = ?, payment_done = ?::bit, payment_date = ?, payment_amount = ?, id_payment_delay = ?, payment_due_date = ?, id_delivery_mode = ?, id_resource = ?, id_user = ?, creation_date = ?, payments_count = ?, command_amount_pct = ?, send_date = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from bills where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from bills";

	private final static String SQL_COUNT = 
		"select count(*) from bills where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_bill_type, name, id_project, id_client, id_contact, id_recipient, billing_type, description, date, id_status, done, idle, bill_id, tax, untaxed_amount, full_amount, cancelled, id_activity_type, reference, payment_done, payment_date, payment_amount, id_payment_delay, payment_due_date, id_delivery_mode, id_resource, id_user, creation_date, payments_count, command_amount_pct, send_date, client_id, 0 as ext_col_count from bills where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_bill_type, name, id_project, id_client, id_contact, id_recipient, billing_type, description, date, id_status, done, idle, bill_id, tax, untaxed_amount, full_amount, cancelled, id_activity_type, reference, payment_done, payment_date, payment_amount, id_payment_delay, payment_due_date, id_delivery_mode, id_resource, id_user, creation_date, payments_count, command_amount_pct, send_date, client_id, 1 as ext_col_count, count(*) over() as total_count from bills where true";
	
	private final static String SQL_SELECT_EXTEND_PAGING = 
		"SELECT bills.id as id, bills.id_project as id_project, projects.name as project_name, bills.id_bill_type as id_bill_type, types.name as bill_type_name, bills.name as name, bills.description as description, count(*) over() as total_count FROM bills bills LEFT JOIN projects projects ON bills.id_project = projects.id LEFT JOIN types types ON bills.id_bill_type = types.id WHERE bills.id_project = ?";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Bills bills) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(bills));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Bills bills)  {
		return new Object[] {
			bills.getIdBillType(), 			
			bills.getName(), 			
			bills.getIdProject(), 			
			bills.getIdClient(), 			
			bills.getIdContact(), 			
			bills.getIdRecipient(), 			
			bills.getBillingType(), 			
			bills.getDescription(), 			
			bills.getDate(), 			
			bills.getIdStatus(), 			
			bills.getDone(), 			
			bills.getIdle(), 			
			bills.getBillId(), 			
			bills.getTax(), 			
			bills.getUntaxedAmount(), 			
			bills.getFullAmount(), 			
			bills.getCancelled(), 			
			bills.getIdActivityType(), 			
			bills.getReference(), 			
			bills.getPaymentDone(), 			
			bills.getPaymentDate(), 			
			bills.getPaymentAmount(), 			
			bills.getIdPaymentDelay(), 			
			bills.getPaymentDueDate(), 			
			bills.getIdDeliveryMode(), 			
			bills.getIdResource(), 			
			bills.getIdUser(), 			
			bills.getCreationDate(), 			
			bills.getPaymentsCount(), 			
			bills.getCommandAmountPct(), 			
			bills.getSendDate(), 			
			bills.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Bills bills) {
		return new Object[] {		
			bills.getIdBillType(),
			bills.getName(),
			bills.getIdProject(),
			bills.getIdClient(),
			bills.getIdContact(),
			bills.getIdRecipient(),
			bills.getBillingType(),
			bills.getDescription(),
			bills.getDate(),
			bills.getIdStatus(),
			bills.getDone(),
			bills.getIdle(),
			bills.getBillId(),
			bills.getTax(),
			bills.getUntaxedAmount(),
			bills.getFullAmount(),
			bills.getCancelled(),
			bills.getIdActivityType(),
			bills.getReference(),
			bills.getPaymentDone(),
			bills.getPaymentDate(),
			bills.getPaymentAmount(),
			bills.getIdPaymentDelay(),
			bills.getPaymentDueDate(),
			bills.getIdDeliveryMode(),
			bills.getIdResource(),
			bills.getIdUser(),
			bills.getCreationDate(),
			bills.getPaymentsCount(),
			bills.getCommandAmountPct(),
			bills.getSendDate(),
			bills.getClientId(),
			bills.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Bills bills)  {
		return new Object[] {
			bills.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Bills bills) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(bills), keyHolder);

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
	public int update(final Bills bills) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(bills));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return bills.getId();
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
	public List<Bills> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Bills> result = jdbcTemplate.query(SQL_SELECT, primaryKey, billsMapper);
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
	public List<Bills> listBillsForPageAndFilter(final int itemsPerPage, final int pageNo, final Bills bills) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(bills.getId() != null) {
			filterName = bills.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_bill_type.
		if(bills.getIdBillType() != null) {
			filterName = bills.getIdBillType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_bill_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(bills.getName() != null) {
			filterName = bills.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}
/*
		// Filter by id_project.
		if(bills.getIdProject() != null) {
			filterName = bills.getIdProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}
*/
		// Filter by id_client.
		if(bills.getIdClient() != null) {
			filterName = bills.getIdClient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_client) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_contact.
		if(bills.getIdContact() != null) {
			filterName = bills.getIdContact().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_contact) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_recipient.
		if(bills.getIdRecipient() != null) {
			filterName = bills.getIdRecipient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_recipient) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by billing_type.
		if(bills.getBillingType() != null) {
			filterName = bills.getBillingType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(billing_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(bills.getDescription() != null) {
			filterName = bills.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by date.
		if(bills.getDate() != null) {
			filterName = bills.getDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_status.
		if(bills.getIdStatus() != null) {
			filterName = bills.getIdStatus().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_status) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done.
		if(bills.getDone() != null) {
			filterName = bills.getDone().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(bills.getIdle() != null) {
			filterName = bills.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by bill_id.
		if(bills.getBillId() != null) {
			filterName = bills.getBillId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(bill_id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by tax.
		if(bills.getTax() != null) {
			filterName = bills.getTax().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(tax) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by untaxed_amount.
		if(bills.getUntaxedAmount() != null) {
			filterName = bills.getUntaxedAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(untaxed_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by full_amount.
		if(bills.getFullAmount() != null) {
			filterName = bills.getFullAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(full_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by cancelled.
		if(bills.getCancelled() != null) {
			filterName = bills.getCancelled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(cancelled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity_type.
		if(bills.getIdActivityType() != null) {
			filterName = bills.getIdActivityType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by reference.
		if(bills.getReference() != null) {
			filterName = bills.getReference().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(reference) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_done.
		if(bills.getPaymentDone() != null) {
			filterName = bills.getPaymentDone().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_done) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_date.
		if(bills.getPaymentDate() != null) {
			filterName = bills.getPaymentDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_amount.
		if(bills.getPaymentAmount() != null) {
			filterName = bills.getPaymentAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_payment_delay.
		if(bills.getIdPaymentDelay() != null) {
			filterName = bills.getIdPaymentDelay().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_payment_delay) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payment_due_date.
		if(bills.getPaymentDueDate() != null) {
			filterName = bills.getPaymentDueDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payment_due_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_delivery_mode.
		if(bills.getIdDeliveryMode() != null) {
			filterName = bills.getIdDeliveryMode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_delivery_mode) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(bills.getIdResource() != null) {
			filterName = bills.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(bills.getIdUser() != null) {
			filterName = bills.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(bills.getCreationDate() != null) {
			filterName = bills.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by payments_count.
		if(bills.getPaymentsCount() != null) {
			filterName = bills.getPaymentsCount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(payments_count) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by command_amount_pct.
		if(bills.getCommandAmountPct() != null) {
			filterName = bills.getCommandAmountPct().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(command_amount_pct) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by send_date.
		if(bills.getSendDate() != null) {
			filterName = bills.getSendDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(send_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(bills.getClientId() != null) {
			filterName = bills.getClientId().toString();
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
		
		List<Bills> result = jdbcTemplate.query(sql, params.toArray(), billsMapper);
		return result;
	}
	
	// List extend for page and filter.
	public List<Map<Object, Object>> listBillsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
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
