package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;
import mpp.aed.library.Book;
import mpp.aed.library.SystemController;

public class BookController {

	@FXML
	private TextField ISBN;
	@FXML
	private TextField title;
	@FXML
	private TextField maxChecoutLength;
	@FXML
	private TextField numberOfCopies;
	
	private Book newBook = new Book();
	
	private Stage bookStage;
	
	public void saveBook(){
		SystemController sController =  SystemController.getInstance();
		this.newBook.setTitle(this.title.getText());
		this.newBook.setISBN(Integer.parseInt(this.ISBN.getText()));
		this.newBook.setMaxCheckoutDays(Integer.parseInt(this.maxChecoutLength.getText()));
		for (int i = 0; i < Integer.parseInt(numberOfCopies.getText())-1; i++) {
			newBook.addCopyBook();
		}
		System.out.println("Book added");
		sController.getLibrary().addBook(newBook);
		sController.serialize(sController.getLibrary());
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
}
