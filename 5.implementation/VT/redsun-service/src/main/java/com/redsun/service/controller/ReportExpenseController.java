package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.service.ReportExpenseService;

@RestController
@RequestMapping(value = "reportexpense")
public class ReportExpenseController { 
	
	
	@Autowired
	ReportExpenseService reportExpenseService;
	 
    @RequestMapping(value="/listcallandtender/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
    public Object listCallAndTender(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, @RequestParam("clientId") Integer clientId, Model model) {
    	
        return reportExpenseService.listCallAndTender(clientId, projectId, startDate, endDate);
    }
	 
    @RequestMapping(value="/listbillandpayment/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
    public Object listBillAndPayment(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, @RequestParam("clientId") Integer clientId, Model model) {
    	
        return reportExpenseService.listBillAndPayment(clientId, projectId, startDate, endDate);
    }
	 
    @RequestMapping(value="/listquotationandorder/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
    public Object listQuotationAndOrder(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, @RequestParam("clientId") Integer clientId, Model model) {
    	
        return reportExpenseService.listQuotationAndOrder(clientId, projectId, startDate, endDate);
    }
    
}
