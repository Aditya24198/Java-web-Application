package com.virtualpairprogrammers.Websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import org.json.JSONObject;

import com.virtualpairprogrammers.data.MenuDao;
import com.virtualpairprogrammers.data.MenuDaoFactory;
import com.virtualpairprogrammers.domain.Order;

public class KitcheddisplaySyncSessionHandler {
	
		private List<Session> sessions = new ArrayList<Session>();
		public void addSession(Session session) {
			sessions.add(session);
			sendAllorders(session);
		}
		public void removeSession(Session session) {
			sessions.remove(session);
		}
		public void sendMessage(JSONObject message) {
			for(Session session:sessions) {
				try {
					session.getBasicRemote().sendText(message.toString());
				} catch (IOException e) {
					removeSession(session);
					e.printStackTrace();
				}
				
			}
		}
		public void sendMessage(JSONObject message,Session session) {
			try {
				session.getBasicRemote().sendText(message.toString());
			} catch (IOException e) {
				removeSession(session);
				e.printStackTrace();
			}		
		}

		public JSONObject generateJSON(Order order) {
			JSONObject json = new JSONObject();
			json.append("id", order.getId().toString());
			json.append("status", order.getStatus());
			json.append("content", order.toString());
			json.append("action", "add");
			json.append("update", new Date());
			return json;
		}
		public void newOrder(Order order) {
			
			sendMessage(generateJSON(order));
		}
		public void amendorder(Order order) {
			// TODO Auto-generated method stub
			JSONObject json = new JSONObject();
			json.append("id", order.getId().toString());
			json.append("status", order.getStatus());
			json.append("content", order.toString());
			json.append("action", "remove");
			json.append("update", new Date());
			sendMessage(json);
			if(!order.getStatus().equals("ready for collection")) {
			newOrder(order);
			}
		}
		public void sendAllorders(Session session) {
			MenuDao menuDao = MenuDaoFactory.getMenuDao();
			List<Order> orders = menuDao.getAllOrders();
			for(Order order:orders) {
				if(!order.getStatus().equals("ready for collection")) {
					sendMessage(generateJSON(order),session);
			 }
			}
		}
}
