package com.redsun.social.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.social.entities.Chatting;
import com.redsun.social.entities.Group;
import com.redsun.social.service.ChattingService;

@RestController
@RequestMapping("chatting")
public class ChattingController {

	@Autowired
	ChattingService chattingService;

	@RequestMapping(value = "/create", method = { RequestMethod.POST})
	@ResponseBody
	public Object create(@RequestBody Chatting chatting) throws IOException {

		Object result = chattingService.createChatForProject(chatting);

		return result;
	}
	
	@RequestMapping(value = "/group/create", method = { RequestMethod.POST})
	@ResponseBody
	public Object groupCreate(@RequestBody Group group) throws IOException {

		Object result = chattingService.createGroup(group);
		return result;
	}
	
	@RequestMapping(value = "/group/list/by-user/{username}/", method = { RequestMethod.GET})
	@ResponseBody
	public Object groupListByUsername(@PathVariable("username") String username) throws IOException {
		Object result = chattingService.getListOfGroups(username);
		return result;
	}
	
	@RequestMapping(value = "/group/list/{projectId}/{clientId}", method = { RequestMethod.GET})
	@ResponseBody
	public Object groupList(@PathVariable("projectId") Integer projectId, @PathVariable("clientId") Integer clientId) throws IOException {
		Object result = chattingService.getListOfGroups(projectId, clientId);
		return result;
	}

	@RequestMapping(value = "/list-for-group/{groupId}/", method = { RequestMethod.GET })
	@ResponseBody
	public Object getList(@PathVariable("groupId") String groupId) throws IOException {
		return chattingService.getChatByGroupId(groupId);
	}
	
	@RequestMapping(value = "/group/join/{groupId}/{username}/", method = { RequestMethod.GET })
	@ResponseBody
	public Object joinGroup( @PathVariable("groupId") Integer groupId, @PathVariable("username") String username) throws IOException {
		return chattingService.joinGroup(groupId, username);
	}

}
