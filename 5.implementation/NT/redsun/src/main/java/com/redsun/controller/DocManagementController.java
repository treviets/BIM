package com.redsun.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redsun.utils.FileUtil;
import com.redsun.utils.RedSunConstants;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;


@Controller
@EnableWebMvc
@RequestMapping(value = "document-management")
public class DocManagementController extends BaseController{ 
	
	@Autowired
	UserUtil userUtil;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	private ObjectMapper objectMapper;
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addDocument(@RequestBody Map<String, Object> document, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		document.put("clientId", clientId);
		String userName = userUtil.getLoginedUsername();
		document.put("userName", userName);
		
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, document);
		return result;
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateDocument(@PathVariable("id") Integer id, @RequestBody Map<String, Object> document, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		document.put("clientId", clientId);
		String userName = userUtil.getLoginedUsername();
		document.put("userName", userName);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, document, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteDocument(@RequestBody Map<String, Object> document, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_DELETE;
		Object result = RestAPIHelper.post(redsunServiceUrl, document);
		return result;
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getDocumentById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	@RequestMapping(value = "/send/document", method= RequestMethod.GET)
	public String index (@RequestParam("toEmail") final String toEmail 
						, @RequestParam("subject") final String subject 
						, @RequestParam("content") final String content, Model model) throws MessagingException{
		mailSender.send(new MimeMessagePreparator() {
 
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	String [] email = toEmail.split(",");
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom(RedSunConstants.FROM_EMAIL);
                messageHelper.setTo(email);
                //messageHelper.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail ));
           //   messageHelper.setCc("creator email");
                messageHelper.setSubject(subject);
                messageHelper.setText(content);
            }
 
        });
		return "workflow";
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("location") String location, @RequestParam("file") final MultipartFile file, HttpServletRequest request) throws IOException {
        final Map<String, Object> modelAttachment = objectMapper.readValue(file.toString(), HashMap.class);
        final String fileName = file.getOriginalFilename();
        final File tempFile = File.createTempFile(fileName + "%&%&%", null);
        file.transferTo(tempFile);
        final LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new FileSystemResource(tempFile));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        final HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        String serviceUrl = getMainDomain(request) + RedSunURLs.FILE_URL_SERVICE_UPLOAD + "?location=" + location;
        RestAPIHelper.post(serviceUrl, requestEntity);
        tempFile.delete();
        return RestAPIHelper.post(serviceUrl, modelAttachment);
    }
}
