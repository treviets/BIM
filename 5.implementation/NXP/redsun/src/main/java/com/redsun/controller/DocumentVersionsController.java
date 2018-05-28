package com.redsun.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.entities.Result;
import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunConstants;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Document Controller
 */
@Controller
@RequestMapping("documentversion")
public class DocumentVersionsController extends BaseController {
	@Autowired
	ClientService clientService;
	@Autowired
	ServletContext application;
	
	String rootDirectory;
	String attach;
	int projectid;

	// List by documentId.
	@RequestMapping(value = "getbydocumentid/{documentId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listDocumentVersion(@PathVariable("documentId") final Integer documentId, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_SERVICE_ROOT_GET_BY_DOC_ID + documentId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	// List by taskId.
	@RequestMapping(value = "getbytaskid/{taskId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getByTaskId(@PathVariable("taskId") final Integer taskId, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_SERVICE_ROOT_GET_BY_TASK_ID + taskId;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	// List on page
	@RequestMapping(value = "listall/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object listOnPage(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		projectid = projectId;
		int clientId = user.getClientId();
		Map<String, Object> documentVersions = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_SERVICE_ROOT_LIST + clientId + "/" + projectId;
		return RestAPIHelper.get(getDataURL, documentVersions);
	}

	// get by id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getById(@PathVariable("id") final Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_SERVICE_ROOT_GET_BY_ID + id;
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	@RequestMapping(value = "/add", headers = "Accept=application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> documentVersion, HttpServletRequest request) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int clientId = user.getClientId();
		documentVersion.put("clientId", clientId);
		documentVersion.put("link", attach);
		documentVersion.put("authorName", userName);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, documentVersion);
		return result;
	}

	// idTask or idDocument
	@RequestMapping(value = "/upfile/{docId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object upfile(@PathVariable("docId") Integer docId, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		//rootDirectory = application.getRealPath(File.separator + "static" + File.separator);
		String rootFolderName = "document" + File.separator + "project" + projectid + File.separator + docId+ File.separator;
		rootFolderName = rootFolderName.toLowerCase();
		try {
			File thedir = new File(RedSunConstants.rootDirectory + File.separator + rootFolderName);
			thedir.mkdirs();
			//attach = File.separator + "static" + File.separator + rootFolderName + file.getOriginalFilename();
			attach = File.separator + rootFolderName + file.getOriginalFilename();
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
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/download/{id}/{fileName}/", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable final Integer id, @PathVariable final String fileName, HttpServletRequest request) throws IOException {
        final ResponseEntity responseEntity = new RestTemplate().exchange(getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_DOWNLOAD + id, HttpMethod.GET, null, Resource.class);

        final Resource resource = (Resource)responseEntity.getBody();
        final InputStream file = resource.getInputStream();
        final Long contentLength = resource.contentLength();

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("content-disposition", "inline;filename=\"" + fileName + "\"");

        return ResponseEntity.ok().headers(headers).contentLength(contentLength).contentType(MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(file));

    }
	
	// Delete.
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteDocument(@RequestBody Map<String, Object> document, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DOCUMENT_VERSION_URL_DELETE;
		Object result = RestAPIHelper.post(redsunServiceUrl, document);
		return result;
	}

}
