package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;

public class MenuController {

	private Stage primaryStage;
	private Stage menuStage;
	
	@FXML
	public void openAbout(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/AboutView.fxml"));
		BorderPane page;
		try {
			page = (BorderPane) loader.load();
		
			Stage aboutStage = new Stage();
			aboutStage.setTitle("About");
			aboutStage.initModality(Modality.WINDOW_MODAL);
			aboutStage.initOwner(this.menuStage);
			Scene scene = new Scene(page);
			aboutStage.setScene(scene);
	
			// Show the dialog and wait until the user closes it
			aboutStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void openAddBook(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/BookView.fxml"));
		BorderPane page;
		try {
			page = (BorderPane) loader.load();
		
			Stage aboutStage = new Stage();
			aboutStage.setTitle("Book View");
			aboutStage.initModality(Modality.WINDOW_MODAL);
			aboutStage.initOwner(this.menuStage);
			Scene scene = new Scene(page);
			aboutStage.setScene(scene);
	
			// Show the dialog and wait until the user closes it
			aboutStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param primaryStage the primaryStage to set
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
        
        @FXML
    public void openCheckoutBook() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/CheckoutBook.fxml"));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load();

            Stage checkoutBookStage = new Stage();
            checkoutBookStage.setTitle("CheckoutBook View");
            checkoutBookStage.initModality(Modality.WINDOW_MODAL);
            checkoutBookStage.initOwner(this.menuStage);
            Scene scene = new Scene(page);
            checkoutBookStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            checkoutBookStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
