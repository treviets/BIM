package com.redsun.websocket.handler;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.redsun.websocket.client.domain.Client;
import com.redsun.websocket.client.repository.ClientRepository;

@ServerEndpoint(value = "/project/websocket/register/", configurator = SpringConfigurator.class)
public class WebSocketHandlerImpl implements WebSocketHandler {
	@Autowired
	private ClientRepository clientRepository;

	@OnOpen
	public void onOpen(Session session) {
		clientRepository.add(new Client(session));
	}

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		this.clientRepository.remove(new Client(session));
	}
	
	@OnError
	public void OnError(Session session, Throwable t) {
	}
	

}