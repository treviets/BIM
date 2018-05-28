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

import com.redsun.service.dao.ProvidersDao;
import com.redsun.service.dao.mapper.ProvidersRowMapper;
import com.redsun.service.entities.Providers;

/**
 * Providers DAO implementation 
 */
@Repository
public class ProvidersDaoImpl implements ProvidersDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ProvidersRowMapper providersMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_provider_type int4
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.VARCHAR, //provider_code varchar(25)
			java.sql.Types.INTEGER, //id_payment_delay int4
			java.sql.Types.VARCHAR, //num_tax varchar(100)
			java.sql.Types.NUMERIC, //tax numeric
			java.sql.Types.VARCHAR, //designation varchar(100)
			java.sql.Types.VARCHAR, //street varchar(100)
			java.sql.Types.VARCHAR, //complement varchar(100)
			java.sql.Types.VARCHAR, //zip varchar(100)
			java.sql.Types.VARCHAR, //city varchar(100)
			java.sql.Types.VARCHAR, //state varchar(100)
			java.sql.Types.VARCHAR, //country varchar(100)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into providers (name, id_provider_type, description, provider_code, id_payment_delay, num_tax, tax, designation, street, complement, zip, city, state, country, idle, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update providers set name = ?, id_provider_type = ?, description = ?, provider_code = ?, id_payment_delay = ?, num_tax = ?, tax = ?, designation = ?, street = ?, complement = ?, zip = ?, city = ?, state = ?, country = ?, idle = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from providers where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from providers";

	private final static String SQL_COUNT = 
		"select count(*) from providers where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, id_provider_type, description, provider_code, id_payment_delay, num_tax, tax, designation, street, complement, zip, city, state, country, idle, client_id, 0 as ext_col_count from providers where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, id_provider_type, description, provider_code, id_payment_delay, num_tax, tax, designation, street, complement, zip, city, state, country, idle, client_id, 1 as ext_col_count, count(*) over() as total_count from providers where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Providers providers) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(providers));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Providers providers)  {
		return new Object[] {
			providers.getName(), 			
			providers.getIdProviderType(), 			
			providers.getDescription(), 			
			providers.getProviderCode(), 			
			providers.getIdPaymentDelay(), 			
			providers.getNumTax(), 			
			providers.getTax(), 			
			providers.getDesignation(), 			
			providers.getStreet(), 			
			providers.getComplement(), 			
			providers.getZip(), 			
			providers.getCity(), 			
			providers.getState(), 			
			providers.getCountry(), 			
			providers.getIdle(), 			
			providers.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Providers providers) {
		return new Object[] {		
			providers.getName(),
			providers.getIdProviderType(),
			providers.getDescription(),
			providers.getProviderCode(),
			providers.getIdPaymentDelay(),
			providers.getNumTax(),
			providers.getTax(),
			providers.getDesignation(),
			providers.getStreet(),
			providers.getComplement(),
			providers.getZip(),
			providers.getCity(),
			providers.getState(),
			providers.getCountry(),
			providers.getIdle(),
			providers.getClientId(),
			providers.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Providers providers)  {
		return new Object[] {
			providers.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Providers providers) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(providers), keyHolder);

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
	public int update(final Providers providers) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(providers));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return providers.getId();
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
	public List<Providers> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Providers> result = jdbcTemplate.query(SQL_SELECT, primaryKey, providersMapper);
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
	public List<Providers> listProvidersForPageAndFilter(final int itemsPerPage, final int pageNo, final Providers providers) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(providers.getId() != null) {
			filterName = providers.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(providers.getName() != null) {
			filterName = providers.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_provider_type.
		if(providers.getIdProviderType() != null) {
			filterName = providers.getIdProviderType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_provider_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(providers.getDescription() != null) {
			filterName = providers.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by provider_code.
		if(providers.getProviderCode() != null) {
			filterName = providers.getProviderCode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(provider_code) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_payment_delay.
		if(providers.getIdPaymentDelay() != null) {
			filterName = providers.getIdPaymentDelay().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_payment_delay) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by num_tax.
		if(providers.getNumTax() != null) {
			filterName = providers.getNumTax().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(num_tax) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by tax.
		if(providers.getTax() != null) {
			filterName = providers.getTax().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(tax) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by designation.
		if(providers.getDesignation() != null) {
			filterName = providers.getDesignation().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(designation) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by street.
		if(providers.getStreet() != null) {
			filterName = providers.getStreet().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(street) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by complement.
		if(providers.getComplement() != null) {
			filterName = providers.getComplement().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(complement) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by zip.
		if(providers.getZip() != null) {
			filterName = providers.getZip().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(zip) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by city.
		if(providers.getCity() != null) {
			filterName = providers.getCity().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(city) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by state.
		if(providers.getState() != null) {
			filterName = providers.getState().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(state) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by country.
		if(providers.getCountry() != null) {
			filterName = providers.getCountry().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(country) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(providers.getIdle() != null) {
			filterName = providers.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(providers.getClientId() != null) {
			filterName = providers.getClientId().toString();
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
		
		List<Providers> result = jdbcTemplate.query(sql, params.toArray(), providersMapper);
		return result;
	}

}
