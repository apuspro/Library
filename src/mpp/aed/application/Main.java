package mpp.aed.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mpp.aed.view.controller.LoginController;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
			Parent root = (Parent)loader.load();
			LoginController controller = (LoginController)loader.getController();
			//Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Login Page");
			primaryStage.setScene(scene);
			controller.setPrimaryStage(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
