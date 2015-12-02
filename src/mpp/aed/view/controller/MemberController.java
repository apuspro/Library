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
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpp.aed.application.Main;

/**
 *
 * @author 984966
 */
public class MemberController {
    private Stage parentStage;    

    @FXML
    private TextField memberIdField;
    
    @FXML
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
            checkoutBookStage.initOwner(parentStage);
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
    
    public void openMembersCheckoutRecords() {
        
    }
    
    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }   
}
