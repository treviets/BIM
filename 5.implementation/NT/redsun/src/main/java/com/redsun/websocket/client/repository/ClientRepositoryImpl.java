package com.redsun.websocket.client.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.redsun.websocket.client.domain.Client;

@Repository
@Scope("singleton")
public class ClientRepositoryImpl extends ClientRepository {
	private static List<Client> listClient = new ArrayList<Client>();

	public void add(Client session) {
		
		synchronized (listClient) {
			String username = session.getSession().getUserPrincipal().getName();
			boolean isNewClient = true;
			for (Client client : listClient) {
				String u = client.getSession().getUserPrincipal().getName();
				if(username.equals(u)){
					isNewClient = false;
					break;
				}
				
			}
			if(isNewClient){
				listClient.add(session);
			}
			

		}

	}

	public void remove(Client session) {
		synchronized (listClient) {
			listClient.remove(session);
		}

	}

	@Override
	public void forEach(Consumer<Client> clientConsume) {
		if (listClient != null && listClient.size() > 0) {
			synchronized (listClient) {
				listClient.forEach(clientConsume);
			}
		}

	}

	@Override
	public void cleanAll() {
		listClient = new ArrayList<>();
	}

	@Override
	public List<Client> getAll() {
		return listClient;
	}

}
