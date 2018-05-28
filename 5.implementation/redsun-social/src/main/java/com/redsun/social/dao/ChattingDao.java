package com.redsun.social.dao;

import java.util.List;

import com.redsun.social.entities.Chatting;
import com.redsun.social.entities.Group;
import com.redsun.social.entities.Member;

public interface ChattingDao {
	public void createChat(Chatting chatting);
	public void createChats(List<Chatting> chattings);
	public int createGroup(Group group);
	public List<Chatting> getListOfChat(String groupId);
	public List<Member> getMemberOfGroup(int groupId);
	public List<Group> getListOfGroups(int projectId, int clientId);
	public List<Group> getListOfGroups(String username);
	public int joinGroup(int groupId, String username);
}
