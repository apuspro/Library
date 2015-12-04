package mpp.aed.view.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;
import mpp.aed.library.BookException;
import mpp.aed.library.Librarian;
import mpp.aed.library.MemberException;
import mpp.aed.library.SuperUser;
import mpp.aed.library.SystemController;
import mpp.aed.library.User;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSet;
import mpp.aed.view.rulsets.RuleSetFactory;

public class CheckoutController {
    
    @FXML
    private TextField memberIdField;
    @FXML
    private TextField isbnField;
    @FXML
    private Text resultMsg;
    
    @FXML
    public void onCheckoutPerformed() {
        String memberId = memberIdField.getText();
        String isbn = isbnField.getText();
        
        RuleSet rule = RuleSetFactory.getRuleSet(this);
        try{
            rule.applyRules(this);
        
	        User user = SystemController.getInstance().getCurrentUser();
	        Librarian librarian = null;
	        if( user instanceof Librarian ) {
	            librarian = (Librarian)user;
	        } else if( user instanceof  SuperUser ) {
	            librarian = ((SuperUser)user).getLibrarian();
	        }        
        
	        try {
	            boolean success = librarian.checkoutBook(Integer.parseInt(memberId), Long.parseLong(isbn));
	            if( !success ) {
	                throw new BookException("Not enough copies available");                
	            }
	        } catch (MemberException ex) {
	            resultMsg.setFill(Color.RED);
	            resultMsg.setText(ex.getMessage()); 
	            return;
	        } catch (BookException ex) {
	            resultMsg.setFill(Color.RED);
	            resultMsg.setText(ex.getMessage());
	            return;
	        }
        
	        resultMsg.setFill(Color.GREEN);
	        resultMsg.setText("The book successfully checked out");
	        onViewCheckoutRecordsPerformed();
	        System.out.println("member id: "+memberId+", isbn: "+isbn);
        } catch(RuleException re) {
            this.resultMsg.setFill(Color.RED);
            this.resultMsg.setText(re.getMessage());
        }
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
            scene.getStylesheets().add(getClass().getResource("../../application/DarkTheme.css").toExternalForm());
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
