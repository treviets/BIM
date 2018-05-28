package com.redsun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;


@Controller
@EnableWebMvc
@RequestMapping(value = "directoryresource")
public class DirectoryResourceController extends BaseController{ 
	
	@Autowired
	UserUtil userUtil;
	// Insert.
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object assignResource(@RequestBody Map<String, Object> directoryResources, HttpServletRequest request) throws Exception{
		Object folderResources;
		folderResources = directoryResources.get("list");
		int clientId = userUtil.getClientIdOfLoginedUser();
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.DIRECTORY_RESOURCE + "?clientId=" + clientId;
		Object result = RestAPIHelper.post(redsunServiceUrl, folderResources);
		return result;
	}
	
	 @RequestMapping(value="/list/resourceassignedbyid/{directoryId}", method = {RequestMethod.GET})
	    @ResponseBody
	    public Object getResourceAssigned(@PathVariable("directoryId") int directoryId, Model model, HttpServletRequest request) throws Exception {
	    	int clientId = userUtil.getClientIdOfLoginedUser();
	    	Map<String, Object> listResourceByDirectoryId = new HashMap<String, Object>();
			String getDataURL = getMainDomain(request) + RedSunURLs.DIRECTORY_RESOURCE_URL_SERVICE_LIST_BY_RESOURCE_ID + clientId + "/" + directoryId;
	        return RestAPIHelper.get(getDataURL, listResourceByDirectoryId);
	    }
}
