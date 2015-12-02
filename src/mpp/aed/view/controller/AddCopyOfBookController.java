package mpp.aed.view.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mpp.aed.library.*;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSetFactory;

public class AddCopyOfBookController {

	private TextField isbnField;
	
	private Label titleField;
	
	private Label numberOfCopies;
	
	private SystemController sysController;
	
	private Label messageField;
	
	private Book book;
	
	public AddCopyOfBookController() {
		this.sysController = SystemController.getInstance();
	}
	
	public void handleGetBookBtn() {
		try {
			RuleSetFactory.getRuleSet(this).applyRules(this);
			book = this.sysController.getLibrary().getBookByISBN(Integer.parseInt(this.isbnField.getText()));
			if(book != null){
				this.titleField.setText(book.getTitle());
				this.numberOfCopies.setText(""+book.getNumberOfCopies());
				this.messageField.setText("");;
			}
			else{
				this.titleField.setText("");
				this.numberOfCopies.setText("");
				this.messageField.setText("No exists a Book with ISBN " + this.isbnField);
			}
		} catch (RuleException e) {
			this.messageField.setText(e.getMessage());
		}
	}
	
	public TextField getIsbnField() {
		return isbnField;
	}

	public void handleAddCopyBtn(){
		if(book != null){
			try {
				RuleSetFactory.getRuleSet(this).applyRules(this);
				this.sysController.addCopyToBook(book);
				this.numberOfCopies.setText("" + book.getNumberOfCopies());
				this.isbnField.setText("" + book.getISBN());
				this.messageField.setText("Copy of Book added sucessfully!");
			} catch (RuleException e) {
				this.messageField.setText(e.getMessage());
			}
		}
		else{
			this.messageField.setText("Please first get a book by ISBN.");
		}
		
	}
}
