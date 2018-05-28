package com.redsun.social.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.social.dao.ChattingDao;
import com.redsun.social.dao.mapper.ChattingMapper;
import com.redsun.social.dao.mapper.GroupMapper;
import com.redsun.social.dao.mapper.MemberMapper;
import com.redsun.social.entities.Chatting;
import com.redsun.social.entities.Group;
import com.redsun.social.entities.Member;

@Repository
public class ChattingDaoImpl implements ChattingDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ChattingMapper chattingMapper;
	
	@Autowired
	GroupMapper groupMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	private String createChat = "Insert into discussing (client_id, project_id,group_id, created_by, message, avatar, scopes) values (?,?,?,?,?,?,?)";
	private String selectChat = "select id, client_id, project_id, created_by, created_date, message, avatar, scopes from discussing where group_id = ? order by created_date";
	
	
	private String createChatGroup = "Insert into discussing_groups (client_id, project_id, created_by, name, description) values (?,?,?,?,?)";
	private String selectGroup = "select id, client_id, project_id, created_by, created_date, name, description from discussing_groups where project_id =? and client_id=? order by created_date";
	private String selectGroupByUsername = "select dg.id, dg.client_id, dg.project_id, dg.created_by, dg.created_date, dg.name, dg.description "
			+ "from discussing_groups dg LEFT JOIN discussing_groups_members dgm on dg.id = dgm.group_id where dgm.username=? order by dg.created_date";
	private String selectMemberOfGroup = "select username, avatar from discussing_groups_members where group_id = ? order by created_date";
	
	private String joinChatGroup = "Insert into discussing_groups_members (group_id, username, created_by, status, approved_by) values (?,?,?,?,?)";
	
	
	private final static int[] SQL_INSERT_TYPES = new int[] { 
			java.sql.Types.INTEGER, // client_id
			java.sql.Types.INTEGER, // project_id
			java.sql.Types.VARCHAR, // create_by
			java.sql.Types.VARCHAR, // name
			java.sql.Types.VARCHAR // Description

	};
	private final static String AUTO_INCREMENTED_COLUMN = "id";
	
	private PreparedStatementCreator getPreparedStatementCreator(final Group group) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(createChatGroup, SQL_INSERT_TYPES);

		factory.setGeneratedKeysColumnNames(new String[] { AUTO_INCREMENTED_COLUMN });
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(group));
		return psc;
	}
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Group group) {
		return new Object[] { 
			group.getClientId(),
			group.getProjectId(),
			group.getCreatedBy(),
			group.getName(),
			group.getDescription()
		};
	}
	
	@Override
	public void createChat(Chatting chatting) {
		
		Object[] params = new Object[] { chatting.getClientId(), chatting.getProjectId(), chatting.getGroupId(), chatting.getCreatedBy(), chatting.getMessage(), chatting.getAvatar(), chatting.getScopes() };
		int result = jdbcTemplate.update(createChat, params);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after Create Chatting : " + result + " (0 or 1 expected) ");
		}
	}
	 
	@Override
	public void createChats(List<Chatting> chattings) {
		for(Chatting chatting:chattings){
			createChat(chatting);
		}
		
	}

	@Override
	public List<Chatting> getListOfChat(String groupId) {
		List<Chatting> chattings = null;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		chattings = jdbcTemplate.query(selectChat, params.toArray(), chattingMapper);
		if(chattings == null){
			return new ArrayList<>();
		}
		return chattings;
	}

	@Override
	public int createGroup(Group group) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(getPreparedStatementCreator(group), keyHolder);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after Create Chatting : " + result + " (0 or 1 expected) ");
		}
		return keyHolder.getKey().intValue();
		
	}
	
	@Override
	public List<Group> getListOfGroups(int projectId, int clientId) {
		List<Group> groups = null;
		groups = jdbcTemplate.query(selectGroup, new Object[]{projectId, clientId}, groupMapper);
		if(groups == null){
			return new ArrayList<>();
		}
		return groups;
	}
	
	@Override
	public List<Group> getListOfGroups(String username) {
		List<Group> groups = null;
		groups = jdbcTemplate.query(selectGroupByUsername, new Object[]{username}, groupMapper);
		if(groups == null){
			return new ArrayList<>();
		}
		return groups;
	}
	@Override
	public int joinGroup(int groupId, String username) {
		int result = jdbcTemplate.update(joinChatGroup, new Object[]{groupId, username, username, 1, "Auto"});
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after Create Chatting : " + result + " (0 or 1 expected) ");
		}
		return result;
	}
	@Override
	public List<Member> getMemberOfGroup(int groupId) {
		
		List<Member> members = null;
		members = jdbcTemplate.query(selectMemberOfGroup, new Object[]{groupId}, memberMapper);
		if(members == null){
			return new ArrayList<>();
		}
		return members;
	}
	
	
}
