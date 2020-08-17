package com.virtualpairprogrammers.Websockets;

public class KitchenDisplaySessionHandlrFactory {

	private  static KitcheddisplaySyncSessionHandler session ;
	public static KitcheddisplaySyncSessionHandler getHandler() {
		if(session == null) {
			session = new KitcheddisplaySyncSessionHandler();
		}
		 return session;
	}
}
	