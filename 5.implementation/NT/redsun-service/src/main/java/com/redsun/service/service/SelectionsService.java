package com.redsun.service.service;

import com.redsun.service.entities.Result;

/**
 * Selections Service interface
 */
public interface SelectionsService {

	// List all projects for selection.
	Result listProjectsForSelection(final Integer clientId);

	// List quotation types for selection.
	Result listQuotationTypesForSelection(final Integer clientId);

	// List command types for selection.
	Result listCommandTypesForSelection(final Integer clientId);

	// List bill types for selection.
	Result listBillTypesForSelection(final Integer clientId);

	// List bills for selection.
	Result listBillsForSelection(final Integer clientId);

	// List payment types for selection.
	Result listPaymentTypesForSelection(final Integer clientId);

	// List payment modes for selection.
	Result listPaymentModesForSelection(final Integer clientId);

	// List statuses for selection.
	Result listStatusesForSelection(final Integer clientId);

	// List statuses by scope for selection.
	Result listStatusesForSelectionByScope(final Integer clientId, final String scope);

	// List customers for selection.
	Result listCustomersForSelection(final Integer clientId);

	// List users for selection.
	Result listUsersForSelection(final Integer clientId);

	// List resources for selection.
	Result listResourcesForSelection(final Integer clientId);

	// List contacts for selection.
	Result listContactsForSelection(final Integer clientId);

	Result listTypesForSelectionByScope(final Integer clientId, final String scope);

	Result listProvidersForSelection(final Integer clientId);

	Result listCallForTendersForSelection(final Integer clientId);

	Result listExpenseTypesSelection(Integer clientId);

	Result listDeliveryModeSelection(Integer clientId);
}
