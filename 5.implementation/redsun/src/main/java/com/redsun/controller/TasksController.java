package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.entities.Result;
import com.redsun.service.ClientService;
import com.redsun.utils.FileUtil;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@RequestMapping(value = "/task")
public class TasksController extends BaseController{

	@Autowired
	ServletContext application;
	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;
	@Autowired
	JavaMailSender mailSender;

	// send email
	@RequestMapping(value = "/send/{toEmail}", method = RequestMethod.GET)
	public String index(@PathVariable("toEmail") String toEmail) throws MessagingException {
		System.out.println("Begin send email");
		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				String[] email = toEmail.split(",");
				messageHelper.setFrom("redsun@bimexpert.vn");
				messageHelper.setTo(email);
				messageHelper.setSubject("Your task has just updated");
				messageHelper.setText("Contact with your Leader/Project Manager");
			}

		});
		return "project";
	}
	// send email when add task
		@RequestMapping(value = "/sendAdd/{toEmail}", method = RequestMethod.GET)
		public String senEdit(@PathVariable("toEmail") String toEmail) throws MessagingException {
			System.out.println("Begin send email");
			mailSender.send(new MimeMessagePreparator() {

				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					String[] email = toEmail.split(",");
					messageHelper.setFrom("redsun@bimexpert.vn");
					messageHelper.setTo(email);
					messageHelper.setSubject("You have just assigned task");
					messageHelper.setText("Contact with your Leader/Project Manager");
				}

			});
			return "project";
		}
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String index(Model model) {

		return "Tasks";
	}

	// list tasks of one the project
	@RequestMapping(value = "/listforoneproject/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllDataOneProject(@PathVariable("projectId") int projectId, Model model, HttpServletRequest request) throws Exception {
		String userName = userUtil.getLoginedUsername();
		int clientId = userUtil.getClientIdOfLoginedUser();
		Map<String, Object> tasks = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.TASK_URL_GET_ALL_FOR_ONE_PROJECT + "/" + clientId + "/" + projectId + "/"
				+ userName +"/";
		Result result = RestAPIHelper.get(getDataURL, tasks);
		return result;
	}

	// Get by Id.
	@RequestMapping("/getbyid/{id}")
	@ResponseBody
	public Object getById(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_URL_GETBYID + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;

	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_URL_DELETE + "/" + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	// Update.
	@RequestMapping(value = "/update/{id}", headers = "Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> task, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, task, null);
		return result;
	}

	@RequestMapping(value = "/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> task, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		String owner = userUtil.getLoginedUsername();
		task.put("clientId", clientId);
		task.put("taskOwner", owner);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.TASK_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, task);
		return result;
	}

	@RequestMapping(value = "import-task", method = RequestMethod.POST)
	@ResponseBody
	public Object importTask(@RequestParam("filePath") final String filePath,
			@RequestParam("projectId") final Integer projectId, @RequestParam("file") final MultipartFile file, HttpServletRequest request)
			throws Exception {
		Object result = null;
		String userName = userUtil.getLoginedUsername();
		String serviceUrl = getMainDomain(request) + RedSunURLs.TASK_URL_IMPORT + "?filePath=" + filePath + "&projectId=" + projectId + "&userName=" + userName;
		Integer clientId = userUtil.getClientIdOfLoginedUser();
		if (clientId != null && clientId > 0) {
			serviceUrl += "&clientId=" + clientId;
		}

		// Post file to service.
		LinkedMultiValueMap<String, Object> map = FileUtil.saveFileToLocalReturnLinkedMultiValueMap(file);
		// Transfer files as resources to service.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
		result = RestAPIHelper.post(serviceUrl, requestEntity);

		return result;
	}
}
