package com.redsun.doc.restcontroller;

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
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.doc.entities.Result;
import com.redsun.doc.utils.FileUtil;

/**
 * Upload files Controller
 */
@RestController
@RequestMapping("files-service")
public class FilesRestController {
	
	@Autowired
	private ServletContext servletContext;

    @Autowired
    private Environment env;

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Object upload(@RequestParam("location") String location, @RequestParam("files") MultipartFile[] files) throws IOException {
		// Prepare file path.
	    final String absolutePath = env.getProperty("rootDirectory") + location.replace('/', File.separatorChar);
	    List<String> fileNames = FileUtil.saveFilesToLocal(absolutePath, files);
	    
	    // Return result.
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("fileNames", fileNames);
	    return new Result(result, true);
	}

    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody 
    public Resource download(HttpServletRequest request, HttpServletResponse response, @RequestParam("location") String location, @RequestParam("fileName") String fileName) throws IOException {
	    // Prepare file path.
    	final String absolutePath = env.getProperty("rootDirectory") + location.replace('/', File.separatorChar) + fileName;
    	// Create header.
	    String mimeType = servletContext.getMimeType(absolutePath);
	    if(mimeType == null) {
	    	mimeType = "application/octet-stream";
	    }
        response.setContentType(mimeType);
	    response.addHeader("Content-Disposition", "attachmnet; filename=" + absolutePath);
	    // Return resource.
        return new FileSystemResource(absolutePath);
    }

    @RequestMapping(value = "download-fullname", method = RequestMethod.GET)
    @ResponseBody 
    public Resource downloadByFullName(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileFullName") String fileFullName) throws IOException {
	    // Prepare file path.
    	String absolutePath = env.getProperty("rootDirectory") + fileFullName;
    	// Create header.
	    String mimeType = servletContext.getMimeType(absolutePath);
	    if(mimeType == null) {
	    	mimeType = "application/octet-stream";
	    }
        response.setContentType(mimeType);
	    response.addHeader("Content-Disposition", "attachmnet; filename=" + absolutePath);
	    // Return resource.
        return new FileSystemResource(absolutePath);
    }

}
