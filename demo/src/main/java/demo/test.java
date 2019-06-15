package demo;

import org.junit.Before;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;


public class test {
	private WebSocketContainer container;
	private HelloEndPoint endPoint;

	@Before
	public void oninIt() {
		System.err.println("HEEEEEEERRRRRRRREEEEEEEEEE");
		this.container = ContainerProvider.getWebSocketContainer();
		this.endPoint = new HelloEndPoint();
	}
	int counter = 0;
	@Test
	public void pingServer() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		
		
		Session session;
		
		
		 session = this.container.connectToServer(this.endPoint, new URI("ws://localhost:9000/ws"));

		try {
			

		this.endPoint.sentMessage(Integer.toString(++counter));
		
			System.err.println("****************************************************");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("4");
		}
		

		 }


	

}
