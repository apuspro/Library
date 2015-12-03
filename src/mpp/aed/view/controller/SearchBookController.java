/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpp.aed.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mpp.aed.library.Library;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSet;
import mpp.aed.view.rulsets.RuleSetFactory;

/**
 *
 * @author HP ENVY TS
 */
public class SearchBookController {
    private Stage parentStage;
    private Stage selfStage;
    @FXML
    private TextField isbnField;
    @FXML
    private Text resultMsg;
    
    @FXML
    public void onSearchPerformed() {
        try {
            RuleSet rs = RuleSetFactory.getRuleSet(this);
            rs.applyRules(this);
        } catch(RuleException ex) {
            resultMsg.setFill(Color.RED);
            resultMsg.setText(ex.getMessage());
        }
        Library library = Library.getInstance();
        if( library.getBookByISBN(Integer.parseInt(isbnField.getText().trim())) != null ) {
            resultMsg.setFill(Color.GREEN);
            resultMsg.setText("A book with ISBN: "+isbnField.getText().trim()+" exists");
        } else {
            resultMsg.setFill(Color.RED);
            resultMsg.setText("A book with ISBN: "+isbnField.getText().trim()+" doesn't exists");
        }        
    }
    
    @FXML
    public void onCancelPerformed() {
        this.selfStage.hide();
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }            

    public TextField getIsbnField() {
        return isbnField;
    }

    public void setIsbnField(TextField isbnField) {
        this.isbnField = isbnField;
    }  

    public Stage getSelfStage() {
        return selfStage;
    }

    public void setSelfStage(Stage selfStage) {
        this.selfStage = selfStage;
    }  
}
