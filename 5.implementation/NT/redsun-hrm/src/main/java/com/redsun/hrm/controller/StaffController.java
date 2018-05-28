package com.redsun.hrm.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import com.redsun.hrm.service.StaffService;

/**
 * Staff Controller
 */
@RestController
@RequestMapping("staffservice")
public class StaffController {
	
	// Service.
	@Autowired
	StaffService staffService;
	@Autowired
	    private Environment env;
	@Autowired
	private ServletContext servletContext;
	 
	@RequestMapping(value = "list/staffs/{clientId}", method = { RequestMethod.GET })
	public Object getAllStaff(@PathVariable("clientId") int clientId) {
		return staffService.getAllStaffs(clientId);
	}

	@RequestMapping(value = "/download/{fileName}/{fileType}", method = RequestMethod.GET)
    public Resource download(@PathVariable final String fileName, @PathVariable final String fileType, final HttpServletResponse response) throws IOException {
		final String absolutePath = env.getProperty("rootDirectory")  + fileName + "." + fileType;
    	// Create header.
	    String mimeType = servletContext.getMimeType(absolutePath);
	    if(mimeType == null) {
	    	mimeType = "application/octet-stream";
	    }
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    response.addHeader("Content-Disposition", "attachmnet; filename=" + absolutePath);
	    // Return resource.
        return new FileSystemResource(absolutePath);
    }
	
	@RequestMapping(value = "/downloadimg/{fileName}/{fileType}", method = RequestMethod.GET)
    public @ResponseBody byte[] getImageAsByteArray(@PathVariable final String fileName, @PathVariable final String fileType) throws IOException {
		final String absolutePath = env.getProperty("rootDirectory")  + fileName + "." + fileType;
        InputStream in = servletContext.getResourceAsStream(absolutePath);
        return IOUtils.toByteArray(in);
    }
	
	@ResponseBody
	@RequestMapping(value = "/downloadimgs/{fileName}/{fileType}", method = RequestMethod.GET)
	public Resource getImageAsResource(@PathVariable final String fileName, @PathVariable final String fileType) {
		final String absolutePath = env.getProperty("rootDirectory")  + fileName + "." + fileType;
		return new ServletContextResource(servletContext, absolutePath);
	}
	@RequestMapping(value = "/download1/{fileName}/{fileType}", method = RequestMethod.GET)
	public void getImageAsByteArray(HttpServletResponse response, @PathVariable final String fileName, @PathVariable final String fileType) throws IOException {
		final String absolutePath = env.getProperty("rootDirectory")  + fileName + "." + fileType;
	    InputStream in = servletContext.getResourceAsStream(absolutePath);
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}
	@RequestMapping(value = "/download2/{fileName}/{fileType}", method = RequestMethod.GET)
	public String getImageAsResponseEntity(@PathVariable final String fileName, @PathVariable final String fileType) throws IOException {
	    final String absolutePath = env.getProperty("rootDirectory")  + fileName + "." + fileType;
	    File file = new File(absolutePath);
	    FileInputStream fis=new FileInputStream(file);
	    ByteArrayOutputStream bos=new ByteArrayOutputStream();
	    int b;
	    byte[] buffer = new byte[1024];
	    while((b=fis.read(buffer))!=-1){
	       bos.write(buffer,0,b);
	    }
	    byte[] fileBytes=bos.toByteArray();
	    fis.close();
	    bos.close();


	    byte[] encoded=Base64.getEncoder().encode(fileBytes);
	    String encodedString = new String(encoded);
	    return encodedString;
	}
	
	@RequestMapping(value = "/download3/{fileName}/{fileType}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> getImageAsResource2(@PathVariable final String fileName, @PathVariable final String fileType) {
	    HttpHeaders headers = new HttpHeaders();
	    final String absolutePath = env.getProperty("rootDirectory")  + fileName + "." + fileType;
	    Resource resource = 
	      new ServletContextResource(servletContext, "/WEB-INF/images/NT005.jpg");
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}
