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
 * Term Controller
 */
@Controller
@RequestMapping("term")
public class TermsController extends BaseController{
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addTerm (Model model){
		model.addAttribute("id", -1);
		return "Terms_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addTerm(@RequestBody Map<String, Object> term, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TERMS_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, term);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editTerm(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Terms_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateTerm(@PathVariable("id") Integer id, @RequestBody Map<String, Object> term, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TERMS_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, term, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteTerm(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TERMS_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listTerm(){
		return "Terms_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getTermById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TERMS_URL_GETBYID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listTermsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> term = new HashMap<String, Object>();
		String serviceUrl = getMainDomain(request) + RedSunURLs.TERMS_URL_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, term);
	}

}
