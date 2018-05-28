package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.SelectionsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.service.SelectionsService;

/**
 * Selections Service implementation 
 */
@Service
public class SelectionsServiceImpl implements SelectionsService {
    
    @Autowired
    private SelectionsDao selectionsDao;
	
    // Create success result.
    private Result createSuccessResult(final Object data, final String name) {
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put(name, data);
		// Return.
		return new Result(result, true);
    }
    
	// List all projects for selection.
	public Result listProjectsForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listProjectsForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "projects");
	}

	// List quotation types for selection.
	public Result listQuotationTypesForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listQuotationTypesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "quotationTypes");
	}

	// List command types for selection.
	public Result listCommandTypesForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listCommandTypesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "commandTypes");
	}

	// List bill types for selection.
	public Result listBillTypesForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listBillTypesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "billTypes");
	}

	// List bills for selection.
	public Result listBillsForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listBillsForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "bills");
	}

	// List payment types for selection.
	public Result listPaymentTypesForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listPaymentTypesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "paymentTypes");
	}

	// List payment modes for selection.
	public Result listPaymentModesForSelection(final Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listPaymentModesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "paymentModes");
	}

	// List all statuses for selection.
	public Result listStatusesForSelection(Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listStatusesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "statuses");
	}

	// List all statuses by scope for selection.
	public Result listStatusesForSelectionByScope(Integer clientId, String scope) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listStatusesForSelectionByScope(clientId, scope);
		// Return Result.
		return createSuccessResult(data, "statuses");
	}

	// List all customers for selection.
	public Result listCustomersForSelection(Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listCustomersForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "customers");
	}

	// List all users for selection.
	public Result listUsersForSelection(Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listUsersForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "users");
	}

	// List all resources for selection.
	public Result listResourcesForSelection(Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listResourcesForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "resources");
	}

	// List all contacts for selection.
	public Result listContactsForSelection(Integer clientId) {
		// Call DAO.
		final List<Map<Object, Object>> data = selectionsDao.listContactsForSelection(clientId);
		// Return Result.
		return createSuccessResult(data, "contacts");
	}

	@Override
	public Result listTypesForSelectionByScope(Integer clientId, String scope) {
		final List<Map<Object, Object>> data = selectionsDao.listTypesForSelectionByScope(clientId, scope);
		return createSuccessResult(data, "types");
	}

	@Override
	public Result listProvidersForSelection(Integer clientId) {
		final List<Map<Object, Object>> data = selectionsDao.listProvidersForSelection(clientId);
		return createSuccessResult(data, "providers");
	}

	@Override
	public Result listCallForTendersForSelection(Integer clientId) {
		final List<Map<Object, Object>> data = selectionsDao.listCallForTendersForSelection(clientId);
		return createSuccessResult(data, "callfortenders");
	}

	@Override
	public Result listExpenseTypesSelection(Integer clientId) {
		final List<Map<Object, Object>> data = selectionsDao.listExpenseTypesSelection(clientId);
		return createSuccessResult(data, "expensetypes");
	}

	@Override
	public Result listDeliveryModeSelection(Integer clientId) {
		final List<Map<Object, Object>> data = selectionsDao.listDeliveryModeSelection(clientId);
		return createSuccessResult(data, "deliverymodes");
	}

}
