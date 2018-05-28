package com.redsun.websocket.chat.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.redsun.entities.Chatting;
import com.redsun.websocket.repository.ChatAppRepository;


@Repository
public abstract class ChatRepository extends ChatAppRepository<String, Chatting> {
	@Autowired
	private BeanFactory factory;
	
	@PostConstruct
	public void init() {
		this.addObserver(this.factory.getBean(ChatRepositoryObserver.class));
	}
}
