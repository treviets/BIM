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
 * Command Controller
 */
@Controller
@RequestMapping("command")
public class CommandsController extends BaseController{
	
	@Autowired
	private UserUtil userUtil;
	
	// Form screen.
	@RequestMapping("form")
	public String formCommand(@RequestParam("id") Integer id, Model model){
		if(id == null) {
			model.addAttribute("id", -1);
		} else {
			model.addAttribute("id", id);
		}
		
		return "Financial/Incomes/Commands_form";
	}
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addCommand (Model model){
		model.addAttribute("id", -1);
		return "Financial/Incomes/Commands_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addCommand(@RequestBody Map<String, Object> command, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		command.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, command);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editCommand(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Financial/Incomes/Commands_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateCommand(@PathVariable("id") Integer id, @RequestBody Map<String, Object> command, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		command.put("clientId", clientId);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, command, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteCommand(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listCommand(){
		return "Financial/Incomes/Commands_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getCommandById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listCommandsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo
	, HttpServletRequest request) {
		Map<String, Object> command = new HashMap<String, Object>();

		String serviceUrl = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, command);
	}
	
	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listCommandsExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestParam Map<String, Object> filter, HttpServletRequest request) {
		String serviceUrl = getMainDomain(request) + RedSunURLs.COMMANDS_URL_SERVICE_FILTER_EXTENDS + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, filter);
	}

}
