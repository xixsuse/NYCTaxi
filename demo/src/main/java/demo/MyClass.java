package demo;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

public class MyClass implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		test testt = new test();
		testt.oninIt();
		try {
			testt.pingServer();
		} catch (DeploymentException | IOException | URISyntaxException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
