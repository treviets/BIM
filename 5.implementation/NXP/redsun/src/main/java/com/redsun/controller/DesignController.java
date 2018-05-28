package com.redsun.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.entities.Result;
import com.redsun.utils.RedSunConstants;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.redsun.utils.UserUtil;

@Controller
public class DesignController extends BaseController{
	
	@Autowired
	ServletContext application;

	@Autowired
	JavaMailSender mailSender;

	// Project Management screen
	@RequestMapping("/design")
	public String dashboard() {
		return "design_home";
	}
	
	// Edit form
	@RequestMapping("/design/detail/{id}")
	public String showJspEdit(@PathVariable("id") Integer id, @RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("id", id);
		return "project-detail";
	}

	// Get Standard Diagram
	@RequestMapping(value = "/design/diagram/standard", method = { RequestMethod.GET })
	@ResponseBody
	public Object getStandardDiagram(HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_STANDARD_DIAGRAM;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	
	// Get Custom Diagram
	@RequestMapping(value = "/design/diagram/custom", method = { RequestMethod.GET })
	@ResponseBody
	public Object getCustomDiagram(HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_CUSTOM_DIAGRAM;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	
	// Get Standard Process
	@RequestMapping(value = "/design/process/standard", method = { RequestMethod.GET })
	@ResponseBody
	public Object getStandardProcess(HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_STANDARD_PROCESS;
	
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	
	// Get Custom Process
	@RequestMapping(value = "/design/process/custom", method = { RequestMethod.GET })
	@ResponseBody
	public Object getCustomProcess(HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_CUSTOM_PROCESS;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	// BPMN Management screen
	@RequestMapping("/bpmn")
	public String bpmnDashboard() {
		return "bpmn_home";
	}
	
	// BPMN Management for specific project
	@RequestMapping("/bpmn/{projectId}")
	public String bpmnDetail(@PathVariable("projectId") Integer projectId) {
		return "design";
	}
	
	// Design Process screen
	@RequestMapping(value = "/design/{projectId}")
	public String designDetail(@PathVariable("projectId") Integer projectId) {
		return "design";
	}
	
	// Get Resource for project
	@RequestMapping(value = "/design/resource/{clientId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getProjectResource(@PathVariable("clientId") Integer clientId, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_PROJECT_RESOURCE + clientId;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	
	// Get Selected Resource for step
	@RequestMapping(value = "/design/selected/resource/{projectId}/{stepId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getSelectedResource(@PathVariable("projectId") Integer projectId, @PathVariable("stepId") String stepId, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_SELECTED_RESOURCE + projectId + "/" + stepId;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);

		return result;
	}
	
	// Add Resource for step
	@RequestMapping(value = "/design/resource", method = { RequestMethod.POST })
	@ResponseBody
	public Object createResource(@RequestBody Map<String, Object> resourceStep, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.CREATE_SELECTED_RESOURCE;
		Object result = RestAPIHelper.post(url, resourceStep);

		return result;
	}
	
	// Remove Resource for step
	@RequestMapping(value = "/design/resource/delete", method = { RequestMethod.POST})
	@ResponseBody
	public Object deleteResource(@RequestBody Map<String, Object> resourceStep, HttpServletRequest request) {
		
		String url = getMainDomain(request) + RedSunURLs.DELETE_SELECTED_RESOURCE;
		Object result = RestAPIHelper.post(url, resourceStep);
		
		return result;
	}
	
	// Get Document for step
	@RequestMapping(value = "/design/document/{projectId}/{stepId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getDocument(@PathVariable("projectId") Integer projectId, @PathVariable("stepId") String stepId, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_PROJECT_DOCUMENT + projectId + "/" + stepId;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	
	// Insert Document of step into database
	@RequestMapping(value = "/design/document", method = { RequestMethod.POST })
	@ResponseBody
	public Object createDocument(@RequestBody Map<String, Object> documentStep, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.CREATE_PROJECT_DOCUMENT;
		Object result = RestAPIHelper.post(url, documentStep);

		return result;
	}
	
	// Delete Document of step into database
	@RequestMapping(value = "/design/document/delete", method = { RequestMethod.POST })
	@ResponseBody
	public Object deleteDocument(@RequestBody Map<String, Object> documentStep, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.DELETE_PROJECT_DOCUMENT;
		Object result = RestAPIHelper.post(url, documentStep);

		return result;
	}
	
	// Create phase diagram form redsun-bpmn 
	// Get email list and resource list and send email to resource
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/design/diagram", method = { RequestMethod.POST })
	@ResponseBody
	public Object createDiagram(@RequestBody Map<String, Object> phaseDiagram, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.CREATE_PHASE_DIAGRAM;
		
		Map<String, Object> result = (Map<String, Object>)RestAPIHelper.post(url, phaseDiagram);
		Map<String, Object> detailResult = (Map<String, Object>)result.get("result");
		String title =  "BPMM: Assigned Resource";
		
		Set set = detailResult.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext()) {
			 Map.Entry item = (Map.Entry)iterator.next();
			 String content =  (String) item.getKey();
			 List<String> listOfEmail = (List<String>) item.getValue();
			 if (!listOfEmail.isEmpty()) {
				 sendEmail(listOfEmail, title, content);
			 }
		}
		
		return result;
	}

	// Get phase diagram from redsun-bpmn 
	@RequestMapping(value = "/design/diagram/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getDiagram(@PathVariable("projectId") Integer projectId, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_PHASE_DIAGRAM + projectId;
		
		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}
	
	// Update phase diagram from redsun-bpmn 
	@RequestMapping(value = "/design/diagram/update/{projectId}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateDiagram(@PathVariable("projectId") Integer projectId, @RequestBody Map<String, Object> phaseDiagram, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.UPDATE_PHASE_DIAGRAM + projectId;

		return RestAPIHelper.put(url, phaseDiagram, null);
	}

	// Get all phases from redsun-bpmn service
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllPhases(HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_ALL_PHASE;

		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}

	// Get all phases from redsun-bpmn service
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllPhasesById(@PathVariable("id") Integer id, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.GET_PHASE_BY_ID + id;

		Map<String, Object> phase = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, phase);
		
		return result;
	}

	// Update phase from redsun-bpmn service
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update/{id}/{currentStepId}/{actionType}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updatePhase(@PathVariable("id") Integer id, @PathVariable("currentStepId") String currentStepId,
							  @PathVariable("actionType") String actionType, @RequestBody Map<String, Object> phase, HttpServletRequest request) {
		String url = getMainDomain(request) + RedSunURLs.UPDATE_PHASE + id + "/" + currentStepId + "/" + actionType;

		ResponseEntity responseEntity = (ResponseEntity)RestAPIHelper.put(url, phase, null);
		Map<String, Object> result = (Map<String, Object>)responseEntity.getBody();
		Map<String, Object> detailResult = (Map<String, Object>)result.get("result");
		List<String> emailOfCurrentSteps = (List<String>)detailResult.get("currentEmails");
		List<String> emailOfContinueSteps = (List<String>)detailResult.get("continueEmails");
		this.sendEmailForPeople(actionType, emailOfCurrentSteps, emailOfContinueSteps);
		return responseEntity;
	}
	
	private void sendEmailForPeople(String actionType, List<String> lstEmailOfCurrentStep, List<String> lstEmailOfContinueStep) {
		String titleForCurrent = "[BPMN]: Document submitted";
		String contentForCurrent = "Your request has been submitted for next step! <br/><br/> Best regards, <br/> BPMN Manager";
		
		String titleForContinue = "Document need your approval";
		String contentForContinue = "You have the document(s) to review on BPMN! <br/><br/> Best regards, <br/> BPMN Manager";
		
		if (actionType.equals(RedSunConstants.BPMN_ACTION_TYPE_REJECT)) {
			titleForContinue = "Your document has been rejected";
			contentForContinue = "Your document has been rejected, please help to review your document! <br/><br/> Best regards, <br/> BPMN Manager";
		}
		
		if(!lstEmailOfCurrentStep.isEmpty()){
			// Send email to Current step
			sendEmail(lstEmailOfCurrentStep, titleForCurrent, contentForCurrent);
		}
		
		if(!lstEmailOfContinueStep.isEmpty()){
			// Send email to Continue step
			sendEmail(lstEmailOfContinueStep, titleForContinue, contentForContinue);
		}
	}
	
	private void sendEmail(List<String> toEmail, String title, String content) {
		mailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom(RedSunConstants.FROM_EMAIL);
                messageHelper.setTo(toEmail.toArray(new String[0]));
                messageHelper.setSubject(title);
                messageHelper.setText(content,true);
            }
 
        });
	}

	// Upload file each phase
	@RequestMapping(value = "/design/upfile/{projectId}/{phase}/{subPhase}", method = { RequestMethod.POST })
	@ResponseBody
	public Object upfile(@PathVariable("projectId") Integer projectId, @PathVariable("phase") Integer phase,
			@PathVariable("subPhase") Integer subPhase, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		String rootDirectory = RedSunConstants.rootDirectory;

		try {
			File thedir = new File(rootDirectory + File.separator + "DesignProcess" + File.separator + projectId
					+ File.separator + phase + File.separator + subPhase);
			thedir.mkdirs();
			File f = new File(thedir + File.separator + file.getOriginalFilename());
			file.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		Result result = new Result();
		result.setStatus(1);
		result.setDescription("Success");
		return result;
	}
	
	@RequestMapping(value = "/design/download/{projectId}/{activeStep}/{fileName}/", method = RequestMethod.GET)
    public @ResponseBody void download(@PathVariable("projectId") Integer projectId, @PathVariable("activeStep") String activeStep, @PathVariable final String fileName, HttpServletResponse response) throws IOException {
		try {
			String[] activeStepArray = activeStep.split("_");
			String phase = "";
			String subPhase = "";
			if(activeStepArray.length > 0){
				phase = activeStepArray[0];
				subPhase = activeStepArray[1];
			}
			
			
			String thedir = RedSunConstants.rootDirectory + "DesignProcess" + File.separator + projectId + File.separator + phase + File.separator + subPhase + File.separator+fileName;
			final File file =  new File(thedir);
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        
			FileCopyUtils.copy(inputStream, response.getOutputStream());

		} catch (Exception e) {
		}
	
    }
	

	// Get all uploaded document of project
	@RequestMapping(value = "/design/allfile/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllFileForProject(@PathVariable("projectId") Integer projectId, HttpServletRequest request)
			throws Exception {
		String url = getMainDomain(request) + RedSunURLs.GET_ALL_DOCUMENT + projectId;

		Map<String, Object> document = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, document);

		return result;
	}

	// Get all uploaded document of project
	@RequestMapping(value = "/design/allfile/{projectId}/{phase}/{subPhase}", method = { RequestMethod.POST })
	@ResponseBody
	public Object getAllFile(@PathVariable("projectId") Integer projectId, @PathVariable("phase") Integer phase,
			@PathVariable("subPhase") Integer subPhase, HttpServletRequest request) throws Exception {
		String url = getMainDomain(request) + RedSunURLs.GET_DOCUMENT_FOR_STEP + projectId + "/" + phase + "/" + subPhase;

		Map<String, Object> document = new HashMap<String, Object>();
		Object result = RestAPIHelper.get(url, document);

		return result;
	}

	// Add uploaded document to database
	@RequestMapping(value = "/design/addfile", method = { RequestMethod.POST })
	@ResponseBody
	public Object createFile(@RequestBody Map<String, Object> phaseDocument, HttpServletRequest request) throws Exception {
		UserUtil user = new UserUtil();
		String uploadUser = user.getLoginedUsername();

		phaseDocument.put("uploadBy", uploadUser) ;
		
		String url = getMainDomain(request) + RedSunURLs.ADD_PHASE_DOCUMENT;
		Object result = RestAPIHelper.post(url, phaseDocument);

		return result;
	}
	
	// Add uploaded document to database
	@RequestMapping(value = "/design/deletefile", method = { RequestMethod.POST })
	@ResponseBody
	public Object deleteFile(@RequestBody Map<String, Object> phaseDocument, HttpServletRequest request) throws Exception {
		String url = getMainDomain(request) + RedSunURLs.DELETE_PHASE_DOCUMENT;
		Object result = RestAPIHelper.post(url, phaseDocument);

		return result;
	}

}
