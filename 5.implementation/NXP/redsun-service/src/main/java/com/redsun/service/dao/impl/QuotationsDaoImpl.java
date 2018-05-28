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

import com.redsun.service.dao.QuotationsDao;
import com.redsun.service.dao.mapper.QuotationsRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.Quotations;

/**
 * Quotations DAO implementation 
 */
@Repository
public class QuotationsDaoImpl implements QuotationsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private QuotationsRowMapper quotationsMapper;
    
    @Autowired
    private MapsRowMapper mapsRowMapper;

    // Constant for search expression.
    private Map<String, Map<String, Object>> searchExpression = null;
    
    private Map<String, Map<String, Object>> getSearchExpreesion() {
    	if(searchExpression == null) {
    		searchExpression = new HashMap<String, Map<String, Object>>();
    		// name.
    		Map<String, Object> name = new HashMap<String, Object>();
    		name.put("sql", " or UPPER(quotations.name) like UPPER(?)");
    		name.put("type", String.class);
    		name.put("param", "%{0}%");
    		searchExpression.put("name", name);
    		// project name.
    		Map<String, Object> project_name = new HashMap<String, Object>();
    		project_name.put("sql", " or UPPER(projects.name) like UPPER(?)");
    		project_name.put("type", String.class);
    		project_name.put("param", "%{0}%");
    		searchExpression.put("projectName", project_name);
    		// quotation type name.
    		Map<String, Object> type_name = new HashMap<String, Object>();
    		type_name.put("sql", " or UPPER(types.name) like UPPER(?)");
    		type_name.put("type", String.class);
    		type_name.put("param", "%{0}%");
    		searchExpression.put("quotationTypeName", type_name);
    		// description.
    		Map<String, Object> description = new HashMap<String, Object>();
    		description.put("sql", " or UPPER(quotations.description) like UPPER(?)");
    		description.put("type", String.class);
    		description.put("param", "%{0}%");
    		searchExpression.put("description", description);
    		// status name.
    		Map<String, Object> status_name = new HashMap<String, Object>();
    		status_name.put("sql", " or UPPER(statuses.name) like UPPER(?)");
    		status_name.put("type", String.class);
    		status_name.put("param", "%{0}%");
    		searchExpression.put("statusName", status_name);
    		
    	}
		return searchExpression;
    }

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_quotation_type int4
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER, //id_client int4
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.VARCHAR, //additional_info text(2147483647)
			java.sql.Types.DATE, //initial_end_date date
			java.sql.Types.NUMERIC, //untaxed_amount numeric
			java.sql.Types.NUMERIC, //initial_price_per_day_amount numeric
			java.sql.Types.NUMERIC, //initial_amount numeric
			java.sql.Types.VARCHAR, //comment text(2147483647)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //done bit
			java.sql.Types.INTEGER, //cancelled bit
			java.sql.Types.DATE, //idle_date date
			java.sql.Types.DATE, //done_date date
			java.sql.Types.INTEGER, //handled bit
			java.sql.Types.DATE, //handled_date date
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.DATE, //send_date date
			java.sql.Types.DATE, //validity_end_date date
			java.sql.Types.INTEGER, //id_activity_type int4
			java.sql.Types.VARCHAR, //result text(2147483647)
			java.sql.Types.INTEGER, //id_payment_delay int4
			java.sql.Types.NUMERIC, //tax numeric
			java.sql.Types.NUMERIC, //full_amount numeric
			java.sql.Types.INTEGER, //id_delivery_mode int4
			java.sql.Types.INTEGER, //id_likelihood int4
			java.sql.Types.NUMERIC, //planned_work numeric
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into quotations (id_project, id_quotation_type, name, description, creation_date, id_user, id_status, id_resource, id_client, id_contact, additional_info, initial_end_date, untaxed_amount, initial_price_per_day_amount, initial_amount, comment, idle, done, cancelled, idle_date, done_date, handled, handled_date, reference, send_date, validity_end_date, id_activity_type, result, id_payment_delay, tax, full_amount, id_delivery_mode, id_likelihood, planned_work, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?::bit, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update quotations set id_project = ?, id_quotation_type = ?, name = ?, description = ?, creation_date = ?, id_user = ?, id_status = ?, id_resource = ?, id_client = ?, id_contact = ?, additional_info = ?, initial_end_date = ?, untaxed_amount = ?, initial_price_per_day_amount = ?, initial_amount = ?, comment = ?, idle = ?::bit, done = ?::bit, cancelled = ?::bit, idle_date = ?, done_date = ?, handled = ?::bit, handled_date = ?, reference = ?, send_date = ?, validity_end_date = ?, id_activity_type = ?, result = ?, id_payment_delay = ?, tax = ?, full_amount = ?, id_delivery_mode = ?, id_likelihood = ?, planned_work = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from quotations where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from quotations";

	private final static String SQL_COUNT = 
		"select count(*) from quotations where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, id_quotation_type, name, description, creation_date, id_user, id_status, id_resource, id_client, id_contact, additional_info, initial_end_date, untaxed_amount, initial_price_per_day_amount, initial_amount, comment, idle, done, cancelled, idle_date, done_date, handled, handled_date, reference, send_date, validity_end_date, id_activity_type, result, id_payment_delay, tax, full_amount, id_delivery_mode, id_likelihood, planned_work, client_id, 0 as ext_col_count from quotations where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_project, id_quotation_type, name, description, creation_date, id_user, id_status, id_resource, id_client, id_contact, additional_info, initial_end_date, untaxed_amount, initial_price_per_day_amount, initial_amount, comment, idle, done, cancelled, idle_date, done_date, handled, handled_date, reference, send_date, validity_end_date, id_activity_type, result, id_payment_delay, tax, full_amount, id_delivery_mode, id_likelihood, planned_work, client_id, 1 as ext_col_count, count(*) over() as total_count from quotations where true";
	
	private final static String SQL_SELECT_EXTEND_PAGING = 
		"SELECT quotations.id as id, quotations.id_project as id_project, projects.name as project_name, quotations.id_quotation_type as id_quotation_type, types.name as quotation_type_name, quotations.id_status as id_status, statuses.name as status_name, quotations.name as name, quotations.description as description, count(*) over() as total_count FROM quotations quotations LEFT JOIN projects projects ON quotations.id_project = projects.id LEFT JOIN types types ON quotations.id_quotation_type = types.id LEFT JOIN statuses statuses ON quotations.id_status = statuses.id WHERE quotations.id_project = ?";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Quotations quotations) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(quotations));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Quotations quotations)  {
		return new Object[] {
			quotations.getIdProject(), 			
			quotations.getIdQuotationType(), 			
			quotations.getName(), 			
			quotations.getDescription(), 			
			quotations.getCreationDate(), 			
			quotations.getIdUser(), 			
			quotations.getIdStatus(), 			
			quotations.getIdResource(), 			
			quotations.getIdClient(), 			
			quotations.getIdContact(), 			
			quotations.getAdditionalInfo(), 			
			quotations.getInitialEndDate(), 			
			quotations.getUntaxedAmount(), 			
			quotations.getInitialPricePerDayAmount(), 			
			quotations.getInitialAmount(), 			
			quotations.getComment(), 			
			quotations.getIdle(), 			
			quotations.getDone(), 			
			quotations.getCancelled(), 			
			quotations.getIdleDate(), 			
			quotations.getDoneDate(), 			
			quotations.getHandled(), 			
			quotations.getHandledDate(), 			
			quotations.getReference(), 			
			quotations.getSendDate(), 			
			quotations.getValidityEndDate(), 			
			quotations.getIdActivityType(), 			
			quotations.getResult(), 			
			quotations.getIdPaymentDelay(), 			
			quotations.getTax(), 			
			quotations.getFullAmount(), 			
			quotations.getIdDeliveryMode(), 			
			quotations.getIdLikelihood(), 			
			quotations.getPlannedWork(), 			
			quotations.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Quotations quotations) {
		return new Object[] {		
			quotations.getIdProject(),
			quotations.getIdQuotationType(),
			quotations.getName(),
			quotations.getDescription(),
			quotations.getCreationDate(),
			quotations.getIdUser(),
			quotations.getIdStatus(),
			quotations.getIdResource(),
			quotations.getIdClient(),
			quotations.getIdContact(),
			quotations.getAdditionalInfo(),
			quotations.getInitialEndDate(),
			quotations.getUntaxedAmount(),
			quotations.getInitialPricePerDayAmount(),
			quotations.getInitialAmount(),
			quotations.getComment(),
			quotations.getIdle(),
			quotations.getDone(),
			quotations.getCancelled(),
			quotations.getIdleDate(),
			quotations.getDoneDate(),
			quotations.getHandled(),
			quotations.getHandledDate(),
			quotations.getReference(),
			quotations.getSendDate(),
			quotations.getValidityEndDate(),
			quotations.getIdActivityType(),
			quotations.getResult(),
			quotations.getIdPaymentDelay(),
			quotations.getTax(),
			quotations.getFullAmount(),
			quotations.getIdDeliveryMode(),
			quotations.getIdLikelihood(),
			quotations.getPlannedWork(),
			quotations.getClientId(),
			quotations.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Quotations quotations)  {
		return new Object[] {
			quotations.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Quotations quotations) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(quotations), keyHolder);

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
	public int update(final Quotations quotations) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(quotations));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return quotations.getId();
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
	public List<Quotations> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Quotations> result = jdbcTemplate.query(SQL_SELECT, primaryKey, quotationsMapper);
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
	public List<Quotations> listQuotationsForPageAndFilter(final int itemsPerPage, final int pageNo, final Quotations quotations) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(quotations.getId() != null) {
			filterName = quotations.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}
/*
		// Filter by id_project.
		if(quotations.getIdProject() != null) {
			filterName = quotations.getIdProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}
*/
		// Filter by id_quotation_type.
		if(quotations.getIdQuotationType() != null) {
			filterName = quotations.getIdQuotationType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_quotation_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(quotations.getName() != null) {
			filterName = quotations.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(quotations.getDescription() != null) {
			filterName = quotations.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(quotations.getCreationDate() != null) {
			filterName = quotations.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(quotations.getIdUser() != null) {
			filterName = quotations.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_status.
		if(quotations.getIdStatus() != null) {
			filterName = quotations.getIdStatus().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_status) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(quotations.getIdResource() != null) {
			filterName = quotations.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_client.
		if(quotations.getIdClient() != null) {
			filterName = quotations.getIdClient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_client) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_contact.
		if(quotations.getIdContact() != null) {
			filterName = quotations.getIdContact().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_contact) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by additional_info.
		if(quotations.getAdditionalInfo() != null) {
			filterName = quotations.getAdditionalInfo().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(additional_info) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_end_date.
		if(quotations.getInitialEndDate() != null) {
			filterName = quotations.getInitialEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by untaxed_amount.
		if(quotations.getUntaxedAmount() != null) {
			filterName = quotations.getUntaxedAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(untaxed_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_price_per_day_amount.
		if(quotations.getInitialPricePerDayAmount() != null) {
			filterName = quotations.getInitialPricePerDayAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_price_per_day_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_amount.
		if(quotations.getInitialAmount() != null) {
			filterName = quotations.getInitialAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by comment.
		if(quotations.getComment() != null) {
			filterName = quotations.getComment().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(comment) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(quotations.getIdle() != null) {
			filterName = quotations.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done.
		if(quotations.getDone() != null) {
			filterName = quotations.getDone().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by cancelled.
		if(quotations.getCancelled() != null) {
			filterName = quotations.getCancelled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(cancelled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle_date.
		if(quotations.getIdleDate() != null) {
			filterName = quotations.getIdleDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done_date.
		if(quotations.getDoneDate() != null) {
			filterName = quotations.getDoneDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by handled.
		if(quotations.getHandled() != null) {
			filterName = quotations.getHandled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(handled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by handled_date.
		if(quotations.getHandledDate() != null) {
			filterName = quotations.getHandledDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(handled_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by reference.
		if(quotations.getReference() != null) {
			filterName = quotations.getReference().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(reference) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by send_date.
		if(quotations.getSendDate() != null) {
			filterName = quotations.getSendDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(send_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by validity_end_date.
		if(quotations.getValidityEndDate() != null) {
			filterName = quotations.getValidityEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(validity_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity_type.
		if(quotations.getIdActivityType() != null) {
			filterName = quotations.getIdActivityType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by result.
		if(quotations.getResult() != null) {
			filterName = quotations.getResult().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(result) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_payment_delay.
		if(quotations.getIdPaymentDelay() != null) {
			filterName = quotations.getIdPaymentDelay().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_payment_delay) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by tax.
		if(quotations.getTax() != null) {
			filterName = quotations.getTax().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(tax) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by full_amount.
		if(quotations.getFullAmount() != null) {
			filterName = quotations.getFullAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(full_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_delivery_mode.
		if(quotations.getIdDeliveryMode() != null) {
			filterName = quotations.getIdDeliveryMode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_delivery_mode) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_likelihood.
		if(quotations.getIdLikelihood() != null) {
			filterName = quotations.getIdLikelihood().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_likelihood) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by planned_work.
		if(quotations.getPlannedWork() != null) {
			filterName = quotations.getPlannedWork().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(planned_work) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(quotations.getClientId() != null) {
			filterName = quotations.getClientId().toString();
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
		
		List<Quotations> result = jdbcTemplate.query(sql, params.toArray(), quotationsMapper);
		return result;
	}
	
	// List extend for page and filter.
	public List<Map<Object, Object>> listQuotationsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
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
