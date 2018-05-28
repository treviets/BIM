package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.CommentDao;
import com.redsun.service.entities.Comments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	CommentDao commentDao;
	Result result = new Result();


	@Override
	public Result listComments(int taskId) {
		final List<Comments> data = commentDao.listComments(taskId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("comments", data);
		return new Result(result, true);
	}

}
