package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Comments;

public interface CommentDao {

	//
	List<Comments> listComments(int taskId);



}
