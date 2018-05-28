package com.redsun.service.dao.impl;

import com.redsun.service.dao.SelectionsDao;
import com.redsun.service.dao.mapper.SelectionsRowMapper;
import com.redsun.service.utils.RedSunQueryCollections;
import com.redsun.service.utils.TypesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Quotations DAO implementation 
 */
@Repository
public class SelectionsDaoImpl implements SelectionsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SelectionsRowMapper selectionsRowMapper;

	// Projects.
	private final static String SQL_SELECT_PROJECTS = 
			"SELECT id, name FROM projects WHERE true";

	// Quotation Types.
	private final static String SQL_SELECT_QUOTATION_TYPES = 
			"SELECT id, name FROM types WHERE scope= 'Quotation'";

	// Command Types.
	private final static String SQL_SELECT_COMMAND_TYPES = 
			"SELECT id, name FROM types WHERE scope= 'Command'";

	// Bill Types.
	private final static String SQL_SELECT_BILL_TYPES = 
			"SELECT id, name FROM types WHERE scope= 'Bill'";

	// Bills.
	private final static String SQL_SELECT_BILLS = 
			"SELECT id, name FROM bills";

	// Payment Types.
	private final static String SQL_SELECT_PAYMENT_TYPES = 
			"SELECT id, name FROM types WHERE scope= 'Payment'";

	// Payment Modes.
	private final static String SQL_SELECT_PAYMODE_TYPES = 
			"SELECT id, name FROM payment_modes";

	// Statuses.
	private final static String SQL_SELECT_STATUSES = 
			"SELECT id, name FROM statuses";

	// Statuses.
	private final static String SQL_SELECT_STATUSES_SCOPE = 
			"SELECT id, name FROM statuses WHERE UPPER(scope) = UPPER(?)";
	
	// Customers.
	private final static String SQL_SELECT_CUSTOMERS = 
			"SELECT id, name FROM clients WHERE id_client_type = " + TypesConstants.CUSTOMER_ID;
	
	// Users.
	private final static String SQL_SELECT_USERS =
			"SELECT id, name FROM resources WHERE is_user = B'1'";
	
	// Resources.
	private final static String SQL_SELECT_RESOURCES =
			"SELECT id, name FROM resources WHERE is_resource = B'1'";
	
	// Contacts.
	private final static String SQL_SELECT_CONTACTS =
			"SELECT id, name FROM resources WHERE is_user = B'0' and is_resource = B'0'";

	// Call for tenders
	private final static String SQL_SELECT_CALL_FOR_TENDERS =
			"SELECT id, name FROM call_for_tenders";

	// Providers
	private final static String SQL_SELECT_PROVIDERS =
			"SELECT id, name FROM providers";

	// Expense Types
	private final static String SQL_SELECT_EXPENSE_TYPES =
			"SELECT id, name FROM expense_detail_types";

	private final static String SQL_SELECT_DELIVERY_MODE =
			"SELECT id, name FROM delivery_modes";

	
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	private List<Map<Object, Object>> listSelection(String sql, final Integer clientId, final List<Object> params) {
		if(clientId != null){
			sql += " and client_id = ?";
			params.add(clientId);
		}
		return jdbcTemplate.query(sql, params.toArray(), selectionsRowMapper);
	}
	
	@Override
	public List<Map<Object, Object>> listProjectsForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_PROJECTS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listQuotationTypesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_QUOTATION_TYPES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listCommandTypesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_COMMAND_TYPES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listBillTypesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_BILL_TYPES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listBillsForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_BILLS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listPaymentTypesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_PAYMENT_TYPES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listPaymentModesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_PAYMODE_TYPES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listStatusesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_STATUSES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listStatusesForSelectionByScope(final Integer clientId, final String scope) {
		final List<Object> params = new LinkedList<>();
		params.add(scope);
		
		return listSelection(SQL_SELECT_STATUSES_SCOPE, clientId, params);
	}

	@Override
	public List<Map<Object, Object>> listCustomersForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_CUSTOMERS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listUsersForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_USERS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listResourcesForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_RESOURCES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listContactsForSelection(final Integer clientId) {
		return listSelection(SQL_SELECT_CONTACTS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listTypesForSelectionByScope(final Integer clientId, final String scope) {
		final List<Object> params = new LinkedList<>();
		params.add(scope);
		return listSelection(RedSunQueryCollections.Types_getTypeByScope, clientId, params);
	}

	@Override
	public List<Map<Object, Object>> listProvidersForSelection(Integer clientId) {
		return listSelection(SQL_SELECT_PROVIDERS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listCallForTendersForSelection(Integer clientId) {
		return listSelection(SQL_SELECT_CALL_FOR_TENDERS, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listExpenseTypesSelection(Integer clientId) {
		return listSelection(SQL_SELECT_EXPENSE_TYPES, clientId, new LinkedList<>());
	}

	@Override
	public List<Map<Object, Object>> listDeliveryModeSelection(Integer clientId) {
		return listSelection(SQL_SELECT_DELIVERY_MODE, clientId, new LinkedList<>());
	}

}
