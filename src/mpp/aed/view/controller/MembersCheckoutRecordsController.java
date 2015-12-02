/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpp.aed.view.controller;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author 984966
 */
public class MembersCheckoutRecordsController {
    private class CheckoutRecords {
        public String title;
        public String isbn;
        public LocalDate checkoutDate;
        public LocalDate dueDate;
    }
    
    @FXML
    private TableView<CheckoutRecords> tableView;
    
    public void initData() {
        
    }
}
