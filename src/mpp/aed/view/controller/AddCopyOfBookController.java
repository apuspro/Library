package mpp.aed.view.controller;

import mpp.aed.library.*;

public class AddCopyOfBookController {

	private String isbnField;
	
	private String titleField;
	
	private String numberOfCopies;
	
	private SystemController sysController;
	
	private String messageField;
	
	private Book book;
	
	public AddCopyOfBookController(SystemController sysController) {
		this.sysController = sysController;
	}
	
	public void handleGetBookBtn() {
		book = this.sysController.getLibrary().getBookByISBN(Integer.parseInt(this.isbnField));
		if(book != null){
			this.titleField = book.getTitle();
			this.numberOfCopies = "" + book.getNumberOfCopies();
			this.messageField = "";
		}
		else{
			this.titleField = "";
			this.numberOfCopies = "";
			this.messageField = "No exists a Book with ISBN " + this.isbnField;
		}
	}
	
	public void handleAddCopyBtn(){
		if(book != null){
			this.sysController.addCopyToBook(book);
			this.numberOfCopies = "" + book.getNumberOfCopies();
			this.messageField = "Copy added sucessfully!";
		}
		else{
			this.messageField = "Please first get a book by ISBN.";
		}
		
	}
}
