package com.redsun.websocket.broadcaster;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.redsun.entities.Chatting;
import com.redsun.websocket.client.repository.ClientRepository;

@Component
public class ChatBroadcaster implements Broadcaster<Chatting> {
	@Autowired
	private ClientRepository clientRepo;
	private Gson gson;
	
	@PostConstruct
	public void init() {
		this.gson = new Gson();
	}
	
	@Override
	public void broadcast(List<Chatting> chatting) {
		this.clientRepo.forEach(client -> {
			try {
				Chatting chat = chatting.get(chatting.size()-1);
				client.sendText(this.gson.toJson(chat));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
