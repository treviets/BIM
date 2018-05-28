package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

/**
 * Selections DAO interface
 */
public interface SelectionsDao {

	// List all projects for selection.
	List<Map<Object, Object>> listProjectsForSelection(final Integer clientId);

	// List quotation types for selection.
	List<Map<Object, Object>> listQuotationTypesForSelection(final Integer clientId);

	// List command types for selection.
	List<Map<Object, Object>> listCommandTypesForSelection(final Integer clientId);

	// List bill types for selection.
	List<Map<Object, Object>> listBillTypesForSelection(final Integer clientId);

	// List all bills for selection.
	List<Map<Object, Object>> listBillsForSelection(final Integer clientId);

	// List payment types for selection.
	List<Map<Object, Object>> listPaymentTypesForSelection(final Integer clientId);

	// List payment modes for selection.
	List<Map<Object, Object>> listPaymentModesForSelection(final Integer clientId);

	// List statuses for selection.
	List<Map<Object, Object>> listStatusesForSelection(final Integer clientId);

	// List statuses by scope for selection.
	List<Map<Object, Object>> listStatusesForSelectionByScope(final Integer clientId, final String scope);

	// List customers for selection.
	List<Map<Object, Object>> listCustomersForSelection(final Integer clientId);

	// List users for selection.
	List<Map<Object, Object>> listUsersForSelection(final Integer clientId);

	// List resources for selection.
	List<Map<Object, Object>> listResourcesForSelection(final Integer clientId);

	// List contacts for selection.
	List<Map<Object, Object>> listContactsForSelection(final Integer clientId);

	List<Map<Object, Object>> listTypesForSelectionByScope(final Integer clientId, final String scope);
	List<Map<Object, Object>> listProvidersForSelection(final Integer clientId);
	List<Map<Object, Object>> listCallForTendersForSelection(final Integer clientId);
	List<Map<Object, Object>> listExpenseTypesSelection(final Integer clientId);
	List<Map<Object, Object>> listDeliveryModeSelection(final Integer clientId);

}
