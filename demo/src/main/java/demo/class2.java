package demo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class class2 extends Application implements Runnable{
	private Stage primaryStage;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch(null);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage=primaryStage;
		showPrimaryPage();
	}
	public void showPrimaryPage() throws IOException {
		Parent mainViewRoot =  FXMLLoader.load(getClass().getResource("main_dash_board.fxml"));
		Scene scene = new Scene(mainViewRoot);
		primaryStage.setScene(scene);
		primaryStage.setTitle("NYC Taxi");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
}
