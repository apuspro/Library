package mpp.aed.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mpp.aed.library.SystemController;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSet;
import mpp.aed.view.rulsets.RuleSetFactory;

public class UserController {

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Text resultMsg;
	
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
	public void addUser(){
		
		RuleSet userRules = RuleSetFactory.getRuleSet(this);
		try{
			userRules.applyRules(this);
			
			SystemController.getInstance().addUser(this.usernameField.getText(), this.passwordField.getText(), this.comboBox.getValue());
			SystemController.getInstance().serialize(SystemController.getInstance().getLibrary());
			
			resultMsg.setFill(Color.GREEN);
			resultMsg.setText("User "+usernameField.getText()+" created");
			System.out.println("User "+usernameField.getText()+" created");
			cleanFields();
		}catch(RuleException e) {
			resultMsg.setFill(Color.RED);
			resultMsg.setText(e.getMessage());
		}
	}
	
	@FXML
	public void cancelBtn(){
		this.userStage.hide();
	}
	
	@FXML
	public void printUsers(){
		SystemController.getInstance().printUsers();
	}

	public void setUserStage(Stage userStage) {
		this.userStage = userStage;
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public ComboBox<String> getComboBox() {
		return comboBox;
	}
	
	private void cleanFields(){
		this.usernameField.setText("");
		this.passwordField.setText("");
		this.comboBox.getSelectionModel().clearSelection();
	}
	
}
