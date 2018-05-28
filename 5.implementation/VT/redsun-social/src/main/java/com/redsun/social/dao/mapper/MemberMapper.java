package com.redsun.social.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.social.entities.Member;


@Component
public class MemberMapper implements RowMapper<Member> {
	
	@Autowired
	private Member member;

	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

		member = new Member();
		member.setUsername(rs.getString("username"));
		member.setAvatar(rs.getString("avatar"));
	
		return member;
	}
}
