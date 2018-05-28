package com.redsun.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ExpensesDao;
import com.redsun.service.dao.mapper.ExpensesRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.Expenses;
import com.redsun.service.utils.RedSunQueryCollections;

/**
 * Expenses DAO implementation 
 */
@Repository
public class ExpensesDaoImpl implements ExpensesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MapsRowMapper mapper;
    @Autowired
    private ExpensesRowMapper expensesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.INTEGER, //id_expense_type int4
			java.sql.Types.VARCHAR, //scope varchar(100)
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //expense_planned_date date
			java.sql.Types.DATE, //expense_real_date date
			java.sql.Types.NUMERIC, //planned_amount numeric
			java.sql.Types.NUMERIC, //real_amount numeric
			java.sql.Types.VARCHAR, //day varchar(8)
			java.sql.Types.VARCHAR, //week varchar(6)
			java.sql.Types.VARCHAR, //month varchar(6)
			java.sql.Types.VARCHAR, //year varchar(4)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.VARCHAR, //external_reference varchar(100)
			java.sql.Types.INTEGER, //cancelled bit
			java.sql.Types.INTEGER, //id_document int4
			java.sql.Types.INTEGER, //id_provider int4
			java.sql.Types.DATE, //send_date date
			java.sql.Types.INTEGER, //id_delivery_mode int4
			java.sql.Types.VARCHAR, //delivery_delay varchar(100)
			java.sql.Types.DATE, //delivery_date date
			java.sql.Types.VARCHAR, //payment_condition varchar(100)
			java.sql.Types.DATE, //reception_date date
			java.sql.Types.VARCHAR, //result text(2147483647)
			java.sql.Types.NUMERIC, //tax_pct numeric
			java.sql.Types.NUMERIC, //planned_full_amount numeric
			java.sql.Types.NUMERIC, //real_full_amount numeric
			java.sql.Types.DATE, //idle_date date
			java.sql.Types.INTEGER, //handled bit
			java.sql.Types.DATE, //handled_date date
			java.sql.Types.INTEGER, //done bit
			java.sql.Types.DATE, //done_date date
			java.sql.Types.INTEGER, //id_responsible int4
			java.sql.Types.INTEGER, //payment_done bit
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into expenses (id_project, id_resource, id_user, id_expense_type, scope, name, id_status, description, expense_planned_date, expense_real_date, planned_amount, real_amount, day, week, month, year, idle, reference, external_reference, cancelled, id_document, id_provider, send_date, id_delivery_mode, delivery_delay, delivery_date, payment_condition, reception_date, result, tax_pct, planned_full_amount, real_full_amount, idle_date, handled, handled_date, done, done_date, id_responsible, payment_done, id_contact, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?::bit, ?, ?, ?::bit, ?, ?)";

	private final static String SQL_UPDATE = 
		"update expenses set id_project = ?, id_resource = ?, id_user = ?, id_expense_type = ?, scope = ?, name = ?, id_status = ?, description = ?, expense_planned_date = ?, expense_real_date = ?, planned_amount = ?, real_amount = ?, day = ?, week = ?, month = ?, year = ?, idle = ?::bit, reference = ?, external_reference = ?, cancelled = ?::bit, id_document = ?, id_provider = ?, send_date = ?, id_delivery_mode = ?, delivery_delay = ?, delivery_date = ?, payment_condition = ?, reception_date = ?, result = ?, tax_pct = ?, planned_full_amount = ?, real_full_amount = ?, idle_date = ?, handled = ?::bit, handled_date = ?, done = ?::bit, done_date = ?, id_responsible = ?, payment_done = ?::bit, id_contact = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from expenses where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from expenses";

	private final static String SQL_COUNT = 
		"select count(*) from expenses where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, id_resource, id_user, id_expense_type, scope, name, id_status, description, expense_planned_date, expense_real_date, planned_amount, real_amount, day, week, month, year, idle, reference, external_reference, cancelled, id_document, id_provider, send_date, id_delivery_mode, delivery_delay, delivery_date, payment_condition, reception_date, result, tax_pct, planned_full_amount, real_full_amount, idle_date, handled, handled_date, done, done_date, id_responsible, payment_done, id_contact, client_id, 0 as ext_col_count from expenses where id = ?";
	

	private PreparedStatementCreator getPreparedStatementCreator(final Expenses expenses) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(expenses));
		return psc ;
	}
	
	protected Object[] getValuesForInsert(final Expenses expenses)  {
		return new Object[] {
			expenses.getIdProject(), 			
			expenses.getIdResource(), 			
			expenses.getIdUser(), 			
			expenses.getIdExpenseType(), 			
			expenses.getScope(), 			
			expenses.getName(), 			
			expenses.getIdStatus(), 			
			expenses.getDescription(), 			
			expenses.getExpensePlannedDate(), 			
			expenses.getExpenseRealDate(), 			
			expenses.getPlannedAmount(), 			
			expenses.getRealAmount(), 			
			expenses.getDay(), 			
			expenses.getWeek(), 			
			expenses.getMonth(), 			
			expenses.getYear(), 			
			expenses.getIdle(), 			
			expenses.getReference(), 			
			expenses.getExternalReference(), 			
			expenses.getCancelled(), 			
			expenses.getIdDocument(), 			
			expenses.getIdProvider(), 			
			expenses.getSendDate(), 			
			expenses.getIdDeliveryMode(), 			
			expenses.getDeliveryDelay(), 			
			expenses.getDeliveryDate(), 			
			expenses.getPaymentCondition(), 			
			expenses.getReceptionDate(), 			
			expenses.getResult(), 			
			expenses.getTaxPct(), 			
			expenses.getPlannedFullAmount(), 			
			expenses.getRealFullAmount(), 			
			expenses.getIdleDate(), 			
			expenses.getHandled(), 			
			expenses.getHandledDate(), 			
			expenses.getDone(), 			
			expenses.getDoneDate(), 			
			expenses.getIdResponsible(), 			
			expenses.getPaymentDone(), 			
			expenses.getIdContact(), 			
			expenses.getClientId()			
		};
	}
	
	protected Object[] getValuesForUpdate(final Expenses expenses) {
		return new Object[] {		
			expenses.getIdProject(),
			expenses.getIdResource(),
			expenses.getIdUser(),
			expenses.getIdExpenseType(),
			expenses.getScope(),
			expenses.getName(),
			expenses.getIdStatus(),
			expenses.getDescription(),
			expenses.getExpensePlannedDate(),
			expenses.getExpenseRealDate(),
			expenses.getPlannedAmount(),
			expenses.getRealAmount(),
			expenses.getDay(),
			expenses.getWeek(),
			expenses.getMonth(),
			expenses.getYear(),
			expenses.getIdle(),
			expenses.getReference(),
			expenses.getExternalReference(),
			expenses.getCancelled(),
			expenses.getIdDocument(),
			expenses.getIdProvider(),
			expenses.getSendDate(),
			expenses.getIdDeliveryMode(),
			expenses.getDeliveryDelay(),
			expenses.getDeliveryDate(),
			expenses.getPaymentCondition(),
			expenses.getReceptionDate(),
			expenses.getResult(),
			expenses.getTaxPct(),
			expenses.getPlannedFullAmount(),
			expenses.getRealFullAmount(),
			expenses.getIdleDate(),
			expenses.getHandled(),
			expenses.getHandledDate(),
			expenses.getDone(),
			expenses.getDoneDate(),
			expenses.getIdResponsible(),
			expenses.getPaymentDone(),
			expenses.getIdContact(),
			expenses.getClientId(),
			expenses.getId()			
		};
	}
	
	protected Object[] getValuesForPrimaryKey(final Expenses expenses)  {
		return new Object[] {
			expenses.getId()
		};
	}
	
	@Override
	public Integer insert(final Expenses expenses) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(expenses), keyHolder);

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

	@Override
	public Integer update(final Expenses expenses) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(expenses));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return expenses.getId();
	}	

	@Override
	public Integer delete(final Integer id) {
		int result = jdbcTemplate.update(SQL_DELETE, new Object[] { id });
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	@Override
	public List<Expenses> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Expenses> result = jdbcTemplate.query(SQL_SELECT, primaryKey, expensesMapper);
		return result;
	}

	@Override
	public boolean exists(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		long count = jdbcTemplate.queryForObject(SQL_COUNT, primaryKey, Long.class);
		return count > 0 ;
	}

	@Override
	public Long count() {
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, Long.class);
	}

	@Override
	public List<Map<Object, Object>> getExpensesByType(final Integer clientId, final String scope, final Integer pageNo, final Integer itemsPerPage) {
		final Integer offset = (pageNo - 1) * itemsPerPage;
		if(scope.equalsIgnoreCase("ProjectExpense")){
			return jdbcTemplate.query(RedSunQueryCollections.Expenses_join_Projects_join_ExpenseTypes_join_Statues_getAllByExpenseTypes, new Object[] { clientId, scope, itemsPerPage, offset }, mapper);
		}
		return jdbcTemplate.query(RedSunQueryCollections.Expenses_join_Projects_join_ExpenseTypes_join_Resources_join_Statues_getAllByExpenseTypes, new Object[] { clientId, scope, itemsPerPage, offset }, mapper);
	}

}
