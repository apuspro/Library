package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;
import mpp.aed.library.Book;
import mpp.aed.library.SystemController;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSet;
import mpp.aed.view.rulsets.RuleSetFactory;

public class BookController {

	@FXML
	private TextField ISBNField;
	@FXML
	private TextField titleField;
	@FXML
	private TextField maxChecOutLengthField;
	@FXML
	private TextField numberOfCopiesField;
	@FXML
	private Text resultMsg;
	
	private Book newBook = new Book();
	
	private Stage bookStage;
	
	@FXML
	public void saveBook(){
		RuleSet userRules = RuleSetFactory.getRuleSet(this);
		try{
			userRules.applyRules(this);
			
			SystemController sController =  SystemController.getInstance();
			this.newBook.setTitle(this.titleField.getText());
			this.newBook.setISBN(Integer.parseInt(this.ISBNField.getText()));
			this.newBook.setMaxCheckoutDays(Integer.parseInt(this.maxChecOutLengthField.getText()));
			for (int i = 0; i < Integer.parseInt(numberOfCopiesField.getText())-1; i++) {
				newBook.addCopyBook();
			}
			
			sController.getLibrary().addBook(newBook);
			sController.serialize(sController.getLibrary());
			
			resultMsg.setText("Book created");
		}catch(RuleException e) {
			resultMsg.setText(e.getMessage());
		}
	}
	
	@FXML
	public void openAuthorView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/AuthorView.fxml"));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
			Stage authorStage = new Stage();
			authorStage.setTitle("Author Edit");
			authorStage.initModality(Modality.WINDOW_MODAL);
			authorStage.initOwner(this.bookStage);			
			AuthorController controller = loader.getController();
			controller.setBook(this.newBook);
			controller.setData();
			controller.setAuthorStage(authorStage);
			
			Scene scene = new Scene(page);
			authorStage.setScene(scene);
			
			authorStage.showAndWait();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cancelBtn(){
		this.bookStage.hide();
	}

	public void setBookStage(Stage bookStage) {
		this.bookStage = bookStage;
	}

	public TextField getISBNField() {
		return ISBNField;
	}

	public TextField getTitleField() {
		return titleField;
	}

	public TextField getMaxChecOutLengthField() {
		return maxChecOutLengthField;
	}

	public TextField getNumberOfCopiesField() {
		return numberOfCopiesField;
	}

	public Book getNewBook() {
		return newBook;
	}
}
