/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpp.aed.view.controller;

import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpp.aed.application.Main;
import mpp.aed.library.CheckOutRecord;
import mpp.aed.library.Entry;
import mpp.aed.library.Library;
import mpp.aed.library.Member;

/**
 *
 * @author 984966
 */
public class MembersCheckoutRecordsController {
    
    private Stage parentStage;
    
    private class Record {
        public String title;
        public String isbn;
        public LocalDate checkoutDate;
        public LocalDate dueDate;
    }
    
    private ObservableList<Record> checkoutRecordsData = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Record> checkoutRecordsView;
    @FXML
    private TableColumn<Record, String> titleColumn;
    @FXML
    private TableColumn<Record, String> isbnColumn;
    @FXML
    private TableColumn<Record, String> checkoutDateColumn;
    @FXML
    private TableColumn<Record, String> dueDateColumn;
  
    @FXML
    protected void initialize() {
        preJava8();
    }

    private void preJava8() {
        titleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().title);
				return prop;
			}
		});
        
        isbnColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().isbn);
				return prop;
			}
		});
        
        checkoutDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().checkoutDate.toString());
				return prop;
			}
		});
        
        dueDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().dueDate.toString());
				return prop;
			}
		});
    }  
    
    public void initData(int memberid) {
        Library library = Library.getInstance();
        Member member = library.getMemberById(memberid);
        CheckOutRecord chr = member.getCheckOutRecord();
        
        for( Entry entry : chr.getEntries() ) {
            Record record = new Record();
            record.checkoutDate = entry.getCheckoutDate();
            record.dueDate = entry.getDueDate();
            record.title = entry.getCopyBook().getOriginal().getTitle();
            record.isbn = ""+entry.getCopyBook().getOriginal().getISBN();
            checkoutRecordsData.add(record);
        }        
        checkoutRecordsView.setItems(checkoutRecordsData);
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }  
}
