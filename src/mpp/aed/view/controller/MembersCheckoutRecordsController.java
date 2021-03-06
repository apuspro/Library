/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpp.aed.view.controller;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpp.aed.library.CheckOutRecord;
import mpp.aed.library.Entry;
import mpp.aed.library.Library;
import mpp.aed.library.Member;

/**
 *
 * @author 984966
 */
public class MembersCheckoutRecordsController {
    
    
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
    
    private Stage parentStage;
    
    private Stage selfStage;
    
    private class Record {
        public String title;
        public String isbn;
        public LocalDate checkoutDate;
        public LocalDate dueDate;
    }
    
    private ObservableList<Record> checkoutRecordsData = FXCollections.observableArrayList();
    
  
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
    
    @FXML
    public void onBackPerformed() {
        this.selfStage.hide();
    }
    
    @FXML
    public void onPrintPerformed() {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("|%40s|", "Title");
        System.out.printf("%12s|", "ISBN");
        System.out.printf("%15s|", "Checkout date");
        System.out.printf("%15s|\n", "Due date");
        System.out.println("---------------------------------------------------------------------------------------");
        for( Record record : checkoutRecordsData ) {
            System.out.printf("|%40s|", record.title);
            System.out.printf("%12s|", record.isbn);
            System.out.printf("%15s|", record.checkoutDate);
            System.out.printf("%15s|\n", record.dueDate);
            System.out.println("---------------------------------------------------------------------------------------");            
        }
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }
    
    public Stage getSelfStage() {
        return selfStage;
    }

    public void setSelfStage(Stage selfStage) {
        this.selfStage = selfStage;
    }
}
