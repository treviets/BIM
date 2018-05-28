package com.redsun.service.service;

import com.redsun.service.entities.Result;

/**
 * ReportExpense Service interface
 */
public interface ReportExpenseService {

	// Get all CallAndTender
	Result listCallAndTender(Integer clientId, int projectId, String fromDate, String toDate);

	// Get all BillAndPayment
	Result listBillAndPayment(Integer clientId, int projectId, String fromDate, String toDate);

	// Get all QuotationAndOrder
	Result listQuotationAndOrder(Integer clientId, int projectId, String fromDate, String toDate);

}
