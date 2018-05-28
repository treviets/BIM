package com.redsun.restcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.controller.BaseController;
import com.redsun.utils.FileUtil;
import com.redsun.utils.RedSunConstants;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Upload files Controller
 */
@RestController
@RequestMapping("files")
public class FilesRestController extends BaseController{
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "upload-forward", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public Object uploadForward(@RequestParam("actionType") String actionType, @RequestParam("id") Integer id, @RequestParam("files") MultipartFile file, HttpServletRequest request) throws Exception {
		File folder = new File(RedSunConstants.rootDirectory+"Projects"+File.separator+ actionType +File.separator);
		folder.mkdirs();
		File f = new File(folder + File.separator + file.getOriginalFilename());
		file.transferTo(f);
	    return 1;	
	}
	
	@RequestMapping(value = "download-farther", method = RequestMethod.GET)
	public @ResponseBody void downloadFarther(HttpServletRequest request, HttpServletResponse response, @RequestParam("actionType") String actionType, @RequestParam("id") Integer id, @RequestParam("fileName") String fileName) throws IOException {
		try {			
			String thedir = RedSunConstants.rootDirectory+"Projects"+File.separator+actionType+File.separator + fileName;
			final File file = new File(thedir);
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			response.setHeader("Content-Length", String.valueOf(file.length()));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		} catch (Exception e) {
		}
	}
	
	@RequestMapping(value = "download-farther-fullname", method = RequestMethod.GET)
	public Object downloadFartherByFullName(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileFullName") String fileFullName) throws IOException {
		String serviceUrl = getMainDomain(request) + RedSunURLs.FILE_URL_SERVICE_DOWNLOAD_FULLNAME + "?fileFullName=" + fileFullName;
		return RestAPIHelper.generalMethod(serviceUrl, HttpMethod.GET, null, new HashMap<String, Object>());
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public Object upload(@RequestParam("files") MultipartFile[] files) throws IOException {
		// Prepare file path.
	    String filePath = RedSunConstants.rootDirectory + "resources" + File.separatorChar;
	    List<String> fileNames = FileUtil.saveFilesToLocal(filePath, files);
	    
	    // Return result.
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("fileNames", fileNames);
	    return fileNames;
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileFullName") String fileFullName) throws IOException {
	    String absolutePath = RedSunConstants.rootDirectory + fileFullName;
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
