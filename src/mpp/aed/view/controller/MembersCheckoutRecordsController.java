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
    @FXML
    private TextField memberIdField;
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
    
    private void initColumn() {
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

    @FXML
    public void onViewCheckoutRecordsPerformed() {
        System.out.println("processing checkout records..., member id: "+memberIdField.getText());
        
        this.parentStage.hide();
        openMembersCheckoutRecords();
    }
    
        @FXML
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
            initData(Integer.parseInt(memberIdField.getText()));
            controller.initColumn();          
            Scene scene = new Scene(page);
            checkoutBookStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            checkoutBookStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
