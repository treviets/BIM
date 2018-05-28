package com.redsun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * ActivityPrice Controller
 */
@Controller
@RequestMapping("activityprice")
public class ActivityPricesController extends BaseController{
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addActivityPrice (Model model){
		model.addAttribute("id", -1);
		return "ActivityPrices_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addActivityPrice(@RequestBody Map<String, Object> activityPrice, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = RedSunURLs.ACTIVITIES_URL_PRICE_SERVICE_INSERT;
		Object result = RestAPIHelper.post(getMainDomain(request) + redsunServiceUrl, activityPrice);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editActivityPrice(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "ActivityPrices_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateActivityPrice(@PathVariable("id") Integer id, @RequestBody Map<String, Object> activityPrice, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ACTIVITIES_URL_PRICE_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, activityPrice, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteActivityPrice(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ACTIVITIES_URL_PRICE_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listActivityPrice(){
		return "ActivityPrices_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getActivityPriceById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.ACTIVITIES_URL_PRICE_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listActivityPricesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo
	, HttpServletRequest request) {
		Map<String, Object> activityPrice = new HashMap<String, Object>();

		String serviceUrl = getMainDomain(request) + RedSunURLs.ACTIVITIES_URL_PRICE_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, activityPrice);
	}

}
