package mpp.aed.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mpp.aed.library.LibraryException;
import mpp.aed.library.Member;
import mpp.aed.library.SystemController;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSetFactory;

public class CreateMemberController  implements Initializable {

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
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	private Member memberToEdit;
	
	public CreateMemberController() {
		this.sysController = SystemController.getInstance();
	}

	private void setUpCreationViewMode() {
		this.btnSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent e){
				handleCreateBtn();
			}
		});
		this.btnCancel.setDisable(true);
		this.memberIdField.setDisable(false);
		this.clearFields();
		this.memberToEdit = null;
	}
	
	private void setUpModificationViewMode() {
		this.btnSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent e){
				handleModificationBtn();
			}
		});
		this.btnCancel.setDisable(false);
		this.memberIdField.setDisable(true);
	}
	
	
	public void handleCreateBtn() {
		try {
			this.messageField.setText("");
			RuleSetFactory.getRuleSet(this).applyRules(this);
			this.sysController.createMember(Integer.parseInt(memberIdField.getText()), firstNameField.getText(), lastNameField.getText(), streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText(), Long.parseLong(phoneNumberField.getText()));
			clearFields();
			this.messageField.setText("Member created successfully");
		} catch (LibraryException e) {
			this.messageField.setText(e.getMessage());
		} catch (RuleException e) {
			this.messageField.setText(e.getMessage());
		}
	}
	
	public void handleModificationBtn() {
		try {
			RuleSetFactory.getRuleSet(this).applyRules(this);
			this.updateMember();
			this.setUpCreationViewMode();
			this.messageField.setText("Member updated successfully");
		} catch (RuleException e) {
			this.messageField.setText(e.getMessage());
		}
	}

	private void updateMember() {
		this.memberToEdit.setCity(this.getCityField().getText());
		this.memberToEdit.setFirstName(this.firstNameField.getText());
		this.memberToEdit.setLastName(this.lastNameField.getText());
		this.memberToEdit.setPhoneNumber(Long.parseLong(this.phoneNumberField.getText()));
		this.memberToEdit.setState(this.stateField.getText());
		this.memberToEdit.setStreet(this.streetField.getText());
		this.memberToEdit.setZip(this.zipField.getText());
		this.sysController.serialize(this.sysController.getLibrary());
	}
	
	@FXML
	public void handleSearchBtn(){
		int memberId;
		if(this.getMemberIdField().getText().isEmpty()){
			this.messageField.setText("Member Id cannot be empty");
			return;
		}
		try {
			memberId = Integer.parseInt(this.getMemberIdField().getText());
		} catch (NumberFormatException e) {
			this.messageField.setText("Member Id must be numeric");
			return;
		}
		this.memberToEdit = this.sysController.getLibrary().getMemberById(memberId);
		if(this.memberToEdit!= null){
			printMemberToEdit();
			this.setUpModificationViewMode();
		}
		else{
			this.messageField.setText("It doesn´t exist a member with Member Id" + this.memberIdField.getText() ); 
		}
	}

	private void printMemberToEdit() {
		this.memberIdField.setText("" + this.memberToEdit.getMemberId());
		this.firstNameField.setText(this.memberToEdit.getFirstName());
		this.lastNameField.setText(this.memberToEdit.getLastName());
		this.streetField.setText(this.memberToEdit.getStreet());
		this.stateField.setText(this.memberToEdit.getState());
		this.cityField.setText(this.memberToEdit.getCity());
		this.zipField.setText(this.memberToEdit.getZip());
		this.phoneNumberField.setText("" + this.memberToEdit.getPhoneNumber());
	}
	
	@FXML
	private void handleCancelBtn(){
		this.setUpCreationViewMode();
	}
	
	private void clearFields() {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpCreationViewMode();
	}
}
