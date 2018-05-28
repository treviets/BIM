package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.CommentDao;
import com.redsun.service.dao.mapper.CommentRowMapper;
import com.redsun.service.entities.Comments;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(CommentDaoImpl.class);

	@Override
	public List<Comments> listComments(int taskId) {
		List<Comments> listComment = null;
		String SELECT_COMMENT = "SELECT id, id_task, content, comment_by, create_date_time "
		+ " FROM comments WHERE id_task = ? ORDER BY create_date_time desc" ;
		List<Object> params = new ArrayList<Object>();
		params.add(taskId);


		try {
			listComment = jdbcTemplate.query(SELECT_COMMENT, params.toArray(), new CommentRowMapper());
			return listComment;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listComment;
	}

}
