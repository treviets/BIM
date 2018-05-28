package com.redsun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@EnableWebMvc
@RequestMapping(value = "reportexpense")
public class ReportExpenseController extends BaseController{

	@Autowired
	ClientService clientService;

	@Autowired
	UserUtil userUtil;
	
	
	@RequestMapping(value="/listcallandtender/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
	@ResponseBody
    public Object listCallAndTender(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model, HttpServletRequest request) throws Exception {
				
		String serviceUrl = getMainDomain(request) + RedSunURLs.PM_EXPENSE_CALLANDTENDER + projectId + "/" + startDate + "/" + endDate + "?clientId=";
		int clientId = userUtil.getClientIdOfLoginedUser();
		serviceUrl += clientId;
		
		return RestAPIHelper.get(serviceUrl, new HashMap<>());
    }

	@RequestMapping(value="/listbillandpayment/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
	@ResponseBody
    public Object listBillAndPayment(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model, HttpServletRequest request) throws Exception {
				
		String serviceUrl = getMainDomain(request) + RedSunURLs.PM_EXPENSE_BILLANDPAYMENT + projectId + "/" + startDate + "/" + endDate + "?clientId=";
		Integer clientId = userUtil.getClientIdOfLoginedUser();
		if(clientId != null && clientId > 0) {
			serviceUrl += clientId;
		}
		
		return RestAPIHelper.get(serviceUrl, new HashMap<>());
    }

	@RequestMapping(value="/listquotationandorder/{projectId}/{startDate}/{endDate}", method = {RequestMethod.GET})
	@ResponseBody
    public Object listQuotationAndOrder(@PathVariable("projectId") int projectId
    						, @PathVariable("startDate") String startDate
    						, @PathVariable("endDate") String endDate, Model model, HttpServletRequest request) throws Exception {
		String serviceUrl = getMainDomain(request) + RedSunURLs.PM_EXPENSE_QUOTATIONANDORDER + projectId + "/" + startDate + "/" + endDate + "?clientId=";
		Integer clientId = userUtil.getClientIdOfLoginedUser();
		if(clientId != null && clientId > 0) {
			serviceUrl += clientId;
		}
		
		return RestAPIHelper.get(serviceUrl, new HashMap<>());
    }
	
	
}
