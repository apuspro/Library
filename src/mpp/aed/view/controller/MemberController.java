/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import mpp.aed.library.Library;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSet;
import mpp.aed.view.rulsets.RuleSetFactory;

/**
 *
 * @author 984966
 */
public class MemberController {
    private Stage parentStage;    

    @FXML
    private TextField memberIdField;
    @FXML
    private Text resultMsg;
    
    @FXML
    public void onViewCheckoutRecordsPerformed() {
        
        System.out.println("processing checkout records..., member id: "+memberIdField.getText());
        RuleSet rrs = RuleSetFactory.getRuleSet(this);
        try{
            rrs.applyRules(this);
            if( Library.getInstance().getMemberById(Integer.parseInt(memberIdField.getText())) == null ) {
                throw new RuleException("There is no member with the id: "+memberIdField.getText());
            }
        } catch( RuleException ex ) {
            this.resultMsg.setFill(Color.RED);
            this.resultMsg.setText(ex.getMessage());
            return;
        }
        openMembersCheckoutRecords();       
    }
    
    public void openMembersCheckoutRecords() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/MemberCheckoutRecords.fxml"));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load();

            Stage checkoutBookStage = new Stage();
            checkoutBookStage.setTitle("Member's checkout records");
            checkoutBookStage.initModality(Modality.WINDOW_MODAL);
            checkoutBookStage.initOwner(parentStage);
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
    
    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }   

    public TextField getMemberIdField() {
        return memberIdField;
    }  
}
