package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.redsun.service.service.SelectionsService;

/**
 * Selections Controller
 */
@RestController
@RequestMapping("selections-service")
public class SelectionsController {
	
	// Service.
	@Autowired
	private SelectionsService selectionsService;

	// List all projects for selection.
	@RequestMapping(value = "list-projects", method = { RequestMethod.GET })
	public Object listProjectsForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listProjectsForSelection(clientId);
	}
	
	// List all quotation types for selection.
	@RequestMapping(value = "list-quotation-types", method = { RequestMethod.GET })
	public Object listQuotationTypesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listQuotationTypesForSelection(clientId);
	}
	
	// List all command types for selection.
	@RequestMapping(value = "list-command-types", method = { RequestMethod.GET })
	public Object listCommandTypesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listCommandTypesForSelection(clientId);
	}
	
	// List all bill types for selection.
	@RequestMapping(value = "list-bill-types", method = { RequestMethod.GET })
	public Object listBillTypesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listBillTypesForSelection(clientId);
	}
	
	// List all bills for selection.
	@RequestMapping(value = "list-bills", method = { RequestMethod.GET })
	public Object listBillsForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listBillsForSelection(clientId);
	}
	
	// List all payment types for selection.
	@RequestMapping(value = "list-payment-types", method = { RequestMethod.GET })
	public Object listPaymentTypesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listPaymentTypesForSelection(clientId);
	}
	
	// List all payment modes for selection.
	@RequestMapping(value = "list-payment-modes", method = { RequestMethod.GET })
	public Object listPaymentModesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listPaymentModesForSelection(clientId);
	}

	// List statuses for selection.
	@RequestMapping(value = "list-statuses", method = { RequestMethod.GET })
	public Object listStatusesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listStatusesForSelection(clientId);
	}

	// List statuses by scope for selection.
	@RequestMapping(value = "list-statuses/{scope}", method = { RequestMethod.GET })
	public Object listStatusesForSelectionByScope(@RequestParam(required = false) final Integer clientId, @PathVariable(required = false) final String scope) {
		return selectionsService.listStatusesForSelectionByScope(clientId, scope);
	}

	// List customers for selection.
	@RequestMapping(value = "list-customers", method = { RequestMethod.GET })
	public Object listCustomersForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listCustomersForSelection(clientId);
	}

	// List users for selection.
	@RequestMapping(value = "list-users", method = { RequestMethod.GET })
	public Object listUsersForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listUsersForSelection(clientId);
	}

	// List resources for selection.
	@RequestMapping(value = "list-resources", method = { RequestMethod.GET })
	public Object listResourcesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listResourcesForSelection(clientId);
	}

	// List contacts for selection.
	@RequestMapping(value = "list-contacts", method = { RequestMethod.GET })
	public Object listContactsForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listContactsForSelection(clientId);
	}

	@RequestMapping(value = "list-types/{scope}", method = { RequestMethod.GET })
	public Object listTypesForSelectionByScope(@PathVariable final String scope) {
		return selectionsService.listTypesForSelectionByScope(null, scope);
	}

	@RequestMapping(value = "list-providers", method = { RequestMethod.GET })
	public Object listProvidersForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listProvidersForSelection(clientId);
	}

	@RequestMapping(value = "list-callfortenders", method = { RequestMethod.GET })
	public Object listCallForTendersForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listCallForTendersForSelection(clientId);
	}

	@RequestMapping(value = "list-expensetypes", method = { RequestMethod.GET })
	public Object listExpenseTypesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listExpenseTypesSelection(clientId);
	}

	@RequestMapping(value = "list-deliverymodes", method = { RequestMethod.GET })
	public Object listDeliveryModesForSelection(@RequestParam(required = false) final Integer clientId) {
		return selectionsService.listDeliveryModeSelection(clientId);
	}
	
}
