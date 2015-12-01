package mpp.aed.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import mpp.aed.library.SystemController;

public class LoginController {
	
	private SystemController sController;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Text resultMsg;
	
   @FXML
    protected void initialize(){
		sController =  new SystemController();
    }

	@FXML
	private void handleLoginBtn() {

		String username = usernameField.getText();
		String password = passwordField.getText();

		if (sController.login(username, password)) {
			//TODO Call the next Window
			resultMsg.setText("Login Successfully!");
		} else {
			resultMsg.setText("Login Failed");
		}

	}

}
