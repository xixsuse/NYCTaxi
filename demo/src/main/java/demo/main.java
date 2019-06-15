package demo;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main  {
	
//	private Stage primaryStage;
	




	public static void main(String[] args) throws DeploymentException, IOException, URISyntaxException, InterruptedException  {
		// TODO Auto-generated method stub
		

		
		Thread t1 = new Thread(new MyClass());
		t1.start();
		Thread t2 = new Thread(new class2());
		t2.start();

//		
//		test tt = new test();
//		tt.oninIt();
//		tt.pingServer();
//				launch(args);

	}
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		this.primaryStage=primaryStage;
//		showPrimaryPage();
//	}
//	public void showPrimaryPage() throws IOException {
//		Parent mainViewRoot =  FXMLLoader.load(getClass().getResource("main_dash_board.fxml"));
//		Scene scene = new Scene(mainViewRoot);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("NYC Taxi");
//		primaryStage.setResizable(false);
//		primaryStage.show();
//		
//	}

}
