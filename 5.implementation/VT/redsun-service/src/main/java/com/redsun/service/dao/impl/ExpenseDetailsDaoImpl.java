package com.redsun.service.dao.impl;

import com.redsun.service.dao.ExpenseDetailsDao;
import com.redsun.service.dao.mapper.ExpenseDetailsRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.ExpenseDetails;
import com.redsun.service.utils.RedSunQueryCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ExpenseDetails DAO implementation 
 */
@Repository
public class ExpenseDetailsDaoImpl implements ExpenseDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MapsRowMapper mapper;
    @Autowired
    private ExpenseDetailsRowMapper expenseDetailsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_expense int4
			java.sql.Types.DATE, //expense_date date
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_expense_detail_type int4
			java.sql.Types.NUMERIC, //value01 numeric
			java.sql.Types.NUMERIC, //value02 numeric
			java.sql.Types.NUMERIC, //value03 numeric
			java.sql.Types.VARCHAR, //unit01 varchar(20)
			java.sql.Types.VARCHAR, //unit02 varchar(20)
			java.sql.Types.VARCHAR, //unit03 varchar(20)
			java.sql.Types.VARCHAR, //description varchar(4000)
			java.sql.Types.NUMERIC, //amount numeric
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.VARCHAR, //external_reference varchar(100)
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into expense_details (id_project, id_expense, expense_date, name, id_expense_detail_type, value01, value02, value03, unit01, unit02, unit03, description, amount, idle, external_reference, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?)";

	private final static String SQL_UPDATE = 
		"update expense_details set id_project = ?, id_expense = ?, expense_date = ?, name = ?, id_expense_detail_type = ?, value01 = ?, value02 = ?, value03 = ?, unit01 = ?, unit02 = ?, unit03 = ?, description = ?, amount = ?, external_reference = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from expense_details where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from expense_details";

	private final static String SQL_COUNT = 
		"select count(*) from expense_details where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, id_expense, expense_date, name, id_expense_detail_type, value01, value02, value03, unit01, unit02, unit03, description, amount, idle, external_reference, client_id, 0 as ext_col_count from expense_details where id = ?";
	

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final ExpenseDetails expenseDetails) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(expenseDetails));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final ExpenseDetails expenseDetails)  {
		return new Object[] {
			expenseDetails.getIdProject(), 			
			expenseDetails.getIdExpense(), 			
			expenseDetails.getExpenseDate(), 			
			expenseDetails.getName(), 			
			expenseDetails.getIdExpenseDetailType(), 			
			expenseDetails.getValue01(), 			
			expenseDetails.getValue02(), 			
			expenseDetails.getValue03(), 			
			expenseDetails.getUnit01(), 			
			expenseDetails.getUnit02(), 			
			expenseDetails.getUnit03(), 			
			expenseDetails.getDescription(), 			
			expenseDetails.getAmount(), 			
			expenseDetails.getIdle(), 			
			expenseDetails.getExternalReference(), 			
			expenseDetails.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final ExpenseDetails expenseDetails) {
		return new Object[] {		
			expenseDetails.getIdProject(),
			expenseDetails.getIdExpense(),
			expenseDetails.getExpenseDate(),
			expenseDetails.getName(),
			expenseDetails.getIdExpenseDetailType(),
			expenseDetails.getValue01(),
			expenseDetails.getValue02(),
			expenseDetails.getValue03(),
			expenseDetails.getUnit01(),
			expenseDetails.getUnit02(),
			expenseDetails.getUnit03(),
			expenseDetails.getDescription(),
			expenseDetails.getAmount(),
			expenseDetails.getExternalReference(),
			expenseDetails.getClientId(),
			expenseDetails.getId()			
		};
	}
	
	protected Object[] getValuesForPrimaryKey(final ExpenseDetails expenseDetails)  {
		return new Object[] {
			expenseDetails.getId()
		};
	}
	
	@Override
	public Integer insert(final ExpenseDetails expenseDetails) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(expenseDetails), keyHolder);

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
	public int update(final ExpenseDetails expenseDetails) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(expenseDetails));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return expenseDetails.getId();
	}	

	@Override
	public int delete(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	@Override
	public List<ExpenseDetails> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<ExpenseDetails> result = jdbcTemplate.query(SQL_SELECT, primaryKey, expenseDetailsMapper);
		return result;
	}

	@Override
	public boolean exists(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		long count = jdbcTemplate.queryForObject(SQL_COUNT, primaryKey, Long.class);
		return count > 0 ;
	}

	@Override
	public long count() {
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, Long.class);
	}

	@Override
	public List<Map<Object, Object>> listExpenseDetailsByExpensesId(final Integer clientId, final Integer expensesId, final Integer itemsPerPage, final Integer pageNo) {
		final Integer offset = (pageNo - 1) * itemsPerPage;
		return jdbcTemplate.query(RedSunQueryCollections.ExpensesDetail_join_ExpenseTypes_getByExpensesId, new Object[] { clientId, expensesId, itemsPerPage, offset }, mapper);
	}

}
