package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
			controller.setPrimaryStage(this.primaryStage);
			
			Scene scene = new Scene(page);
			menuStage.setScene(scene);
			
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
