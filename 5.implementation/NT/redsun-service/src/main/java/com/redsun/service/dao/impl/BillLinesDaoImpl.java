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

import com.redsun.service.dao.BillLinesDao;
import com.redsun.service.dao.mapper.BillLinesRowMapper;
import com.redsun.service.entities.BillLines;

/**
 * BillLines DAO implementation 
 */
@Repository
public class BillLinesDaoImpl implements BillLinesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private BillLinesRowMapper billLinesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //line int4
			java.sql.Types.NUMERIC, //quantity numeric
			java.sql.Types.VARCHAR, //description varchar(200)
			java.sql.Types.VARCHAR, //detail varchar(4000)
			java.sql.Types.NUMERIC, //price numeric
			java.sql.Types.NUMERIC, //amount numeric
			java.sql.Types.VARCHAR, //ref_type varchar(100)
			java.sql.Types.INTEGER, //ref_id int4
			java.sql.Types.INTEGER, //id_term int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER, //id_activity_price int4
			java.sql.Types.DATE, //start_date date
			java.sql.Types.DATE, //end_date date
			java.sql.Types.INTEGER, //id_measure_unit int4
			java.sql.Types.INTEGER, //extra bit
			java.sql.Types.VARCHAR, //billing_type varchar(10)
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into bill_lines (line, quantity, description, detail, price, amount, ref_type, ref_id, id_term, id_resource, id_activity_price, start_date, end_date, id_measure_unit, extra, billing_type, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?)";

	private final static String SQL_UPDATE = 
		"update bill_lines set line = ?, quantity = ?, description = ?, detail = ?, price = ?, amount = ?, ref_type = ?, ref_id = ?, id_term = ?, id_resource = ?, id_activity_price = ?, start_date = ?, end_date = ?, id_measure_unit = ?, extra = ?::bit, billing_type = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from bill_lines where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from bill_lines";

	private final static String SQL_COUNT = 
		"select count(*) from bill_lines where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, line, quantity, description, detail, price, amount, ref_type, ref_id, id_term, id_resource, id_activity_price, start_date, end_date, id_measure_unit, extra, billing_type, client_id, 0 as ext_col_count from bill_lines where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, line, quantity, description, detail, price, amount, ref_type, ref_id, id_term, id_resource, id_activity_price, start_date, end_date, id_measure_unit, extra, billing_type, client_id, 1 as ext_col_count, count(*) over() as total_count from bill_lines where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final BillLines billLines) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(billLines));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final BillLines billLines)  {
		return new Object[] {
			billLines.getLine(), 			
			billLines.getQuantity(), 			
			billLines.getDescription(), 			
			billLines.getDetail(), 			
			billLines.getPrice(), 			
			billLines.getAmount(), 			
			billLines.getRefType(), 			
			billLines.getRefId(), 			
			billLines.getIdTerm(), 			
			billLines.getIdResource(), 			
			billLines.getIdActivityPrice(), 			
			billLines.getStartDate(), 			
			billLines.getEndDate(), 			
			billLines.getIdMeasureUnit(), 			
			billLines.getExtra(), 			
			billLines.getBillingType(), 			
			billLines.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final BillLines billLines) {
		return new Object[] {		
			billLines.getLine(),
			billLines.getQuantity(),
			billLines.getDescription(),
			billLines.getDetail(),
			billLines.getPrice(),
			billLines.getAmount(),
			billLines.getRefType(),
			billLines.getRefId(),
			billLines.getIdTerm(),
			billLines.getIdResource(),
			billLines.getIdActivityPrice(),
			billLines.getStartDate(),
			billLines.getEndDate(),
			billLines.getIdMeasureUnit(),
			billLines.getExtra(),
			billLines.getBillingType(),
			billLines.getClientId(),
			billLines.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final BillLines billLines)  {
		return new Object[] {
			billLines.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final BillLines billLines) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(billLines), keyHolder);

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
	public int update(final BillLines billLines) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(billLines));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return billLines.getId();
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
	public List<BillLines> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<BillLines> result = jdbcTemplate.query(SQL_SELECT, primaryKey, billLinesMapper);
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
	public List<BillLines> listBillLinesForPageAndFilter(final int itemsPerPage, final int pageNo, final BillLines billLines) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(billLines.getId() != null) {
			filterName = billLines.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by line.
		if(billLines.getLine() != null) {
			filterName = billLines.getLine().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(line) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by quantity.
		if(billLines.getQuantity() != null) {
			filterName = billLines.getQuantity().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(quantity) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(billLines.getDescription() != null) {
			filterName = billLines.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by detail.
		if(billLines.getDetail() != null) {
			filterName = billLines.getDetail().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(detail) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by price.
		if(billLines.getPrice() != null) {
			filterName = billLines.getPrice().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(price) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by amount.
		if(billLines.getAmount() != null) {
			filterName = billLines.getAmount().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(amount) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by ref_type.
		if(billLines.getRefType() != null) {
			filterName = billLines.getRefType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(ref_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by ref_id.
		if(billLines.getRefId() != null) {
			filterName = billLines.getRefId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(ref_id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_term.
		if(billLines.getIdTerm() != null) {
			filterName = billLines.getIdTerm().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_term) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(billLines.getIdResource() != null) {
			filterName = billLines.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity_price.
		if(billLines.getIdActivityPrice() != null) {
			filterName = billLines.getIdActivityPrice().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity_price) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by start_date.
		if(billLines.getStartDate() != null) {
			filterName = billLines.getStartDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(start_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by end_date.
		if(billLines.getEndDate() != null) {
			filterName = billLines.getEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_measure_unit.
		if(billLines.getIdMeasureUnit() != null) {
			filterName = billLines.getIdMeasureUnit().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_measure_unit) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by extra.
		if(billLines.getExtra() != null) {
			filterName = billLines.getExtra().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(extra) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by billing_type.
		if(billLines.getBillingType() != null) {
			filterName = billLines.getBillingType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(billing_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(billLines.getClientId() != null) {
			filterName = billLines.getClientId().toString();
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
		
		List<BillLines> result = jdbcTemplate.query(sql, params.toArray(), billLinesMapper);
		return result;
	}

}
