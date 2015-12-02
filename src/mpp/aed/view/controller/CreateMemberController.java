package mpp.aed.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mpp.aed.library.LibraryException;
import mpp.aed.library.SystemController;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSetFactory;

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
			RuleSetFactory.getRuleSet(this).applyRules(this);
			this.sysController.createMember(Integer.parseInt(memberIdField.getText()), firstNameField.getText(), lastNameField.getText(), streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText(), Long.parseLong(phoneNumberField.getText()));
			clearFields();
		} catch (LibraryException e) {
			this.messageField.setText(e.getMessage());
		} catch (RuleException e) {
			this.messageField.setText(e.getMessage());
		}
	}

	private void clearFields() {
		this.messageField.setText("Member created succesfully");
		this.memberIdField.setText("");
		this.firstNameField.setText("");
		this.lastNameField.setText("");
		this.streetField.setText("");
		this.stateField.setText("");
		this.cityField.setText("");
		this.zipField.setText("");
		this.phoneNumberField.setText("");
	}

	public TextField getMemberIdField() {
		return memberIdField;
	}

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public TextField getLastNameField() {
		return lastNameField;
	}

	public TextField getStreetField() {
		return streetField;
	}

	public TextField getCityField() {
		return cityField;
	}

	public TextField getStateField() {
		return stateField;
	}

	public TextField getZipField() {
		return zipField;
	}

	public TextField getPhoneNumberField() {
		return phoneNumberField;
	}

	public Label getMessageField() {
		return messageField;
	}
}
