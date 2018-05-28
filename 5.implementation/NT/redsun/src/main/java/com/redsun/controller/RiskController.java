package com.redsun.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

@Controller
@EnableWebMvc
@RequestMapping(value = "risk")
public class RiskController extends BaseController{

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/{name}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRiskData(@PathVariable("name") String name, @PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, Model model, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> risk = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.RISK_URL_SELECT + clientId + "/" + name + "/" + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.get(getDataURL, risk);
	}

	@RequestMapping(value = "/listforoneproject/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getListRiskOneProject(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> risk = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.RISK_URL_ALL_ONE_PROJECT + clientId + "/" + projectId;
		return RestAPIHelper.get(getDataURL, risk);
	}

	// Edit form
	@RequestMapping("/edit/{id}")
	public String showJspEdit(@PathVariable("id") Integer id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("id", id);
		return "Risk_form";
	}

	// List screen.
	@RequestMapping("list")
	public String listRisk() {
		return "Risks";
	}

	// List screen.
	@RequestMapping("showform")
	public String insert() {
		return "Risk_form";
	}

	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@RequestBody Map<String, Object> risk, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		risk.put("clientId", clientId);
		risk.put("updateBy", userName);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RISK_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, risk);
		return result;
	}

	// Update.
	@RequestMapping(value = "update/{id}", headers = "Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> risk, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		risk.put("updateBy", userName);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RISK_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, risk, null);
		return result;
	}

	// Get by Id.
	@RequestMapping("/getbyid/{id}")
	@ResponseBody
	public Object getById(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RISK_URL_GETBYID + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;

	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.RISK_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

}
