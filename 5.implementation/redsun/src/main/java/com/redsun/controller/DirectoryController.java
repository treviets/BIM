package com.redsun.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;


@Controller
@EnableWebMvc
@RequestMapping(value = "directory")
public class DirectoryController extends BaseController{ 
	
	@Autowired
	UserUtil userUtil;
	
	// Insert.
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public Object addDirectory(@RequestBody Map<String, Object> directory, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		directory.put("clientId", clientId);
		String userName = userUtil.getLoginedUsername();
		directory.put("createUserName", userName);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_INSERT;
		Object result = RestAPIHelper.post(redsunServiceUrl, directory);
		return result;
	}

	@RequestMapping(value = "insertdef", method = RequestMethod.POST)
	@ResponseBody
	public Object addDirectoryDef(@RequestBody Map<String, Object> directory, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		directory.put("clientId", clientId);
		String userName = userUtil.getLoginedUsername();
		directory.put("createUserName", userName);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_INSERT_DEF;
		Object result = RestAPIHelper.post(redsunServiceUrl, directory);
		return result;
	}
	
	// Insert.
		@RequestMapping(value = "createlistdirectory/{id}/{projectName}", method = RequestMethod.GET)
		@ResponseBody
		public Object createListDirectory(@PathVariable("id") Integer id, @PathVariable("projectName") String projectName, HttpServletRequest request) throws Exception{
			int clientId = userUtil.getClientIdOfLoginedUser();
			String userName = userUtil.getLoginedUsername();
			Map<String, Object> resultDoc = new HashMap<String, Object>();
			String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_CREATE_LIST + id + "/" + projectName + "/" + userName + "/" + clientId;
			Object result = RestAPIHelper.get(redsunServiceUrl, resultDoc);
			return result;
		}
		
	// Update.
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateDirectory(@PathVariable("id") Integer id, @RequestBody Map<String, Object> directory, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		directory.put("clientId", clientId);
		String userName = userUtil.getLoginedUsername();
		directory.put("createUserName", userName);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, directory, null);
		return result;
	}
	
	@RequestMapping(value = "updatedef/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateDirectoryDef(@PathVariable("id") Integer id, @RequestBody Map<String, Object> directory, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		directory.put("clientId", clientId);
		String userName = userUtil.getLoginedUsername();
		directory.put("createUserName", userName);

		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_UPDATE_DEF + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, directory, null);
		return result;
	}
	
	// Update.
	@RequestMapping(value = "updatetrash/{id}/{location}", method = RequestMethod.PUT)
	@ResponseBody
	public Object moveFolderToTrash(@PathVariable("id") Integer id, @PathVariable("location") String location, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_UPDATE_TRASH + id + "/" + location;
		Object result = RestAPIHelper.put(redsunServiceUrl, null, null);
		return result;
	}

	@RequestMapping(value = "undofromtrash/{ids}", method = RequestMethod.PUT)
	@ResponseBody
	public Object undoFolderFromTrash(@PathVariable("ids") String ids, HttpServletRequest request) throws Exception {
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_UNDO_TRASH + ids;
		Object result = RestAPIHelper.put(redsunServiceUrl, null, null);
		return result;
	}

	// Delete.
	@RequestMapping(value = "delete/{id}/{location}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteDirectory(@PathVariable("id") Integer id, @PathVariable("location") String location, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_DELETE + id + "/" + location;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
	
	@RequestMapping(value = "deletedef/{id}/{location}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteDirectoryDef(@PathVariable("id") Integer id, @PathVariable("location") String location, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_DELETE_DEF + id + "/" + location;
		Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
		return result;
	}
	
	// List by Id.
	@RequestMapping("getbyid/{id}")
	@ResponseBody
	public Object getDirectoryById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_GET_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	@RequestMapping("getdefbyid/{id}")
	@ResponseBody
	public Object getDirectoryDefById(@PathVariable("id") Integer id, HttpServletRequest request){
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_GET_DEF_BY_ID + id;
		Object result =  RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "doc_directory_display";
	}
	
    @RequestMapping(value="/list", method = {RequestMethod.GET})
    @ResponseBody
    public Object getDirectory(Model model, HttpServletRequest request) throws Exception {
    	int clientId = userUtil.getClientIdOfLoginedUser();
    	String userName = userUtil.getLoginedUsername();
    	Map<String, Object> listDirectories = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DIRECTORY_GET_ALL + clientId + "/" + userName + "/";
        return RestAPIHelper.get(getDataURL, listDirectories);
    }
    
    @RequestMapping(value="/listdefault", method = {RequestMethod.GET})
    @ResponseBody
    public Object getDefaultDirectory(Model model, HttpServletRequest request) throws Exception {
    	Map<String, Object> lisDefaultDirectories = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DIRECTORY_GET_DEFAULT;
        return RestAPIHelper.get(getDataURL, lisDefaultDirectories);
    }
    
    @RequestMapping(value="/list/{userName}", method = {RequestMethod.GET})
    @ResponseBody
    public Object getDirectoryByUser(@PathVariable("userName") String userName, Model model, HttpServletRequest request) throws Exception {
    	int clientId = userUtil.getClientIdOfLoginedUser();
    	Map<String, Object> listDirectories = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DIRECTORY_GET_ALL + clientId + "/" + userName + "/";
        return RestAPIHelper.get(getDataURL, listDirectories);
    }
    
    @RequestMapping(value="/list/resourceassigned", method = {RequestMethod.GET})
    @ResponseBody
    public Object getResourceAssigned(Model model, HttpServletRequest request) throws Exception {
    	int clientId = userUtil.getClientIdOfLoginedUser();
    	Map<String, Object> listDirectories = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DIRECTORY_GET_ALL + "resourceassigned/" + clientId;
        return RestAPIHelper.get(getDataURL, listDirectories);
    }
    
    @RequestMapping(value="/list/fromtrash", method = {RequestMethod.GET})
    @ResponseBody
    public Object getDirectoryFromTrash(Model model, HttpServletRequest request) throws Exception {
    	int clientId = userUtil.getClientIdOfLoginedUser();
    	String userName = userUtil.getLoginedUsername();
    	Map<String, Object> listDirectories = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DIRECTORY_URL_SERVICE_LIST_FROM_TRASH + clientId + "/" + userName + "/";
        return RestAPIHelper.get(getDataURL, listDirectories);
    }
    
    @RequestMapping(value="/doc/{directoryId}", method = {RequestMethod.GET})
    @ResponseBody
    public Object getDocument(@PathVariable("directoryId") Integer directoryId, HttpServletRequest request) throws Exception {
    	Map<String, Object> docs = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.DOCUMENT_GET_BY_FOLDER + directoryId;
        return RestAPIHelper.get(getDataURL, docs);
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/download/{id}/{fileName}/", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable final Integer id, @PathVariable final String fileName, HttpServletRequest request) throws IOException {
        final ResponseEntity responseEntity = new RestTemplate().exchange(getMainDomain(request) + RedSunURLs.DOWNLOAD_DOCUMENT + id, HttpMethod.GET, null, Resource.class);

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

}
