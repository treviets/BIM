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

import com.redsun.service.dao.CallForTendersDao;
import com.redsun.service.dao.mapper.CallForTendersRowMapper;
import com.redsun.service.dao.mapper.MapsRowMapper;
import com.redsun.service.entities.CallForTenders;
import com.redsun.service.exceptions.CustomSQLException;
import com.redsun.service.utils.RedSunColumnNames;
import com.redsun.service.utils.RedSunQueryCollections;

/**
 * 	CallForTenders class
 * 	Author: HauLL
 */
@Repository
public class CallForTendersDaoImpl implements CallForTendersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MapsRowMapper mapper;
    @Autowired
    private CallForTendersRowMapper callForTendersMapper;
  
	/**
	 * get some field of call for tender table
	 * @param clientId
	 * @return
	 */
	@Override
	public List<Map<Object, Object>> getAll(final Integer clientId, final Integer pageNo, final Integer itemsPerPage) {
		final Integer offset = (pageNo - 1) * itemsPerPage;
		final String query = RedSunQueryCollections.CallForTenders_join_Projects_join_Types_join_Statuses_getAllByClientId;
		List<Map<Object, Object>> result = jdbcTemplate.query(query, new Object[] { clientId, itemsPerPage, offset }, mapper);
		return result;
	}

	/**
	 * get tender by tender id & client id
	 * @param clientId
	 * @param tenderId
	 * @return
	 */
	@Override
	public List<CallForTenders> getById(final Integer clientId, final Integer tenderId) {
		final String query = RedSunQueryCollections.CallForTenders_getTenderByClientIdAndTenderId;
		List<CallForTenders> result = jdbcTemplate.query(query, new Object[]{clientId, tenderId}, callForTendersMapper);
		return result;
	}

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_call_for_tender_type int4
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.VARCHAR, //technical_requirements text(2147483647)
			java.sql.Types.VARCHAR, //business_requirements text(2147483647)
			java.sql.Types.VARCHAR, //other_requirements text(2147483647)
			java.sql.Types.TIMESTAMP, //creation_date timestamp
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.TIMESTAMP, //send_date_time timestamp
			java.sql.Types.TIMESTAMP, //expected_tender_date_time timestamp
			java.sql.Types.NUMERIC, //max_amount numeric
			java.sql.Types.DATE, //delivery_date date
			java.sql.Types.NUMERIC, //evaluation_max_value numeric
			java.sql.Types.INTEGER, //fix_value bit
			java.sql.Types.INTEGER, //id_product int4
			java.sql.Types.INTEGER, //id_product_version int4
			java.sql.Types.INTEGER, //id_component int4
			java.sql.Types.INTEGER, //id_component_version int4
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

	protected Object[] getValuesForInsert(final CallForTenders callForTenders)  {
		return new Object[] {
				callForTenders.getReference(),
				callForTenders.getName(),
				callForTenders.getIdCallForTenderType(),
				callForTenders.getIdProject(),
				callForTenders.getIdUser(),
				callForTenders.getDescription(),
				callForTenders.getTechnicalRequirements(),
				callForTenders.getBusinessRequirements(),
				callForTenders.getOtherRequirements(),
				callForTenders.getCreationDate(),
				callForTenders.getIdStatus(),
				callForTenders.getIdResource(),
				callForTenders.getSendDateTime(),
				callForTenders.getExpectedTenderDateTime(),
				callForTenders.getMaxAmount(),
				callForTenders.getDeliveryDate(),
				callForTenders.getEvaluationMaxValue(),
				callForTenders.getFixValue(),
				callForTenders.getIdProduct(),
				callForTenders.getIdProductVersion(),
				callForTenders.getIdComponent(),
				callForTenders.getIdComponentVersion(),
				callForTenders.getResult(),
				callForTenders.getHandled(),
				callForTenders.getDone(),
				callForTenders.getIdle(),
				callForTenders.getCancelled(),
				callForTenders.getHandledDate(),
				callForTenders.getDoneDate(),
				callForTenders.getIdleDate(),
				callForTenders.getClientId()
		};
	}

	protected Object[] getValuesForUpdate(final CallForTenders callForTenders) {
		return new Object[] {
				callForTenders.getReference(),
				callForTenders.getName(),
				callForTenders.getIdCallForTenderType(),
				callForTenders.getIdProject(),
				callForTenders.getIdUser(),
				callForTenders.getDescription(),
				callForTenders.getTechnicalRequirements(),
				callForTenders.getBusinessRequirements(),
				callForTenders.getOtherRequirements(),
				callForTenders.getCreationDate(),
				callForTenders.getIdStatus(),
				callForTenders.getIdResource(),
				callForTenders.getSendDateTime(),
				callForTenders.getExpectedTenderDateTime(),
				callForTenders.getMaxAmount(),
				callForTenders.getDeliveryDate(),
				callForTenders.getEvaluationMaxValue(),
				callForTenders.getFixValue(),
				callForTenders.getIdProduct(),
				callForTenders.getIdProductVersion(),
				callForTenders.getIdComponent(),
				callForTenders.getIdComponentVersion(),
				callForTenders.getResult(),
				callForTenders.getHandled(),
				callForTenders.getDone(),
				callForTenders.getIdle(),
				callForTenders.getCancelled(),
				callForTenders.getHandledDate(),
				callForTenders.getDoneDate(),
				callForTenders.getIdleDate(),
				callForTenders.getClientId(),
				callForTenders.getId()
		};
	}

	private PreparedStatementCreator getPreparedStatementCreator(final CallForTenders callForTenders) {
		final PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(RedSunQueryCollections.CallForTenders_insertTender, SQL_INSERT_TYPES) ;
		factory.setGeneratedKeysColumnNames(new String[]{RedSunColumnNames.call_for_tenders_id});
		return factory.newPreparedStatementCreator(getValuesForInsert(callForTenders));
	}

	@Override
	public Integer insert(final CallForTenders callForTenders) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final int result = jdbcTemplate.update(getPreparedStatementCreator(callForTenders), keyHolder);
		if (result != 1) {
			throw new CustomSQLException("Unexpected return value after INSERT : " + result + " (1 expected) ");
		}
		final Number key = keyHolder.getKey();
		if (key != null) {
			return key.intValue();
		} else {
			throw new CustomSQLException("Cannot retrive generated key after INSERT : KeyHolder returns null");
		}
	}

	@Override
	public Integer update(final CallForTenders callForTenders) {
		final int result = jdbcTemplate.update(RedSunQueryCollections.CallForTenders_updateTender, getValuesForUpdate(callForTenders));
		if (result != 0 && result != 1) {
			throw new CustomSQLException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return callForTenders.getId();
	}

	@Override
	public Integer delete(final Integer id) {
		final int result = jdbcTemplate.update(RedSunQueryCollections.CallForTenders_deleteTender, new Object[] { id });
		if (result != 0 && result != 1) {
			throw new CustomSQLException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

}
