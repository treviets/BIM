package com.redsun.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.controller.BaseController;
import com.redsun.utils.FileUtil;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Upload files Controller
 */
@RestController
@RequestMapping("document-rest")
public class DocumentRestController extends BaseController{
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "upload-forward", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public Object uploadForward(@RequestParam("location") String location, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		Object result = null;
		
		String serviceUrl = getMainDomain(request) + RedSunURLs.FILE_URL_SERVICE_UPLOAD + "?location=" + location;
		//int clientId = userUtil.getClientIdOfLoginedUser();
		//serviceUrl += clientId;
		
	    LinkedMultiValueMap<String, Object> map = FileUtil.saveFilesToLocalReturnLinkedMultiValueMap(files);
        // Transfer files as resources to service.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        result = RestAPIHelper.post(serviceUrl, requestEntity);	
        
	    return result;	
	}
	
	@RequestMapping(value = "download-farther", method = RequestMethod.GET)
	public Object downloadFarther(HttpServletRequest request, HttpServletResponse response, @RequestParam("location") String location, @RequestParam("fileName") String fileName) throws IOException {
		final String serviceUrl = getMainDomain(request) + RedSunURLs.FILE_URL_SERVICE_DOWNLOAD_FARTHER +"?location=" + location + "&fileName=" + fileName;
		return RestAPIHelper.generalMethod(serviceUrl, HttpMethod.GET, null, new HashMap<String, Object>());
	}
	
	@RequestMapping(value = "download-farther-fullname", method = RequestMethod.GET)
	public Object downloadFartherByFullName(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileFullName") String fileFullName) throws IOException {
		String serviceUrl = getMainDomain(request) + RedSunURLs.FILE_URL_SERVICE_DOWNLOAD_FULLNAME +"?fileFullName=" + fileFullName;
		return RestAPIHelper.generalMethod(serviceUrl, HttpMethod.GET, null, new HashMap<String, Object>());
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public Object upload(@RequestParam("files") MultipartFile[] files) throws IOException {
		// Prepare file path.
	    String filePath = servletContext.getRealPath("/") + "resources" + File.separatorChar;
	    List<String> fileNames = FileUtil.saveFilesToLocal(filePath, files);
	    
	    // Return result.
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("fileNames", fileNames);
	    return fileNames;
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileFullName") String fileFullName) throws IOException {
	    String absolutePath = servletContext.getRealPath("/") + fileFullName;
	    // Set mime type and header.
	    String mimeType = servletContext.getMimeType(absolutePath + fileFullName);
	    if(mimeType == null) {
	    	mimeType = "application/octet-stream";
	    }
	    response.setContentType(mimeType);
	    response.addHeader("Content-Disposition", "attachmnet; filename=" + absolutePath);
	    // Copy file to out put stream.
	    Path path = Paths.get(absolutePath);
	    Files.copy(path, response.getOutputStream());
	}

}
