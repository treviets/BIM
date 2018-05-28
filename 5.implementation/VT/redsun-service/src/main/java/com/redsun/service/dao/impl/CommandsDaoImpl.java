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

import com.redsun.service.dao.CommandsDao;
import com.redsun.service.dao.mapper.CommandsRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.Commands;

/**
 * Commands DAO implementation 
 */
@Repository
public class CommandsDaoImpl implements CommandsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private CommandsRowMapper commandsMapper;
    
    @Autowired
    private MapsRowMapper mapsRowMapper;

    // Constant for search expression.
    private Map<String, Map<String, Object>> searchExpression = null;
    
    private Map<String, Map<String, Object>> getSearchExpreesion() {
    	if(searchExpression == null) {
    		searchExpression = new HashMap<String, Map<String, Object>>();
    		// name.
    		Map<String, Object> name = new HashMap<String, Object>();
    		name.put("sql", " or UPPER(commands.name) like UPPER(?)");
    		name.put("type", String.class);
    		name.put("param", "%{0}%");
    		searchExpression.put("name", name);
    		// project name.
    		Map<String, Object> project_name = new HashMap<String, Object>();
    		project_name.put("sql", " or UPPER(projects.name) like UPPER(?)");
    		project_name.put("type", String.class);
    		project_name.put("param", "%{0}%");
    		searchExpression.put("projectName", project_name);
    		// command type name.
    		Map<String, Object> type_name = new HashMap<String, Object>();
    		type_name.put("sql", " or UPPER(types.name) like UPPER(?)");
    		type_name.put("type", String.class);
    		type_name.put("param", "%{0}%");
    		searchExpression.put("commandTypeName", type_name);
    		// description.
    		Map<String, Object> description = new HashMap<String, Object>();
    		description.put("sql", " or UPPER(commands.description) like UPPER(?)");
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
			java.sql.Types.INTEGER, //id_command_type int4
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.VARCHAR, //additional_info text(2147483647)
			java.sql.Types.VARCHAR, //external_reference varchar(100)
			java.sql.Types.INTEGER, //id_activity int4
			java.sql.Types.DATE, //initial_start_date date
			java.sql.Types.DATE, //initial_end_date date
			java.sql.Types.DATE, //validated_end_date date
			java.sql.Types.NUMERIC, //initial_work numeric
			java.sql.Types.NUMERIC, //initial_price_per_day_amount numeric
			java.sql.Types.NUMERIC, //untaxed_amount numeric
			java.sql.Types.NUMERIC, //add_work numeric
			java.sql.Types.NUMERIC, //add_price_per_day_amount numeric
			java.sql.Types.NUMERIC, //add_untaxed_amount numeric
			java.sql.Types.NUMERIC, //validated_work numeric
			java.sql.Types.NUMERIC, //validated_price_per_day_amount numeric
			java.sql.Types.NUMERIC, //total_untaxed_amount numeric
			java.sql.Types.VARCHAR, //comment text(2147483647)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //done bit
			java.sql.Types.INTEGER, //cancelled bit
			java.sql.Types.DATE, //idle_date date
			java.sql.Types.DATE, //done_date date
			java.sql.Types.INTEGER, //handled bit
			java.sql.Types.DATE, //handled_date date
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.DATE, //validated_start_date date
			java.sql.Types.INTEGER, //id_activity_type int4
			java.sql.Types.INTEGER, //id_client int4
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.INTEGER, //id_payment_delay int4
			java.sql.Types.NUMERIC, //tax numeric
			java.sql.Types.NUMERIC, //full_amount numeric
			java.sql.Types.NUMERIC, //add_full_amount numeric
			java.sql.Types.NUMERIC, //total_full_amount numeric
			java.sql.Types.INTEGER, //id_delivery_mode int4
			java.sql.Types.DATE, //reception_date date
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into commands (id_project, id_command_type, name, description, creation_date, id_user, id_status, id_resource, additional_info, external_reference, id_activity, initial_start_date, initial_end_date, validated_end_date, initial_work, initial_price_per_day_amount, untaxed_amount, add_work, add_price_per_day_amount, add_untaxed_amount, validated_work, validated_price_per_day_amount, total_untaxed_amount, comment, idle, done, cancelled, idle_date, done_date, handled, handled_date, reference, validated_start_date, id_activity_type, id_client, id_contact, id_payment_delay, tax, full_amount, add_full_amount, total_full_amount, id_delivery_mode, reception_date, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?::bit, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update commands set id_project = ?, id_command_type = ?, name = ?, description = ?, creation_date = ?, id_user = ?, id_status = ?, id_resource = ?, additional_info = ?, external_reference = ?, id_activity = ?, initial_start_date = ?, initial_end_date = ?, validated_end_date = ?, initial_work = ?, initial_price_per_day_amount = ?, untaxed_amount = ?, add_work = ?, add_price_per_day_amount = ?, add_untaxed_amount = ?, validated_work = ?, validated_price_per_day_amount = ?, total_untaxed_amount = ?, comment = ?, idle = ?::bit, done = ?::bit, cancelled = ?::bit, idle_date = ?, done_date = ?, handled = ?::bit, handled_date = ?, reference = ?, validated_start_date = ?, id_activity_type = ?, id_client = ?, id_contact = ?, id_payment_delay = ?, tax = ?, full_amount = ?, add_full_amount = ?, total_full_amount = ?, id_delivery_mode = ?, reception_date = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from commands where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from commands";

	private final static String SQL_COUNT = 
		"select count(*) from commands where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, id_command_type, name, description, creation_date, id_user, id_status, id_resource, additional_info, external_reference, id_activity, initial_start_date, initial_end_date, validated_end_date, initial_work, initial_price_per_day_amount, untaxed_amount, add_work, add_price_per_day_amount, add_untaxed_amount, validated_work, validated_price_per_day_amount, total_untaxed_amount, comment, idle, done, cancelled, idle_date, done_date, handled, handled_date, reference, validated_start_date, id_activity_type, id_client, id_contact, id_payment_delay, tax, full_amount, add_full_amount, total_full_amount, id_delivery_mode, reception_date, client_id, 0 as ext_col_count from commands where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_project, id_command_type, name, description, creation_date, id_user, id_status, id_resource, additional_info, external_reference, id_activity, initial_start_date, initial_end_date, validated_end_date, initial_work, initial_price_per_day_amount, untaxed_amount, add_work, add_price_per_day_amount, add_untaxed_amount, validated_work, validated_price_per_day_amount, total_untaxed_amount, comment, idle, done, cancelled, idle_date, done_date, handled, handled_date, reference, validated_start_date, id_activity_type, id_client, id_contact, id_payment_delay, tax, full_amount, add_full_amount, total_full_amount, id_delivery_mode, reception_date, client_id, 1 as ext_col_count, count(*) over() as total_count from commands where true";
	
	private final static String SQL_SELECT_EXTEND_PAGING = 
		"SELECT commands.id as id, commands.id_project as id_project, projects.name as project_name, commands.id_command_type as id_command_type, types.name as command_type_name, commands.id_status as id_status, statuses.name as status_name, commands.name as name, commands.description as description, count(*) over() as total_count FROM commands commands LEFT JOIN projects projects ON commands.id_project = projects.id LEFT JOIN types types ON commands.id_command_type = types.id LEFT JOIN statuses statuses ON commands.id_status = statuses.id WHERE commands.id_project = ?";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Commands commands) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(commands));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Commands commands)  {
		return new Object[] {
			commands.getIdProject(), 			
			commands.getIdCommandType(), 			
			commands.getName(), 			
			commands.getDescription(), 			
			commands.getCreationDate(), 			
			commands.getIdUser(), 			
			commands.getIdStatus(), 			
			commands.getIdResource(), 			
			commands.getAdditionalInfo(), 			
			commands.getExternalReference(), 			
			commands.getIdActivity(), 			
			commands.getInitialStartDate(), 			
			commands.getInitialEndDate(), 			
			commands.getValidatedEndDate(), 			
			commands.getInitialWork(), 			
			commands.getInitialPricePerDayAmount(), 			
			commands.getUntaxedAmount(), 			
			commands.getAddWork(), 			
			commands.getAddPricePerDayAmount(), 			
			commands.getAddUntaxedAmount(), 			
			commands.getValidatedWork(), 			
			commands.getValidatedPricePerDayAmount(), 			
			commands.getTotalUntaxedAmount(), 			
			commands.getComment(), 			
			commands.getIdle(), 			
			commands.getDone(), 			
			commands.getCancelled(), 			
			commands.getIdleDate(), 			
			commands.getDoneDate(), 			
			commands.getHandled(), 			
			commands.getHandledDate(), 			
			commands.getReference(), 			
			commands.getValidatedStartDate(), 			
			commands.getIdActivityType(), 			
			commands.getIdClient(), 			
			commands.getIdContact(), 			
			commands.getIdPaymentDelay(), 			
			commands.getTax(), 			
			commands.getFullAmount(), 			
			commands.getAddFullAmount(), 			
			commands.getTotalFullAmount(), 			
			commands.getIdDeliveryMode(), 			
			commands.getReceptionDate(), 			
			commands.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Commands commands) {
		return new Object[] {		
			commands.getIdProject(),
			commands.getIdCommandType(),
			commands.getName(),
			commands.getDescription(),
			commands.getCreationDate(),
			commands.getIdUser(),
			commands.getIdStatus(),
			commands.getIdResource(),
			commands.getAdditionalInfo(),
			commands.getExternalReference(),
			commands.getIdActivity(),
			commands.getInitialStartDate(),
			commands.getInitialEndDate(),
			commands.getValidatedEndDate(),
			commands.getInitialWork(),
			commands.getInitialPricePerDayAmount(),
			commands.getUntaxedAmount(),
			commands.getAddWork(),
			commands.getAddPricePerDayAmount(),
			commands.getAddUntaxedAmount(),
			commands.getValidatedWork(),
			commands.getValidatedPricePerDayAmount(),
			commands.getTotalUntaxedAmount(),
			commands.getComment(),
			commands.getIdle(),
			commands.getDone(),
			commands.getCancelled(),
			commands.getIdleDate(),
			commands.getDoneDate(),
			commands.getHandled(),
			commands.getHandledDate(),
			commands.getReference(),
			commands.getValidatedStartDate(),
			commands.getIdActivityType(),
			commands.getIdClient(),
			commands.getIdContact(),
			commands.getIdPaymentDelay(),
			commands.getTax(),
			commands.getFullAmount(),
			commands.getAddFullAmount(),
			commands.getTotalFullAmount(),
			commands.getIdDeliveryMode(),
			commands.getReceptionDate(),
			commands.getClientId(),
			commands.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Commands commands)  {
		return new Object[] {
			commands.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Commands commands) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(commands), keyHolder);

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
	public int update(final Commands commands) {
		try {
			int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(commands));
			if (result != 0 && result != 1) {
				throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
			}
		} catch(DataAccessException ex) {
			String errorCode = ((SQLException)ex.getCause()).getSQLState();
			if(!errorCode.equals("42846")) {// can't cast boolean to bit.
				throw ex;
			}
		}

		return commands.getId();
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
	public List<Commands> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Commands> result = jdbcTemplate.query(SQL_SELECT, primaryKey, commandsMapper);
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
	public List<Commands> listCommandsForPageAndFilter(final int itemsPerPage, final int pageNo, final Commands commands) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(commands.getId() != null) {
			filterName = commands.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}
/*
		// Filter by id_project.
		if(commands.getIdProject() != null) {
			filterName = commands.getIdProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}
*/
		// Filter by id_command_type.
		if(commands.getIdCommandType() != null) {
			filterName = commands.getIdCommandType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_command_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(commands.getName() != null) {
			filterName = commands.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(commands.getDescription() != null) {
			filterName = commands.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(commands.getCreationDate() != null) {
			filterName = commands.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(commands.getIdUser() != null) {
			filterName = commands.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_status.
		if(commands.getIdStatus() != null) {
			filterName = commands.getIdStatus().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_status) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(commands.getIdResource() != null) {
			filterName = commands.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by additional_info.
		if(commands.getAdditionalInfo() != null) {
			filterName = commands.getAdditionalInfo().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(additional_info) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by external_reference.
		if(commands.getExternalReference() != null) {
			filterName = commands.getExternalReference().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(external_reference) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity.
		if(commands.getIdActivity() != null) {
			filterName = commands.getIdActivity().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_start_date.
		if(commands.getInitialStartDate() != null) {
			filterName = commands.getInitialStartDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_start_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_end_date.
		if(commands.getInitialEndDate() != null) {
			filterName = commands.getInitialEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by validated_end_date.
		if(commands.getValidatedEndDate() != null) {
			filterName = commands.getValidatedEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(validated_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_work.
		if(commands.getInitialWork() != null) {
			filterName = commands.getInitialWork().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_work) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_price_per_day_amount.
		if(commands.getInitialPricePerDayAmount() != null) {
			filterName = commands.getInitialPricePerDayAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_price_per_day_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by untaxed_amount.
		if(commands.getUntaxedAmount() != null) {
			filterName = commands.getUntaxedAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(untaxed_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by add_work.
		if(commands.getAddWork() != null) {
			filterName = commands.getAddWork().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(add_work) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by add_price_per_day_amount.
		if(commands.getAddPricePerDayAmount() != null) {
			filterName = commands.getAddPricePerDayAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(add_price_per_day_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by add_untaxed_amount.
		if(commands.getAddUntaxedAmount() != null) {
			filterName = commands.getAddUntaxedAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(add_untaxed_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by validated_work.
		if(commands.getValidatedWork() != null) {
			filterName = commands.getValidatedWork().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(validated_work) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by validated_price_per_day_amount.
		if(commands.getValidatedPricePerDayAmount() != null) {
			filterName = commands.getValidatedPricePerDayAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(validated_price_per_day_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by total_untaxed_amount.
		if(commands.getTotalUntaxedAmount() != null) {
			filterName = commands.getTotalUntaxedAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(total_untaxed_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by comment.
		if(commands.getComment() != null) {
			filterName = commands.getComment().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(comment) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(commands.getIdle() != null) {
			filterName = commands.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done.
		if(commands.getDone() != null) {
			filterName = commands.getDone().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by cancelled.
		if(commands.getCancelled() != null) {
			filterName = commands.getCancelled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(cancelled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle_date.
		if(commands.getIdleDate() != null) {
			filterName = commands.getIdleDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done_date.
		if(commands.getDoneDate() != null) {
			filterName = commands.getDoneDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by handled.
		if(commands.getHandled() != null) {
			filterName = commands.getHandled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(handled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by handled_date.
		if(commands.getHandledDate() != null) {
			filterName = commands.getHandledDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(handled_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by reference.
		if(commands.getReference() != null) {
			filterName = commands.getReference().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(reference) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by validated_start_date.
		if(commands.getValidatedStartDate() != null) {
			filterName = commands.getValidatedStartDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(validated_start_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity_type.
		if(commands.getIdActivityType() != null) {
			filterName = commands.getIdActivityType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_client.
		if(commands.getIdClient() != null) {
			filterName = commands.getIdClient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_client) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_contact.
		if(commands.getIdContact() != null) {
			filterName = commands.getIdContact().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_contact) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_payment_delay.
		if(commands.getIdPaymentDelay() != null) {
			filterName = commands.getIdPaymentDelay().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_payment_delay) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by tax.
		if(commands.getTax() != null) {
			filterName = commands.getTax().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(tax) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by full_amount.
		if(commands.getFullAmount() != null) {
			filterName = commands.getFullAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(full_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by add_full_amount.
		if(commands.getAddFullAmount() != null) {
			filterName = commands.getAddFullAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(add_full_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by total_full_amount.
		if(commands.getTotalFullAmount() != null) {
			filterName = commands.getTotalFullAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(total_full_amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_delivery_mode.
		if(commands.getIdDeliveryMode() != null) {
			filterName = commands.getIdDeliveryMode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_delivery_mode) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by reception_date.
		if(commands.getReceptionDate() != null) {
			filterName = commands.getReceptionDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(reception_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(commands.getClientId() != null) {
			filterName = commands.getClientId().toString();
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
		
		List<Commands> result = jdbcTemplate.query(sql, params.toArray(), commandsMapper);
		return result;
	}
	
	// List extend for page and filter.
	public List<Map<Object, Object>> listCommandsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
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
