package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ReportExpenseDao;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ReportExpenseService;

/**
 * Selections Service implementation 
 */
@Service
public class ReportExpenseServiceImpl implements ReportExpenseService {
    
    @Autowired
    private ReportExpenseDao reportExpenseDao;
	
    // Create success result.
    private Result createSuccessResult(final Object data, final String name) {
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put(name, data);
		// Return.
		return new Result(result, true);
    }

	// Get all CallAndTender
	public Result listCallAndTender(Integer clientId, int projectId, String fromDate, String toDate) {
		// Call DAO.
		final List<Map<Object, Object>> data = reportExpenseDao.listCallAndTender(clientId, projectId, fromDate, toDate);
		// Return Result.
		return createSuccessResult(data, "callAndTenders");
	}

	// Get all BillAndPayment
	public Result listBillAndPayment(Integer clientId, int projectId, String fromDate, String toDate) {
		// Call DAO.
		final List<Map<Object, Object>> data = reportExpenseDao.listBillAndPayment(clientId, projectId, fromDate, toDate);
		// Return Result.
		return createSuccessResult(data, "billAndPayments");
	}

	// Get all QuotationAndOrder
	public Result listQuotationAndOrder(Integer clientId, int projectId, String fromDate, String toDate) {
		// Call DAO.
		final List<Map<Object, Object>> data = reportExpenseDao.listQuotationAndOrder(clientId, projectId, fromDate, toDate);
		// Return Result.
		return createSuccessResult(data, "quotationAndOrders");
	}


}
