package com.redsun.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.ClientService;
import com.redsun.utils.RedSunConstants;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * File Sharing Controller
 */
@Controller
@RequestMapping("file-sharing")
public class FileSharingController extends BaseController {
	@Autowired
	ClientService clientService;
	@Autowired
	ServletContext application;
	@Autowired
	UserUtil userUtil;

	@RequestMapping(value = "/upfile/{projectId}/{title}/{description}/{groupId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object upfile(@PathVariable("projectId") Integer projectId, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("groupId") Integer groupId, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
		String rootFolderName = "Social" + File.separator + "project" + File.separator + projectId + File.separator;
		try {
			File thedir = new File(RedSunConstants.rootDirectory + File.separator + rootFolderName);
			thedir.mkdirs();
			File f = new File(thedir + File.separator + file.getOriginalFilename());
			file.transferTo(f);
			
			Map<String, Object> mapData = new HashMap<>();
			mapData.put("projectId", projectId);
			mapData.put("clientId", userUtil.getClientIdOfLoginedUser());
			mapData.put("name", file.getOriginalFilename());
			mapData.put("filePath", "/download/"+projectId+"/"+file.getOriginalFilename()+"/");
			mapData.put("createdBy", userUtil.getLoginedUsername());
			mapData.put("title", title);
			mapData.put("description", description);
			mapData.put("groupId", groupId);
			
			String redsunServiceUrl = getMainDomain(request) + RedSunURLs.FILE_SHARING_CREATE;
			Object result = RestAPIHelper.post(redsunServiceUrl, mapData);
			return result;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/list-by-group/{projectId}/{groupId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAll(@PathVariable("projectId") Integer projectId,@PathVariable("groupId") Integer groupId, HttpServletRequest request) throws Exception {
		int client_Id = userUtil.getClientIdOfLoginedUser();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.FILE_SHARING_LIST_PROJECT + projectId + "/" + groupId + "/"
				+ client_Id + "/";
		return RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
	}

	// Delete.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.FILE_SHARING_DELETE + id;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}

	@RequestMapping(value = "/download/{projectId}/{fileName}/", method = RequestMethod.GET)
	public @ResponseBody void download(@PathVariable("projectId") final Integer projectId,
			@PathVariable("fileName") final String fileName, HttpServletResponse response) throws IOException {
		try {			
			String thedir = RedSunConstants.rootDirectory + "Social" + File.separator + "project" + File.separator + projectId + File.separator + fileName;
			final File file = new File(thedir);
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			response.setHeader("Content-Length", String.valueOf(file.length()));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		} catch (Exception e) {
		}

	}
}
