package com.redsun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * PaymentDelay Controller
 */
@Controller
@RequestMapping("paymentdelay")
public class PaymentDelaysController  extends BaseController{
	@Autowired
	ClientService clientService;

	// Add screen.
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addPaymentDelay(Model model) {
		model.addAttribute("id", -1);
		return "PaymentDelays_form";
	}

	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addPaymentDelay(@RequestBody Map<String, Object> paymentDelay, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PAYMENT_DEPLAY_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, paymentDelay);
		return result;
	}

	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editPaymentDelay(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "PaymentDelays_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updatePaymentDelay(@PathVariable("id") Integer id, @RequestBody Map<String, Object> paymentDelay, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PAYMENT_DEPLAY_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, paymentDelay, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deletePaymentDelay(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PAYMENT_DEPLAY_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	// List screen.
	@RequestMapping("list")
	public String listPaymentDelay() {
		return "PaymentDelays_list";
	}

	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getPaymentDelayById(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PAYMENT_DEPLAY_URL_SERVICE_GET_BY_ID + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listPaymentDelaysForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> paymentDelay = new HashMap<String, Object>();
		String serviceUrl = getMainDomain(request) + RedSunURLs.PAYMENT_DEPLAY_URL_SERVICE_FILTER + itemsPerPage + "/"
				+ pageNo;
		return RestAPIHelper.post(serviceUrl, paymentDelay);
	}

	@RequestMapping(value = "/listall", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		String getDataURL = getMainDomain(request) + RedSunURLs.PAYMENT_DEPLAY_URL_SERVICE_LIST_ALL + clientId;
		return RestAPIHelper.get(getDataURL, new HashMap<String, Object>());
	}
}
