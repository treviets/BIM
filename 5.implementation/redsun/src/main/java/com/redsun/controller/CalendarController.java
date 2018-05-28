package com.redsun.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.entities.Result;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@RequestMapping(value = "/calendars")
public class CalendarController extends BaseController{
	
	@Autowired
	ServletContext application;
	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;
	
	String rootDirectory;
	String linkAttachFile;
	int projectid;
		
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(Model model) {
		
		return "Calendar";
	}
	
	
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAllData(Model model, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		/*calendar.put("clientId", clientId);*/
		Map<String, Object> calendars = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.CALENDAR_URL_GET_ALL + "/" + clientId;
		Result result = RestAPIHelper.get(getDataURL, calendars);
		return result;
	}
	//upload file
	@RequestMapping(value = "/upfile/{title}",  method = { RequestMethod.POST })
	@ResponseBody
	public Object upfile(@PathVariable("title") String title,@RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {		
		
		 rootDirectory = application.getRealPath("\\static\\");
		try {
			File thedir = new File(rootDirectory+"\\events"+"\\"+title);
			thedir.mkdirs();
			linkAttachFile ="\\static\\events\\"+"\\"+title+"\\"+File.separator+file.getOriginalFilename();
			File f = new File(thedir+File.separator+file.getOriginalFilename());
			file.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return rootDirectory;
	}
	
	@RequestMapping(value = "/add",method ={ RequestMethod.POST })
	@ResponseBody
	public Object create(@RequestBody Map<String, Object> calendar, HttpServletRequest request) throws Exception {
		int clientId = userUtil.getClientIdOfLoginedUser();
		calendar.put("clientId", clientId);
		calendar.put("attachFile",linkAttachFile);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.CALENDAR_URL_ADD;
		Object result = RestAPIHelper.post(redsunServiceUrl, calendar);
		return result;
	}
	
	
	

}
