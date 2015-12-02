package mpp.aed.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mpp.aed.library.Administrator;
import mpp.aed.library.Librarian;
import mpp.aed.library.SuperUser;
import mpp.aed.library.SystemController;
import mpp.aed.library.User;

public class UserController {

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private ComboBox<String> comboBox;
	
	private Stage userStage;
	
	private ObservableList<String> uOptions;
	
	@FXML
	protected void initialize(){
		uOptions = 
			    FXCollections.observableArrayList(
			        "Administrator",
			        "Librarian",
			        "Super User"
			    );
		
		comboBox.getItems().addAll(uOptions);
    }
	
	@FXML
	public boolean addUser(){
		User newUser;
		if(comboBox.getValue().equals("Administrator")){
			newUser = new Administrator(usernameField.getText(), passwordField.getText());
		}else if(comboBox.getValue().equals("Librarian")){
			newUser = new Librarian(usernameField.getText(), passwordField.getText());
		}else{
			newUser = new SuperUser(usernameField.getText(), passwordField.getText());
		}
		
		return SystemController.getInstance().getLibrary().addUser(newUser);
	}
	
	@FXML
	public void cancelBtn(){
		this.userStage.hide();
	}

	public void setUserStage(Stage userStage) {
		this.userStage = userStage;
	}
	
	
	
	
}
