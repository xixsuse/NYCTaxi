package demo;

import java.io.IOException;
import java.util.ArrayList;


import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;

import javax.websocket.OnMessage;
import javax.websocket.Session;


public class HelloEndPoint extends Endpoint {
	private Session session;
	ArrayList<TripInf> tt = new ArrayList<TripInf>();

	@Override
	public void onOpen(Session session, EndpointConfig arg1) {
		// TODO Auto-generated method stub
		this.session = session;

	}

	@OnMessage
	public void sentMessage(String message) throws IOException, InterruptedException {
	
		this.session.getBasicRemote().sendText(message);
	

		this.session.addMessageHandler(new MessageHandler.Whole<String>() {

			public void onMessage(String messageSent) {

				if (!messageSent.equals("")) {

					TripInf trip = new TripInf();
					trip.addTrip(trip, messageSent);

				}

				// TODO Auto-generated method stub
				
				System.out.println("The message is " + messageSent);

			}

		});

	}
}
