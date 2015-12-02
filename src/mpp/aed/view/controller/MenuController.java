package mpp.aed.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;
import mpp.aed.library.Administrator;
import mpp.aed.library.Librarian;
import mpp.aed.library.SuperUser;
import mpp.aed.library.SystemController;

public class MenuController {
	
	@FXML
	private MenuItem addBookMI;
	@FXML
	private MenuItem chkOutBookMI;

	private Stage primaryStage;
	private Stage menuStage;
	
	@FXML
    protected void initialize(){
		SystemController sController = SystemController.getInstance();
		if(sController.getCurrentUser() instanceof Administrator || 
				sController.getCurrentUser() instanceof SuperUser){
			addBookMI.setVisible(true);
		}else{
			addBookMI.setVisible(false);
		}
		
		if(sController.getCurrentUser() instanceof Librarian || 
				sController.getCurrentUser() instanceof SuperUser){
			chkOutBookMI.setVisible(true);
		}else{
			chkOutBookMI.setVisible(false);
		}
    }
	
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
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		
			Stage bookStage = new Stage();
			bookStage.setTitle("Book View");
			bookStage.initModality(Modality.WINDOW_MODAL);
			bookStage.initOwner(this.menuStage);
			Scene scene = new Scene(page);
			bookStage.setScene(scene);
			
			BookController controller = loader.getController();
			controller.setBookStage(bookStage);
	
			// Show the dialog and wait until the user closes it
			bookStage.showAndWait();
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
    
        @FXML
    public void openMembersCheckoutRecords() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/MemberCheckoutRecords.fxml"));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load();

            Stage checkoutBookStage = new Stage();
            checkoutBookStage.setTitle("Member's checkout records");
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
    
    public void openMembersId() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/MemberIdView.fxml"));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load();

            Stage enterMemberIdStagee = new Stage();
            enterMemberIdStagee.setTitle("Member's checkout records");
            enterMemberIdStagee.initModality(Modality.WINDOW_MODAL);
            enterMemberIdStagee.initOwner(this.menuStage);
            //MembersCheckoutRecordsController controller = loader.getController();
            MembersCheckoutRecordsController controller = loader.getController();
            controller.setParentStage(enterMemberIdStagee);
            Scene scene = new Scene(page);
            enterMemberIdStagee.setScene(scene);

            // Show the dialog and wait until the user closes it
            enterMemberIdStagee.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public void setMenuStage(Stage menuStage) {
		this.menuStage = menuStage;
	}
		
    @FXML
    public void openCreateMember() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/CreateMember.fxml"));
        GridPane page;
        try {
            page = (GridPane) loader.load();

            Stage checkoutBookStage = new Stage();
            checkoutBookStage.setTitle("Create Member View");
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
    
    @FXML
	public void openUserView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/UserView.fxml"));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		
			Stage userStage = new Stage();
			userStage.setTitle("User View");
			userStage.initModality(Modality.WINDOW_MODAL);
			userStage.initOwner(this.menuStage);
			Scene scene = new Scene(page);
			userStage.setScene(scene);
			
			UserController controller = loader.getController();
			controller.setUserStage(userStage);
	
			// Show the dialog and wait until the user closes it
			userStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
