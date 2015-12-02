package mpp.aed.view.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;
import mpp.aed.library.BookException;
import mpp.aed.library.Librarian;
import mpp.aed.library.MemberException;
import mpp.aed.library.SuperUser;
import mpp.aed.library.SystemController;
import mpp.aed.library.User;

public class CheckoutController {
    
    @FXML
    private TextField memberIdField;
    @FXML
    private TextField isbnField;
    
    @FXML
    public void onCheckoutPerformed() {
        String memberId = memberIdField.getText();
        String isbn = isbnField.getText();
        User user = SystemController.getInstance().getCurrentUser();
        Librarian librarian = null;
        if( user instanceof Librarian ) {
            librarian = (Librarian)user;
        } else if( user instanceof  SuperUser ) {
            librarian = ((SuperUser)user).getLibrarian();
        }        
        
        try {
            librarian.checkoutBook(Integer.parseInt(memberId), Integer.parseInt(isbn));
        } catch (MemberException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BookException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        onViewCheckoutRecordsPerformed();
        System.out.println("member id: "+memberId+", isbn: "+isbn);
    } 
    
    public void onViewCheckoutRecordsPerformed() {
        System.out.println("processing checkout records..., member id: "+memberIdField.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/MemberCheckoutRecords.fxml"));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load();

            Stage checkoutBookStage = new Stage();
            checkoutBookStage.setTitle("Member's checkout records");
            checkoutBookStage.initModality(Modality.WINDOW_MODAL);
            checkoutBookStage.initOwner(null);
            MembersCheckoutRecordsController controller = loader.getController();
            controller.initData(Integer.parseInt(memberIdField.getText())); 
            controller.setSelfStage(checkoutBookStage);
            Scene scene = new Scene(page);
            checkoutBookStage.setScene(scene);
            // Show the dialog and wait until the user closes it
            checkoutBookStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public TextField getMemberIdField() {
        return memberIdField;
    }

    public TextField getIsbnField() {
        return isbnField;
    }  
}
