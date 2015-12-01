package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;
import mpp.aed.library.Book;
import mpp.aed.library.SystemController;

public class BookController {

	@FXML
	private int ISBN;
	@FXML
	private String title;
	@FXML
	private int maxChecoutLength;
	@FXML
	private int numberOfCopies;
	
	private Book newBook;
	
	public void saveBook(){
		SystemController sController =  SystemController.getInstance();
		newBook = new Book(this.title, this.ISBN, this.maxChecoutLength);
		sController.getLibrary().addBook(newBook);
	}
	
	@FXML
	public void openAuthorView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/AuthorView.fxml"));
		BorderPane page;
		try {
			page = (BorderPane) loader.load();
			Stage authorStage = new Stage();
			authorStage.setTitle("Library Menu");
			authorStage.initModality(Modality.WINDOW_MODAL);
			//authorStage.initOwner(this.); TODO
			
			//AuthorController controller = loader.getController();
			
			Scene scene = new Scene(page);
			authorStage.setScene(scene);
			
			authorStage.showAndWait();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
