package com.redsun.websocket.chat.repository;

import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redsun.websocket.broadcaster.ChatBroadcaster;

@Component
public class ChatRepositoryObserver implements Observer {
	@Autowired
	private ChatBroadcaster broadcaster;
	
	@Override
	public void update(Observable repository, Object param) {
		ChatRepository repo = (ChatRepository) repository;
		this.broadcaster.broadcast(repo.getAll());
	}

}
