package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Quotation Controller
 */
@Controller
@RequestMapping("quotation")
public class QuotationsController extends BaseController{
	
	@Autowired
	private UserUtil userUtil;
	
	// Form screen.
	@RequestMapping("form")
	public String formQuotation(@RequestParam("id") Integer id, Model model){
		if(id == null) {
			model.addAttribute("id", -1);
		} else {
			model.addAttribute("id", id);
		}
		
		return "Financial/Incomes/Quotations_form";
	}
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addQuotation (Model model){
		model.addAttribute("id", -1);
		return "Financial/Incomes/Quotations_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addQuotation(@RequestBody Map<String, Object> quotation, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		quotation.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.QUOTATIONS_URL_SERVICE_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, quotation);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editQuotation(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Financial/Incomes/Quotations_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateQuotation(@PathVariable("id") Integer id, @RequestBody Map<String, Object> quotation, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		quotation.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.QUOTATIONS_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, quotation, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteQuotation(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.QUOTATIONS_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listQuotation(){
		return "Financial/Incomes/Quotations_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getQuotationById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.QUOTATIONS_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listQuotationsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo
	, HttpServletRequest request) {
		Map<String, Object> quotation = new HashMap<String, Object>();

		String serviceUrl = getMainDomain(request) + RedSunURLs.QUOTATIONS_URL_SERVICE_LIST + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, quotation);
	}
	
	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listQuotationsExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestParam Map<String, Object> filter,
			HttpServletRequest request) {
		String serviceUrl = getMainDomain(request) + RedSunURLs.QUOTATIONS_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, filter);
	}

}
