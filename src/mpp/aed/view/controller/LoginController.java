package mpp.aed.view.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mpp.aed.application.Main;
import mpp.aed.library.SystemController;

public class LoginController {
	
	private SystemController sController;
	private Stage primaryStage;

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Text resultMsg;
	
	@FXML
    protected void initialize(){
		sController =  SystemController.getInstance();
    }

	@FXML
	private void handleLoginBtn() {

		String username = usernameField.getText();
		String password = passwordField.getText();

		if (sController.login(username, password)) {
			//TODO Call the next Window
			resultMsg.setText("Login Successfully!");
			sController.setCurrentUser(sController.getUser(username));
			openMenu();
		} else {
			resultMsg.setText("Login Failed");
		}
	}
	
	private void openMenu(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/MenuView.fxml"));
		BorderPane page;
		try {
			page = (BorderPane) loader.load();
			Stage menuStage = new Stage();
			menuStage.setTitle("Library Menu");
			
			MenuController controller = loader.getController();
			
			Scene scene = new Scene(page);
			menuStage.setScene(scene);
			
			menuStage.setOnHiding(new EventHandler<WindowEvent>() {

	            @Override
	            public void handle(WindowEvent event) {
	                Platform.runLater(new Runnable() {

	                    @Override
	                    public void run() {
	                        System.out.println("Application Closed by click to Close Button(X)");
	                        System.exit(0);
	                    }
	                });
	            }
	        });
			
			controller.setPrimaryStage(this.primaryStage);
			controller.setMenuStage(menuStage);
			
			menuStage.show();
			this.primaryStage.hide();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param primaryStage the primaryStage to set
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
