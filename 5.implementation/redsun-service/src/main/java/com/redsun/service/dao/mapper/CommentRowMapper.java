package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Comments;

public class CommentRowMapper implements RowMapper<Comments> {

	@Autowired
	private Comments comment;

	@Override
	public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		comment = new Comments();
		comment.setId(rs.getInt("id"));
		comment.setTaskId(rs.getInt("id_task"));
		comment.setContent(rs.getString("content"));
		comment.setCommentBy(rs.getString("comment_by"));
		comment.setCreateDateTime(rs.getDate("create_date_time"));
		if (comment.getCreateDateTime() != null) {
			comment.setStringCreateDateTime(sdf.format(comment.getCreateDateTime()));
		}
		return comment;

	}
}
