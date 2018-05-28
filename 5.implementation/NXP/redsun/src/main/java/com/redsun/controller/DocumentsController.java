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

import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Document Controller
 */
@Controller
@RequestMapping("document")
public class DocumentsController extends BaseController{
	@Autowired
	ClientService clientService;

	// Insert.
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object addDocument(@RequestBody Map<String, Object> document, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		document.put("clientId", clientId);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_ROOT_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, document);
		return result;
	}

	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editDocument(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "Documents_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateDocument(@PathVariable("id") Integer id, @RequestBody Map<String, Object> document, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_ROOT_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, document, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteDocument(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_ROOT_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	// List screen.
	@RequestMapping("list")
	public String listDocument() {
		return "Documents_list";
	}

	// List by code.
	@RequestMapping(value = "getbydocumentid", method = { RequestMethod.GET })
	@ResponseBody
	public Object getDocumentById(@RequestParam("documentId") final Integer documentId, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_ROOT_GET_BY_ID + documentId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listDocumentsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> document = new HashMap<String, Object>();
		String serviceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_ROOT_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, document);
	}

	@RequestMapping(value = "/list/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllData(@PathVariable("projectId") int projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> documents = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DOCUMENT_LIST + clientId + "/" + projectId;
		return RestAPIHelper.get(getDataURL, documents);
	}

	@RequestMapping(value = "/getbytaskid/{taskId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getByTaskId(@PathVariable("taskId") Integer taskId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		Map<String, Object> documents = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DOCUMENT_BY_TASKID + taskId + "/" + clientId;
		return RestAPIHelper.get(getDataURL, documents);
	}
}
