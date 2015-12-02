package mpp.aed.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mpp.aed.library.LibraryException;
import mpp.aed.library.SystemController;

public class CreateMemberController {

	@FXML
	private TextField memberIdField;
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField lastNameField;
	
	@FXML
	private TextField streetField;
	
	@FXML
	private TextField cityField;
	
	@FXML
	private TextField stateField;
	
	@FXML
	private TextField zipField;
	
	@FXML
	private TextField phoneNumberField;
	
	@FXML
	private Label messageField;
	
	private SystemController sysController;
	
	public CreateMemberController() {
		this.sysController = SystemController.getInstance();
	}
	
	@FXML
	public void handleCreateBtn() {
		try {
			this.messageField.setText("");
			if(this.validateFields())
				this.sysController.createMember(Integer.parseInt(memberIdField.getText()), firstNameField.getText(), lastNameField.getText(), streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText(), Integer.parseInt(phoneNumberField.getText()));
		} catch (LibraryException e) {
			this.messageField.setText(e.getMessage());
		}
	}
	
	private boolean validateFields()
	{
		if(this.memberIdField.getText().isEmpty()){
			this.messageField.setText("Member Id cannot be empty");
			return false;
		}
		try {
			Integer.parseInt(memberIdField.getText());
		} catch (NumberFormatException e) {
			this.messageField.setText("Member Id must be numeric");
			return false;
		}
		if(this.firstNameField.getText().isEmpty()){
			this.messageField.setText("First Name cannot be empty");
			return false;
		}
		if(this.lastNameField.getText().isEmpty()){
			this.messageField.setText("Last Name cannot be empty");
			return false;
		}
		if(!this.phoneNumberField.getText().isEmpty()){
			try {
				Integer.parseInt(phoneNumberField.getText());
			} catch (NumberFormatException e) {
				this.messageField.setText("Phone Number should be numeric");
				return false;
			}
		}
		return true;
	}
}
