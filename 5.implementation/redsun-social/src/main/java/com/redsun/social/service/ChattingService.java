package com.redsun.social.service;

import com.redsun.social.entities.Chatting;
import com.redsun.social.entities.Group;

public interface ChattingService {
	public Object getChatByGroupId(String groupId);
	public Object createChatForProject(Chatting chatting);
	public Object createGroup(Group group);
	public Object getListOfGroups(int projectId, int clientId);
	public Object getListOfGroups(String username);
	public int joinGroup(int groupId, String username);
}
