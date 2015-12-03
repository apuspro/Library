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
	private MenuItem addUserMI;
	@FXML
	private MenuItem chkOutBookMI;
	@FXML
	private MenuItem memberRecordsMI;
	@FXML
	private MenuItem addMemberMI;
    @FXML
    private MenuItem searchBookMI;
	@FXML
	private MenuItem listBookMI;
	@FXML
	private MenuItem addCopyBookMI;
	
	private Stage primaryStage;
	private Stage menuStage;
	
	@FXML
    protected void initialize(){
		SystemController sController = SystemController.getInstance();
		if(sController.getCurrentUser() instanceof Administrator || 
				sController.getCurrentUser() instanceof SuperUser){
			addBookMI.setVisible(true);
			addMemberMI.setVisible(true);
            searchBookMI.setVisible(true);
            addCopyBookMI.setVisible(true);
		}else{
			addBookMI.setVisible(false);
			addMemberMI.setVisible(false);
            searchBookMI.setVisible(false);
            addCopyBookMI.setVisible(false);
		}
		
		if(sController.getCurrentUser() instanceof Librarian || 
				sController.getCurrentUser() instanceof SuperUser){
			chkOutBookMI.setVisible(true);
			memberRecordsMI.setVisible(true);
            searchBookMI.setVisible(true);
		}else{
			chkOutBookMI.setVisible(false);
			memberRecordsMI.setVisible(false);
            searchBookMI.setVisible(false);
		}
		
		if(sController.getCurrentUser() instanceof SuperUser){
			addUserMI.setVisible(true);
		}else{
			addUserMI.setVisible(false);
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
			scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
			aboutStage.setScene(scene);
			aboutStage.setResizable(false);
	
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
			scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
			bookStage.setScene(scene);
			bookStage.setResizable(false);
			
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
            scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
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
            MemberController controller = loader.getController();
            controller.setParentStage(enterMemberIdStagee);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
            enterMemberIdStagee.setScene(scene);
            enterMemberIdStagee.setResizable(false);

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

            Stage memberStage = new Stage();
            memberStage.setTitle("Member View");
            memberStage.initModality(Modality.WINDOW_MODAL);
            memberStage.initOwner(this.menuStage);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
            memberStage.setScene(scene);
            memberStage.setResizable(false);
            // Show the dialog and wait until the user closes it
            memberStage.showAndWait();
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
			scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
			userStage.setScene(scene);
			userStage.setResizable(false);
			
			UserController controller = loader.getController();
			controller.setUserStage(userStage);
	
			// Show the dialog and wait until the user closes it
			userStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
    public void openListBook(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/ListBooksView.fxml"));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		
			Stage listStage = new Stage();
			listStage.setTitle("List Books View");
			listStage.initModality(Modality.WINDOW_MODAL);
			listStage.initOwner(this.menuStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
			listStage.setScene(scene);
			listStage.setResizable(false);
			
			ListBooksController controller = loader.getController();
			controller.setListBooksStage(listStage);
			controller.setData();
	
			// Show the dialog and wait until the user closes it
			listStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
	public void closeBtn(){
    	System.out.println("Application Closed by close Menu Item!");
        System.exit(0);
	}
        
        @FXML
    public void onSearchBook() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/SearchBookView.fxml"));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load();

            Stage onSearchBookStage = new Stage();
            onSearchBookStage.setTitle("Member's checkout records");
            onSearchBookStage.initModality(Modality.WINDOW_MODAL);
            onSearchBookStage.initOwner(this.menuStage);
            SearchBookController controller = loader.getController();
            controller.setParentStage(onSearchBookStage);
            controller.setSelfStage(onSearchBookStage);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
            onSearchBookStage.setScene(scene);
            onSearchBookStage.setResizable(false);

            // Show the dialog and wait until the user closes it
            onSearchBookStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        
    @FXML
    public void openAddCopyOfBook() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/AddCopyOfBook.fxml"));
        GridPane page;
        try {
            page = (GridPane) loader.load();

            Stage copyOfBookStage = new Stage();
            copyOfBookStage.setTitle("Add Copy Of Book View");
            copyOfBookStage.initModality(Modality.WINDOW_MODAL);
            copyOfBookStage.initOwner(this.menuStage);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
            copyOfBookStage.setScene(scene);
            copyOfBookStage.setResizable(false);

            // Show the dialog and wait until the user closes it
            copyOfBookStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
