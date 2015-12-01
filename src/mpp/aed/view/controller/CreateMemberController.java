package mpp.aed.view.controller;

import javafx.fxml.FXML;
import mpp.aed.library.LibraryException;
import mpp.aed.library.SystemController;

public class CreateMemberController {

	@FXML
	private String memberIdField;
	
	@FXML
	private String firstNameField;
	
	@FXML
	private String lastNameField;
	
	@FXML
	private String streetField;
	
	@FXML
	private String cityField;
	
	@FXML
	private String stateField;
	
	@FXML
	private String zipField;
	
	@FXML
	private String phoneNumberField;
	
	@FXML
	private String messageField;
	
	private SystemController sysController;
	
	public CreateMemberController(SystemController sysController) {
		this.sysController = sysController;
	}
	
	@FXML
	public void handleCreateBtn() {
		try {
			this.messageField = "";
			if(this.validateFields())
				this.sysController.createMember(Integer.parseInt(memberIdField), firstNameField, lastNameField, streetField, cityField, stateField, zipField, Integer.parseInt(phoneNumberField));
		} catch (LibraryException e) {
			this.messageField = e.getMessage();
		}
	}
	
	private boolean validateFields()
	{
		if(this.memberIdField.isEmpty()){
			this.messageField = "Member Id cannot be empty";
			return false;
		}
		try {
			Integer.parseInt(memberIdField);
		} catch (NumberFormatException e) {
			this.messageField = "Member Id must be numeric";
			return false;
		}
		if(this.firstNameField.isEmpty()){
			this.messageField = "First Name cannot be empty";
			return false;
		}
		if(this.lastNameField.isEmpty()){
			this.messageField = "Last Name cannot be empty";
			return false;
		}
		if(!this.phoneNumberField.isEmpty()){
			try {
				Integer.parseInt(phoneNumberField);
			} catch (NumberFormatException e) {
				this.messageField = "Phone Number should be numeric";
				return false;
			}
		}
		return true;
	}
}
