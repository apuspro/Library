package mpp.aed.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CheckoutController {
    
    @FXML
    private TextField memberIdField;
    @FXML
    private TextField isbnField;
    
    @FXML
    public void onCheckoutPerformed() {
        String memberId = memberIdField.getText();
        String isbn = isbnField.getText();
        
        System.out.println("member id: "+memberId+", isbn: "+isbn);
    } 
}
