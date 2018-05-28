package com.redsun.social.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.redsun.social.dao.ChattingDao;
import com.redsun.social.entities.Chatting;
import com.redsun.social.entities.Group;
import com.redsun.social.entities.Result;
import com.redsun.social.service.ChattingService;

@Service
@Scope("singleton")
public class ChattingServiceImpl implements ChattingService{

	private static Map<Object, List<Chatting>> listChattingCache = new HashMap<Object, List<Chatting>>();
	
	private static Map<Object, List<Chatting>> listChattingDidNotStored = new HashMap<Object, List<Chatting>>();
	
	@Autowired
	ChattingDao chattingDao;
	
	@Scheduled(fixedDelay = 5000)
	private void saveChat() {
		/*
		 * Implement code here for save chat from cache to db, this snippet will execute every 5 seconds
		 * */
		List<Chatting> didNotStoredChattingList = listChattingDidNotStored.get("didNotStoredChat");
		if(didNotStoredChattingList != null){
			listChattingDidNotStored.remove("didNotStoredChat");
			chattingDao.createChats(didNotStoredChattingList);
		}
		
		
	}
	

	@Override
	public Object getChatByGroupId(String groupId) {
		Result result = new Result();
		Map<String, Object> data = new HashMap<>();
		List<Chatting> chattings = listChattingCache.get(groupId);
		if(chattings == null){
			chattings = chattingDao.getListOfChat(groupId);
			listChattingCache.put(groupId, chattings);
		}
		data.put("data", chattings);
		result.setResult(data);
		return result;
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Object createChatForProject(Chatting chatting) {
		chatting.setCreatedDate(dateFormat.format(new Date()));
		List<Chatting> currentChattingList = listChattingCache.get(chatting.getGroupId());
		List<Chatting> didNotStoredChattingList = listChattingDidNotStored.get("didNotStoredChat");
		
		if(currentChattingList == null){
			currentChattingList = new ArrayList<>();
		}
		
		if(didNotStoredChattingList == null){
			didNotStoredChattingList = new ArrayList<>();
		}
		
		currentChattingList.add(chatting);
		listChattingCache.put(chatting.getGroupId(), currentChattingList);
		
		didNotStoredChattingList.add(chatting);
		listChattingDidNotStored.put("didNotStoredChat", didNotStoredChattingList);
		
		
		Result result = new Result();
		Map<String, Object> data = new HashMap<>();
		data.put("newChat", chatting);
		result.setResult(data);
		return result;
	}


	@Override
	public Object createGroup(Group group) {
		int newGroupId = chattingDao.createGroup(group);
		Result result = new Result();
		Map<String, Object> data = new HashMap<>();
		group.setId(newGroupId);
		data.put("newGroup", group);
		result.setResult(data);
		return result;
	}


	@Override
	public Object getListOfGroups(int projectId, int clientId) {
		List<Group> groups = new ArrayList<>();
		Result result = new Result();
		Map<String, Object> data = new HashMap<>();
		groups = chattingDao.getListOfGroups(projectId, clientId);
		for(Group group:groups){
			group.setMembers(chattingDao.getMemberOfGroup(group.getId()));
		}
		data.put("data", groups);
		result.setResult(data);
		return result;
	}
	
	@Override
	public Object getListOfGroups(String username) {
		List<Group> groups = new ArrayList<>();
		Result result = new Result();
		Map<String, Object> data = new HashMap<>();
		groups = chattingDao.getListOfGroups(username);
		data.put("data", groups);
		result.setResult(data);
		return result;
	}


	@Override
	public int joinGroup(int groupId, String username) {
		int result = chattingDao.joinGroup(groupId, username);
		return result;
	}


}
