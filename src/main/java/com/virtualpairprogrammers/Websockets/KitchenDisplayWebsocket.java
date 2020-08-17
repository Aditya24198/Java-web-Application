package com.virtualpairprogrammers.Websockets;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.virtualpairprogrammers.data.MenuDao;
import com.virtualpairprogrammers.data.MenuDaoFactory;
import com.virtualpairprogrammers.domain.Order;

@ServerEndpoint("/kitchenManagement")
public class KitchenDisplayWebsocket {
 
	@OnOpen
	public void Open(Session session) {
		KitcheddisplaySyncSessionHandler handler = KitchenDisplaySessionHandlrFactory.getHandler();
		handler.addSession(session);
	}
	@OnClose
	public void Close(Session session) {
		KitcheddisplaySyncSessionHandler handler = KitchenDisplaySessionHandlrFactory.getHandler();
		handler.removeSession(session);
	}
	@OnError
	public void OnError(Throwable error) {
		throw new RuntimeException();
		
	}
	@OnMessage
	public void handleMessage(String message,Session session) {
		JSONObject json = new JSONObject(message);
		Long id = json.getLong("id");
		String status = json.getString("status");
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		menuDao.updateOrderStatus(id,status);
		Order order = menuDao.getOrder(id);
		KitcheddisplaySyncSessionHandler handler = KitchenDisplaySessionHandlrFactory.getHandler();
		handler.amendorder(order);
	}
	
}
