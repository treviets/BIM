package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

public interface ReportExpenseDao {

	// Get all CallAndTender
	List<Map<Object, Object>> listCallAndTender(Integer clientId, int projectId, String fromDate, String toDate);

	// Get all BillAndPayment
	List<Map<Object, Object>> listBillAndPayment(Integer clientId, int projectId, String fromDate, String toDate);

	// Get all QuotationAndOrder
	List<Map<Object, Object>> listQuotationAndOrder(Integer clientId, int projectId, String fromDate, String toDate);

}
