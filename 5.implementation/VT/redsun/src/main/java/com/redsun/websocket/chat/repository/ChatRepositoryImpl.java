package com.redsun.websocket.chat.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.redsun.entities.Chatting;

@Repository
@Scope("singleton")
public class ChatRepositoryImpl extends ChatRepository {
	@Autowired
	private ChatRepositoryObserver observer;

	private static List<Chatting> listChatting = new ArrayList<>();

	@PostConstruct
	public void init() {
		this.addObserver(observer);
	}

	@Override
	public void add(Chatting chatting) {
		synchronized (listChatting) {
			if (!listChatting.contains(chatting)) {
				listChatting.add(chatting);
				this.publish();
			}
			
		}
	}

	@Override
	public void remove(Chatting chatting) {
		synchronized (listChatting) {
			listChatting.remove(chatting);
			this.publish();
		}

	}

	@Override
	public void forEach(Consumer<Chatting> typeConsumer) {
		synchronized (listChatting) {
			listChatting.forEach(typeConsumer);
		}
	}

	@Override
	public void cleanAll() {
		listChatting = new ArrayList<>();
	}

	@Override
	public List<Chatting> getAll() {
		return listChatting;
	}

}
