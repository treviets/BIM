package com.redsun.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.entities.Chatting;
import com.redsun.service.ProjectService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;
import com.redsun.websocket.chat.repository.ChatRepository;

@Controller
@EnableWebMvc
@RequestMapping(value = "/discussing")
public class DiscussingController extends BaseController {
	
	@Autowired
	ProjectService projectService;

	@Autowired
	private ChatRepository chatRepository;
	
	
	@Autowired
	UserUtil userUtil;
	
	SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@RequestMapping(value = "/group/create/", method = { RequestMethod.POST })
	@ResponseBody
	public Object createGroup(@RequestBody Map<String, Object> group, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String createdBy = userUtil.getLoginedUsername();
		group.put("clientId",clientId);
		group.put("createdBy",createdBy);
		group.put("createdDate", formatDate.format(new Date()));
		
		String createGroupUrl = getMainDomain(request) + RedSunURLs.SOCIAL_GROUP_CREATE;
		Object mainMap = RestAPIHelper.post(createGroupUrl, group);
		return mainMap;
	}
	
	@RequestMapping(value = "/group/list/{projectId}", method = { RequestMethod.GET })
	@ResponseBody
	public Object groupList(@PathVariable("projectId") final Integer projectId, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String listGroupUrl = getMainDomain(request) + RedSunURLs.SOCIAL_GROUP_LIST+"/"+projectId+"/"+clientId;
		Object mainMap = RestAPIHelper.get(listGroupUrl, null);
		return mainMap;
	}
	
	@RequestMapping(value = "/group/join/{groupId}/", method = { RequestMethod.GET })
	@ResponseBody
	public Object joinGroup(@PathVariable("groupId") final Integer groupId, HttpServletRequest request) throws Exception{
		String username = userUtil.getLoginedUsername();
		String listGroupUrl = getMainDomain(request) + RedSunURLs.SOCIAL_GROUP_JOIN+"/"+groupId+"/"+username+"/";
		Object mainMap = RestAPIHelper.get(listGroupUrl, null);
		return mainMap;
	}
	
	@RequestMapping(value = "/group/list/by-username/", method = { RequestMethod.GET })
	@ResponseBody
	public Object groupListByUsername(HttpServletRequest request) throws Exception{
		String username = userUtil.getLoginedUsername();
		String listGroupUrl = getMainDomain(request) + RedSunURLs.SOCIAL_GROUP_LIST_BY_USERNAME+"/"+username+"/";
		Object mainMap = RestAPIHelper.get(listGroupUrl, null);
		return mainMap;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public void createDisccussing(@RequestBody Map<String, Object> chat, HttpServletRequest request) throws Exception{
		int clientId = userUtil.getClientIdOfLoginedUser();
		String createdBy = userUtil.getLoginedUsername();
		chat.put("clientId",clientId);
		chat.put("createdBy",createdBy);
		chat.put("avatar", userUtil.getLoginedAvatar());
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.SOCIAL_CHATTING_CREATE;
		Map mainMap = (Map)RestAPIHelper.post(redsunServiceUrl, chat);
		Map resultMap = (Map)mainMap.get("result");
		Map newChat = (Map)resultMap.get("newChat");
		Chatting resultObject = new Chatting();
		
		castMapToObject(newChat, resultObject);
		this.chatRepository.add(resultObject);
	}
	
	
	@SuppressWarnings("rawtypes")
	private void castMapToObject(Map original, Chatting chatting){
		chatting.setAvatar(String.valueOf(original.get("avatar")));
		chatting.setClientId(Integer.valueOf(String.valueOf(original.get("clientId"))));
		chatting.setMessage(String.valueOf(original.get("message")));
		chatting.setCreatedDate(String.valueOf(original.get("createdDate")));
		chatting.setCreatedBy(String.valueOf(original.get("createdBy")));
		chatting.setGroupId(String.valueOf(original.get("groupId")));
	}
	
	@RequestMapping(value = "/list-by-group/{groupId}/", method = { RequestMethod.GET })
	@ResponseBody
	public Object getDisccussing(@PathVariable("groupId") final String groupId, HttpServletRequest request) throws Exception{
		
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.SOCIAL_CHATTING_LIST_BY_GROUP + "/" + groupId+"/";
		Object result = RestAPIHelper.get(redsunServiceUrl, new HashMap<String, Object>());
		return result;
	}

	// Update.
	@RequestMapping(value = "update/{id}", headers = "Accept=application/json", method = RequestMethod.PUT)
	@ResponseBody
	public Object update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> project,
			HttpServletRequest request) throws Exception {
		String updateBy = userUtil.getLoginedUsername();
		project.put("updateBy", updateBy);
		String redsunServiceUrl = getMainDomain(request) + RedSunURLs.PROJECTS_URL_UPDATE + id;
		Object result = RestAPIHelper.put(redsunServiceUrl, project, null);
		return result;
	}

}
