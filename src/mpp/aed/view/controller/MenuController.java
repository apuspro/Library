package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;

public class MenuController {

	private Stage primaryStage;
	private Stage menuStage;
	
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
}
