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

import com.redsun.service.dao.ClientsDao;
import com.redsun.service.dao.mapper.ClientsRowMapper;
import com.redsun.service.entities.Clients;
import com.redsun.service.utils.RedSunConstants;

/**
 * Clients DAO implementation 
 */
@Repository
public class ClientsDaoImpl implements ClientsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ClientsRowMapper clientsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.VARCHAR, //client_code varchar(25)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //payment_delay int4
			java.sql.Types.NUMERIC, //tax numeric
			java.sql.Types.VARCHAR, //designation varchar(100)
			java.sql.Types.VARCHAR, //street varchar(100)
			java.sql.Types.VARCHAR, //complement varchar(100)
			java.sql.Types.VARCHAR, //zip varchar(50)
			java.sql.Types.VARCHAR, //city varchar(100)
			java.sql.Types.VARCHAR, //state varchar(100)
			java.sql.Types.VARCHAR, //country varchar(100)
			java.sql.Types.INTEGER, //id_client_type int4
			java.sql.Types.INTEGER, //payment_delay_end_of_month bit
			java.sql.Types.VARCHAR, //num_tax varchar(100)
			java.sql.Types.INTEGER, //id_payment_delay int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"INSERT INTO clients (name, description, client_code, idle, payment_delay, tax, designation, street, complement, zip, city, state, country, id_client_type, payment_delay_end_of_month, num_tax, id_payment_delay, client_id) VALUES (?, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"UPDATE clients SET name = ?, description = ?, idle = ?::bit, payment_delay = ?, tax = ?, designation = ?, street = ?, complement = ?, zip = ?, city = ?, state = ?, country = ?, id_client_type = ?, payment_delay_end_of_month = ?::bit, num_tax = ?, id_payment_delay = ?, client_id = ? WHERE id = ?";

	private final static String SQL_DELETE = 
		"DELETE FROM clients WHERE id = ?";

	private final static String SQL_COUNT_ALL = 
		"SELECT count(*) FROM clients";

	private final static String SQL_COUNT = 
		"SELECT count(*) FROM clients WHERE id = ?";
	
	private final static String SQL_SELECT = 
		"SELECT id, name, description, client_code, idle, payment_delay, tax, designation, street, complement, zip, city, state, country, id_client_type, payment_delay_end_of_month, num_tax, id_payment_delay, client_id, 0 AS ext_col_count FROM clients WHERE client_id = ? AND id = ?";
	
	private final static String SQL_SELECT_BY_CLIENT_CODE = 
			"SELECT id, name, description, client_code, idle, payment_delay, tax, designation, street, complement, zip, city, state, country, id_client_type, payment_delay_end_of_month, num_tax, id_payment_delay, client_id, 0 AS ext_col_count FROM clients WHERE client_code = ?";
		
	
	private final static String SQL_SELECT_PAGING = 
		"SELECT id, name, description, client_code, idle, payment_delay, tax, designation, street, complement, zip, city, state, country, id_client_type, payment_delay_end_of_month, num_tax, id_payment_delay, client_id, 1 as ext_col_count, count(*) over() AS total_count FROM clients where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Clients clients) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(clients));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Clients clients)  {
		return new Object[] {
			clients.getName(), 			
			clients.getDescription(), 			
			clients.getClientCode(), 			
			clients.getIdle(), 			
			clients.getPaymentDelay(), 			
			clients.getTax(), 			
			clients.getDesignation(), 			
			clients.getStreet(), 			
			clients.getComplement(), 			
			clients.getZip(), 			
			clients.getCity(), 			
			clients.getState(), 			
			clients.getCountry(), 			
			clients.getIdClientType(), 			
			clients.getPaymentDelayEndOfMonth(), 			
			clients.getNumTax(), 			
			clients.getIdPaymentDelay(), 			
			clients.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Clients clients) {
		return new Object[] {		
			clients.getName(),
			clients.getDescription(),
			clients.getIdle(),
			clients.getPaymentDelay(),
			clients.getTax(),
			clients.getDesignation(),
			clients.getStreet(),
			clients.getComplement(),
			clients.getZip(),
			clients.getCity(),
			clients.getState(),
			clients.getCountry(),
			clients.getIdClientType(),
			clients.getPaymentDelayEndOfMonth(),
			clients.getNumTax(),
			clients.getIdPaymentDelay(),
			clients.getClientId(),
			clients.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Clients clients)  {
		return new Object[] {
			clients.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public int insert(final Clients clients) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		//check if duplicate client code
		List<Clients> existingClient= this.getByCode(clients.getClientCode());
		if(existingClient != null  && existingClient.size() > 0){
			return RedSunConstants.DUPLICATE_CODE_STATUS;
		}
		int result = jdbcTemplate.update(getPreparedStatementCreator(clients), keyHolder);

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
	public int update(final Clients clients) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(clients));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return clients.getId();
	}	

    // Delete.
	@Override
	public int delete(final int id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	// Get By Id.
	@Override
	public List<Clients> getById(int clientId, int id) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(id);
		List<Clients> result = jdbcTemplate.query(SQL_SELECT, params.toArray(), clientsMapper);
		return result;
	}
	
	private List<Clients> getByCode(String code) {
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		List<Clients> result = jdbcTemplate.query(SQL_SELECT_BY_CLIENT_CODE, params.toArray(), clientsMapper);
		return result;
	}

    // Exists.
	@Override
	public boolean exists(final int id) {
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
	public List<Clients> listClientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Clients clients) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		// Paging.
		sql += " ORDER BY name ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Clients> result = jdbcTemplate.query(sql, params.toArray(), clientsMapper);
		return result;
	}

	@Override
	public List<Clients> listClients() {
		List<Clients> result = jdbcTemplate.query(SQL_SELECT_PAGING, clientsMapper);
		return result;
	}

}
