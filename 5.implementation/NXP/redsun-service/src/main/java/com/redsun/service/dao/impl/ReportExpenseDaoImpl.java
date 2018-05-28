package com.redsun.service.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ReportExpenseDao;
import com.redsun.service.dao.mapper.MapsRowMapper;



@Repository
public class ReportExpenseDaoImpl implements ReportExpenseDao {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MapsRowMapper mapsRowMapper;


	// CallAndTender.
	private final static String SQL_SELECT_CALLANDTENDER_OVERVIEW = 
			"SELECT sum.id_project, projects.name as project_name, SUM(sum.max_amount) as call_amount, SUM(sum.planned_full_amount) as tender_amount FROM (SELECT call_for_tenders.id_project, call_for_tenders.id, call_for_tenders.max_amount, SUM(tenders.planned_full_amount) as planned_full_amount FROM call_for_tenders as call_for_tenders LEFT JOIN tenders as tenders ON call_for_tenders.id = tenders.id_call_for_tender WHERE (call_for_tenders.id_project = ? OR TRUE) AND (call_for_tenders.max_amount IS NOT NULL) AND call_for_tenders.done_date >= ?::date AND call_for_tenders.done_date <= ?::date GROUP BY call_for_tenders.id_project, call_for_tenders.id) as sum INNER JOIN projects as projects ON sum.id_project = projects.id GROUP BY sum.id_project, projects.name";

	// BillAndPayment.
	private final static String SQL_SELECT_BILLANDPAYMENT_OVERVIEW = 
			"SELECT sum.id_project, projects.name as project_name, SUM(sum.full_amount) as bill_amount, SUM(sum.payment_amount) as payment_amount FROM (SELECT bills.id_project, bills.id, bills.full_amount, SUM(payments.payment_amount) as payment_amount FROM bills as bills LEFT JOIN payments as payments ON bills.id = payments.id_bill WHERE (bills.id_project = ? OR TRUE) AND (bills.full_amount IS NOT NULL) AND bills.payment_date >= ?::date AND bills.payment_date <= ?::date GROUP BY bills.id_project, bills.id) as sum INNER JOIN projects as projects ON sum.id_project = projects.id GROUP BY sum.id_project, projects.name";

	// QuotationAndOrder.
	private final static String SQL_SELECT_QUOTATIONANDORDER_OVERVIEW = 
			"SELECT commands.id_project, projects.name as project_name, commands.id, SUM(commands.full_amount) as full_amount FROM commands as commands INNER JOIN projects as projects ON commands.id_project = projects.id WHERE (commands.id_project = ? OR TRUE) AND (commands.full_amount IS NOT NULL) AND commands.done_date >= ?::date AND commands.done_date <= ?::date GROUP BY commands.id_project, commands.id, projects.name";
	
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	private List<Map<Object, Object>> listSelection(String sql, final Integer clientId, final List<Object> params) {
		if(clientId != null){
			sql += " and client_id = ?";
			params.add(clientId);
		}
		return jdbcTemplate.query(sql, params.toArray(), mapsRowMapper);
	}

	@Override
	public List<Map<Object, Object>> listCallAndTender(Integer clientId, int projectId, String fromDate, String toDate) {
		List<Object> params = new LinkedList<>();
		params.add(projectId);
		params.add(fromDate);
		params.add(toDate);
		
		return listSelection(SQL_SELECT_CALLANDTENDER_OVERVIEW, clientId, params);
	}

	@Override
	public List<Map<Object, Object>> listBillAndPayment(Integer clientId, int projectId, String fromDate, String toDate) {
		List<Object> params = new LinkedList<>();
		params.add(projectId);
		params.add(fromDate);
		params.add(toDate);
		
		return listSelection(SQL_SELECT_BILLANDPAYMENT_OVERVIEW, clientId, params);
	}

	@Override
	public List<Map<Object, Object>> listQuotationAndOrder(Integer clientId, int projectId, String fromDate, String toDate) {
		List<Object> params = new LinkedList<>();
		params.add(projectId);
		params.add(fromDate);
		params.add(toDate);
		
		return listSelection(SQL_SELECT_QUOTATIONANDORDER_OVERVIEW, clientId, params);
	}
	
	
}
