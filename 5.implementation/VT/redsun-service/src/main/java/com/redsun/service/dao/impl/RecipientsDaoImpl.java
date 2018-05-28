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

import com.redsun.service.dao.RecipientsDao;
import com.redsun.service.dao.mapper.RecipientsRowMapper;
import com.redsun.service.entities.Recipients;

/**
 * Recipients DAO implementation 
 */
@Repository
public class RecipientsDaoImpl implements RecipientsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private RecipientsRowMapper recipientsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(50)
			java.sql.Types.VARCHAR, //company_number varchar(100)
			java.sql.Types.VARCHAR, //num_tax varchar(100)
			java.sql.Types.VARCHAR, //bank_name varchar(100)
			java.sql.Types.VARCHAR, //iban_country varchar(2)
			java.sql.Types.VARCHAR, //iban_key varchar(2)
			java.sql.Types.VARCHAR, //iban_bban varchar(34)
			java.sql.Types.VARCHAR, //designation varchar(50)
			java.sql.Types.VARCHAR, //street varchar(50)
			java.sql.Types.VARCHAR, //complement varchar(50)
			java.sql.Types.VARCHAR, //zip varchar(50)
			java.sql.Types.VARCHAR, //city varchar(50)
			java.sql.Types.VARCHAR, //state varchar(50)
			java.sql.Types.VARCHAR, //country varchar(50)
			java.sql.Types.INTEGER, //tax_free bit
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.VARCHAR, //legal_notice varchar(1000)
			java.sql.Types.VARCHAR, //contact_name varchar(100)
			java.sql.Types.VARCHAR, //contact_email varchar(100)
			java.sql.Types.VARCHAR, //contact_phone varchar(100)
			java.sql.Types.VARCHAR, //contact_mobile varchar(100)
			java.sql.Types.VARCHAR, //bank_national_account_number varchar(100)
			java.sql.Types.VARCHAR, //bank_international_account_number varchar(100)
			java.sql.Types.VARCHAR, //bank_identification_code varchar(100)
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into recipients (name, company_number, num_tax, bank_name, iban_country, iban_key, iban_bban, designation, street, complement, zip, city, state, country, tax_free, idle, legal_notice, contact_name, contact_email, contact_phone, contact_mobile, bank_national_account_number, bank_international_account_number, bank_identification_code, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update recipients set name = ?, company_number = ?, num_tax = ?, bank_name = ?, iban_country = ?, iban_key = ?, iban_bban = ?, designation = ?, street = ?, complement = ?, zip = ?, city = ?, state = ?, country = ?, tax_free = ?::bit, idle = ?::bit, legal_notice = ?, contact_name = ?, contact_email = ?, contact_phone = ?, contact_mobile = ?, bank_national_account_number = ?, bank_international_account_number = ?, bank_identification_code = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from recipients where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from recipients";

	private final static String SQL_COUNT = 
		"select count(*) from recipients where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, company_number, num_tax, bank_name, iban_country, iban_key, iban_bban, designation, street, complement, zip, city, state, country, tax_free, idle, legal_notice, contact_name, contact_email, contact_phone, contact_mobile, bank_national_account_number, bank_international_account_number, bank_identification_code, client_id, 0 as ext_col_count from recipients where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, company_number, num_tax, bank_name, iban_country, iban_key, iban_bban, designation, street, complement, zip, city, state, country, tax_free, idle, legal_notice, contact_name, contact_email, contact_phone, contact_mobile, bank_national_account_number, bank_international_account_number, bank_identification_code, client_id, 1 as ext_col_count, count(*) over() as total_count from recipients where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Recipients recipients) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(recipients));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Recipients recipients)  {
		return new Object[] {
			recipients.getName(), 			
			recipients.getCompanyNumber(), 			
			recipients.getNumTax(), 			
			recipients.getBankName(), 			
			recipients.getIbanCountry(), 			
			recipients.getIbanKey(), 			
			recipients.getIbanBban(), 			
			recipients.getDesignation(), 			
			recipients.getStreet(), 			
			recipients.getComplement(), 			
			recipients.getZip(), 			
			recipients.getCity(), 			
			recipients.getState(), 			
			recipients.getCountry(), 			
			recipients.getTaxFree(), 			
			recipients.getIdle(), 			
			recipients.getLegalNotice(), 			
			recipients.getContactName(), 			
			recipients.getContactEmail(), 			
			recipients.getContactPhone(), 			
			recipients.getContactMobile(), 			
			recipients.getBankNationalAccountNumber(), 			
			recipients.getBankInternationalAccountNumber(), 			
			recipients.getBankIdentificationCode(), 			
			recipients.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Recipients recipients) {
		return new Object[] {		
			recipients.getName(),
			recipients.getCompanyNumber(),
			recipients.getNumTax(),
			recipients.getBankName(),
			recipients.getIbanCountry(),
			recipients.getIbanKey(),
			recipients.getIbanBban(),
			recipients.getDesignation(),
			recipients.getStreet(),
			recipients.getComplement(),
			recipients.getZip(),
			recipients.getCity(),
			recipients.getState(),
			recipients.getCountry(),
			recipients.getTaxFree(),
			recipients.getIdle(),
			recipients.getLegalNotice(),
			recipients.getContactName(),
			recipients.getContactEmail(),
			recipients.getContactPhone(),
			recipients.getContactMobile(),
			recipients.getBankNationalAccountNumber(),
			recipients.getBankInternationalAccountNumber(),
			recipients.getBankIdentificationCode(),
			recipients.getClientId(),
			recipients.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Recipients recipients)  {
		return new Object[] {
			recipients.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Recipients recipients) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(recipients), keyHolder);

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
	public int update(final Recipients recipients) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(recipients));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return recipients.getId();
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
	public List<Recipients> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Recipients> result = jdbcTemplate.query(SQL_SELECT, primaryKey, recipientsMapper);
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
	public List<Recipients> listRecipientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Recipients recipients) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(recipients.getId() != null) {
			filterName = recipients.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(recipients.getName() != null) {
			filterName = recipients.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by company_number.
		if(recipients.getCompanyNumber() != null) {
			filterName = recipients.getCompanyNumber().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(company_number) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by num_tax.
		if(recipients.getNumTax() != null) {
			filterName = recipients.getNumTax().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(num_tax) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by bank_name.
		if(recipients.getBankName() != null) {
			filterName = recipients.getBankName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(bank_name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by iban_country.
		if(recipients.getIbanCountry() != null) {
			filterName = recipients.getIbanCountry().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(iban_country) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by iban_key.
		if(recipients.getIbanKey() != null) {
			filterName = recipients.getIbanKey().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(iban_key) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by iban_bban.
		if(recipients.getIbanBban() != null) {
			filterName = recipients.getIbanBban().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(iban_bban) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by designation.
		if(recipients.getDesignation() != null) {
			filterName = recipients.getDesignation().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(designation) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by street.
		if(recipients.getStreet() != null) {
			filterName = recipients.getStreet().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(street) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by complement.
		if(recipients.getComplement() != null) {
			filterName = recipients.getComplement().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(complement) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by zip.
		if(recipients.getZip() != null) {
			filterName = recipients.getZip().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(zip) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by city.
		if(recipients.getCity() != null) {
			filterName = recipients.getCity().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(city) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by state.
		if(recipients.getState() != null) {
			filterName = recipients.getState().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(state) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by country.
		if(recipients.getCountry() != null) {
			filterName = recipients.getCountry().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(country) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by tax_free.
		if(recipients.getTaxFree() != null) {
			filterName = recipients.getTaxFree().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(tax_free) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(recipients.getIdle() != null) {
			filterName = recipients.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by legal_notice.
		if(recipients.getLegalNotice() != null) {
			filterName = recipients.getLegalNotice().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(legal_notice) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by contact_name.
		if(recipients.getContactName() != null) {
			filterName = recipients.getContactName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(contact_name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by contact_email.
		if(recipients.getContactEmail() != null) {
			filterName = recipients.getContactEmail().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(contact_email) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by contact_phone.
		if(recipients.getContactPhone() != null) {
			filterName = recipients.getContactPhone().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(contact_phone) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by contact_mobile.
		if(recipients.getContactMobile() != null) {
			filterName = recipients.getContactMobile().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(contact_mobile) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by bank_national_account_number.
		if(recipients.getBankNationalAccountNumber() != null) {
			filterName = recipients.getBankNationalAccountNumber().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(bank_national_account_number) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by bank_international_account_number.
		if(recipients.getBankInternationalAccountNumber() != null) {
			filterName = recipients.getBankInternationalAccountNumber().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(bank_international_account_number) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by bank_identification_code.
		if(recipients.getBankIdentificationCode() != null) {
			filterName = recipients.getBankIdentificationCode().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(bank_identification_code) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(recipients.getClientId() != null) {
			filterName = recipients.getClientId().toString();
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
		
		List<Recipients> result = jdbcTemplate.query(sql, params.toArray(), recipientsMapper);
		return result;
	}

}
