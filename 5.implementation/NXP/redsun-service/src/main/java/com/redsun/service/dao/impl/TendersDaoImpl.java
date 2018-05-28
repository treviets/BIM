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

import com.redsun.service.dao.TendersDao;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.dao.mapper.TendersRowMapper;
import com.redsun.service.entities.Tenders;
import com.redsun.service.utils.RedSunColumnNames;
import com.redsun.service.utils.RedSunQueryCollections;

/**
 * Tenders DAO implementation 
 */
@Repository
public class TendersDaoImpl implements TendersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private TendersRowMapper tendersMapper;
	@Autowired
	private MapsRowMapper mapper;

	private final static String AUTO_INCREMENTED_COLUMN = RedSunColumnNames.tenders_id;

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.VARCHAR, //name varchar(200)
			java.sql.Types.INTEGER, //id_tender_type int4
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_call_for_tender int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.TIMESTAMP, //creation_date timestamp
			java.sql.Types.INTEGER, //id_provider int4
			java.sql.Types.VARCHAR, //external_reference varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.TIMESTAMP, //request_date_time timestamp
			java.sql.Types.TIMESTAMP, //expected_tender_date_time timestamp
			java.sql.Types.TIMESTAMP, //reception_date_time timestamp
			java.sql.Types.NUMERIC, //evaluation_value numeric
			java.sql.Types.INTEGER, //evaluation_rank int4
			java.sql.Types.DATE, //offer_validity_end_date date
			java.sql.Types.NUMERIC, //planned_amount numeric
			java.sql.Types.NUMERIC, //tax_pct numeric
			java.sql.Types.NUMERIC, //planned_tax_amount numeric
			java.sql.Types.NUMERIC, //planned_full_amount numeric
			java.sql.Types.NUMERIC, //initial_amount numeric
			java.sql.Types.NUMERIC, //initial_tax_amount numeric
			java.sql.Types.NUMERIC, //initial_full_amount numeric
			java.sql.Types.VARCHAR, //delivery_delay varchar(100)
			java.sql.Types.DATE, //delivery_date date
			java.sql.Types.VARCHAR, //payment_condition varchar(100)
			java.sql.Types.NUMERIC, //evaluation numeric
			java.sql.Types.VARCHAR, //result text(2147483647)
			java.sql.Types.INTEGER, //handled bit
			java.sql.Types.INTEGER, //done bit
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //cancelled bit
			java.sql.Types.DATE, //handled_date date
			java.sql.Types.DATE, //done_date date
			java.sql.Types.DATE, //idle_date date
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into tenders (reference, name, id_tender_type, id_project, id_call_for_tender, id_user, creation_date, id_provider, external_reference, description, id_status, id_resource, id_contact, request_date_time, expected_tender_date_time, reception_date_time, evaluation_value, evaluation_rank, offer_validity_end_date, planned_amount, tax_pct, planned_tax_amount, planned_full_amount, initial_amount, initial_tax_amount, initial_full_amount, delivery_delay, delivery_date, payment_condition, evaluation, result, handled, done, idle, cancelled, handled_date, done_date, idle_date, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?::bit, ?::bit, ?::bit, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update tenders set reference = ?, name = ?, id_tender_type = ?, id_project = ?, id_tender_status = ?, id_user = ?, creation_date = ?, id_provider = ?, external_reference = ?, description = ?, id_status = ?, id_resource = ?, id_contact = ?, request_date_time = ?, expected_tender_date_time = ?, reception_date_time = ?, evaluation_value = ?, evaluation_rank = ?, offer_validity_end_date = ?, planned_amount = ?, tax_pct = ?, planned_tax_amount = ?, planned_full_amount = ?, initial_amount = ?, initial_tax_amount = ?, initial_full_amount = ?, delivery_delay = ?, delivery_date = ?, payment_condition = ?, evaluation = ?, result = ?, handled = ?::bit, done = ?::bit, idle = ?::bit, cancelled = ?::bit, handled_date = ?, done_date = ?, idle_date = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from tenders where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from tenders";

	private final static String SQL_COUNT = 
		"select count(*) from tenders where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, reference, name, id_tender_type, id_project, id_call_for_tender, id_tender_status, id_user, creation_date, id_provider, external_reference, description, id_status, id_resource, id_contact, request_date_time, expected_tender_date_time, reception_date_time, evaluation_value, evaluation_rank, offer_validity_end_date, planned_amount, tax_pct, planned_tax_amount, planned_full_amount, initial_amount, initial_tax_amount, initial_full_amount, delivery_delay, delivery_date, payment_condition, evaluation, result, handled, done, idle, cancelled, handled_date, done_date, idle_date, client_id, 0 as ext_col_count from tenders where id = ?";
	

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Tenders tenders) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(tenders));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Tenders tenders)  {
		return new Object[] {
			tenders.getReference(), 			
			tenders.getName(), 			
			tenders.getIdTenderType(), 			
			tenders.getIdProject(), 			
			tenders.getIdCallForTender(), 			
			tenders.getIdUser(),
			tenders.getCreationDate(), 			
			tenders.getIdProvider(), 			
			tenders.getExternalReference(), 			
			tenders.getDescription(), 			
			tenders.getIdStatus(), 			
			tenders.getIdResource(), 			
			tenders.getIdContact(), 			
			tenders.getRequestDateTime(), 			
			tenders.getExpectedTenderDateTime(), 			
			tenders.getReceptionDateTime(), 			
			tenders.getEvaluationValue(), 			
			tenders.getEvaluationRank(), 			
			tenders.getOfferValidityEndDate(), 			
			tenders.getPlannedAmount(), 			
			tenders.getTaxPct(), 			
			tenders.getPlannedTaxAmount(), 			
			tenders.getPlannedFullAmount(), 			
			tenders.getInitialAmount(), 			
			tenders.getInitialTaxAmount(), 			
			tenders.getInitialFullAmount(), 			
			tenders.getDeliveryDelay(), 			
			tenders.getDeliveryDate(), 			
			tenders.getPaymentCondition(), 			
			tenders.getEvaluation(), 			
			tenders.getResult(), 			
			tenders.getHandled(), 			
			tenders.getDone(), 			
			tenders.getIdle(), 			
			tenders.getCancelled(), 			
			tenders.getHandledDate(), 			
			tenders.getDoneDate(), 			
			tenders.getIdleDate(), 			
			tenders.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Tenders tenders) {
		return new Object[] {		
			tenders.getReference(),
			tenders.getName(),
			tenders.getIdTenderType(),
			tenders.getIdProject(),
			tenders.getIdCallForTender(),
			tenders.getIdUser(),
			tenders.getCreationDate(),
			tenders.getIdProvider(),
			tenders.getExternalReference(),
			tenders.getDescription(),
			tenders.getIdStatus(),
			tenders.getIdResource(),
			tenders.getIdContact(),
			tenders.getRequestDateTime(),
			tenders.getExpectedTenderDateTime(),
			tenders.getReceptionDateTime(),
			tenders.getEvaluationValue(),
			tenders.getEvaluationRank(),
			tenders.getOfferValidityEndDate(),
			tenders.getPlannedAmount(),
			tenders.getTaxPct(),
			tenders.getPlannedTaxAmount(),
			tenders.getPlannedFullAmount(),
			tenders.getInitialAmount(),
			tenders.getInitialTaxAmount(),
			tenders.getInitialFullAmount(),
			tenders.getDeliveryDelay(),
			tenders.getDeliveryDate(),
			tenders.getPaymentCondition(),
			tenders.getEvaluation(),
			tenders.getResult(),
			tenders.getHandled(),
			tenders.getDone(),
			tenders.getIdle(),
			tenders.getCancelled(),
			tenders.getHandledDate(),
			tenders.getDoneDate(),
			tenders.getIdleDate(),
			tenders.getClientId(),
			tenders.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Tenders tenders)  {
		return new Object[] {
			tenders.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Tenders tenders) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(tenders), keyHolder);

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
	public int update(final Tenders tenders) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(tenders));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return tenders.getId();
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
	public List<Tenders> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Tenders> result = jdbcTemplate.query(SQL_SELECT, primaryKey, tendersMapper);
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

	public List<Map<Object, Object>> listTendersForPageAndFilter(final int itemsPerPage, final int pageNo, final Tenders tenders) {
		final Integer offset = (pageNo - 1) * itemsPerPage;
		final String query = RedSunQueryCollections.Tenders_join_Projects_join_Types_join_Statuses_getAll;
		List<Map<Object, Object>> result = jdbcTemplate.query(query, new Object[] {itemsPerPage, offset }, mapper);
		return result;
	}

}
