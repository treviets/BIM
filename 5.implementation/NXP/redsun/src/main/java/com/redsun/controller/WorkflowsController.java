package com.redsun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Workflow Controller
 */
@Controller
@RequestMapping("workflow")
public class WorkflowsController extends BaseController{
	
	@Autowired
	JavaMailSender mailSender;
	
	// Add screen.
	@RequestMapping(value = "/send", method= RequestMethod.GET)
	public String index (@RequestParam("toEmail") final String toEmail , Model model) throws MessagingException{
		mailSender.send(new MimeMessagePreparator() {
 
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom("redsun@bimexpert.vn");
                messageHelper.setTo(toEmail);
                messageHelper.setSubject("Kiểm tra thử ­ emai");
                messageHelper.setText("Kiểm tra thử - emai từ BIM Master");
            }
 
        });
		return "workflow";
	}
	
	// Add screen.
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addWorkflow (Model model){
		model.addAttribute("id", -1);
		return "Workflows_form";
	}
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addWorkflow(@RequestBody Map<String, Object> workflow, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.WORKFLOWS_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, workflow);
		return result;
	}
	
	// Edit screen.
	@RequestMapping("edit/{id}")
	public String editWorkflow(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);
		return "Workflows_form";
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateWorkflow(@PathVariable("id") Integer id, @RequestBody Map<String, Object> workflow, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.WORKFLOWS_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, workflow, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteWorkflow(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.WORKFLOWS_URL_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
		
	// List screen.
	@RequestMapping("list")
	public String listWorkflow(){
		return "Workflows_list";
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getWorkflowById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.WORKFLOWS_URL_GETBYID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listWorkflowsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, HttpServletRequest request) {
		Map<String, Object> workflow = new HashMap<String, Object>();
		
		String serviceUrl = getMainDomain(request) + RedSunURLs.WORKFLOWS_URL_FILTER + itemsPerPage + "/" + pageNo;
		return RestAPIHelper.post(serviceUrl, workflow);
	}

}
