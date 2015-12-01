package mpp.aed.view.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
        
        
        System.out.println("member id: "+memberId+", isbn: "+isbn);
    } 
}
