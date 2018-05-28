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
 * Bill Controller
 */
@Controller
@RequestMapping("bill")
public class BillsController extends BaseController{
	
	@Autowired
	private UserUtil userUtil;
	
	
	// Form screen.
	@RequestMapping("form")
	public String formBill(@RequestParam("id") Integer id, Model model){
		if(id == null) {
			model.addAttribute("id", -1);
		} else {
			model.addAttribute("id", id);
		}
		
		return "Financial/Incomes/Bills_form";
	}
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addBill (Model model){
		model.addAttribute("id", -1);
		return "Financial/Incomes/Bills_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addBill(@RequestBody Map<String, Object> bill, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		bill.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.BILL_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, bill);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editBill(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Financial/Incomes/Bills_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateBill(@PathVariable("id") Integer id, @RequestBody Map<String, Object> bill, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		bill.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.BILL_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, bill, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteBill(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.BILL_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listBill(){
		return "Financial/Incomes/Bills_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getBillById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.BILL_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listBillsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo
	, HttpServletRequest request) {
		Map<String, Object> bill = new HashMap<String, Object>();

		String serviceUrl = getMainDomain(request) + RedSunURLs.BILL_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, bill);
	}
	
	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listBillsExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestParam Map<String, Object> filter, HttpServletRequest request) {
		String serviceUrl = getMainDomain(request) + RedSunURLs.BILL_URL_SERVICE_FILTER_EXTENDS + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, filter);
	}

}
